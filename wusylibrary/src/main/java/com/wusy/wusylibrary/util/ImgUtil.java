package com.wusy.wusylibrary.util;


import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

;


/**
 * Created by Administrator on 2019/9/25.
 */


public class ImgUtil {
    //4.4及以上系统使用这个方法处理图片
    @TargetApi(19)
    public static String handleImageOnKitKat(Context context, Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(context, uri)) {
            //如果是document类型的Uri,则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];  //解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(context, contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果不是document类型的Uri,则使用普通方式处理
            imagePath = getImagePath(context, uri, null);
        }

//        if(savebmp2sdcard(imagePath)){
//
//            return picpath;
//        }


        return imagePath;
//        return getImage(imagePath);
    }

    //4.4及以上系统使用这个方法处理图片
    @TargetApi(19)
    public static String handleImageOnKitKat(Context context,  Uri uri ) {
        String imagePath = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            //如果是document类型的Uri,则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];  //解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(context, contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果不是document类型的Uri,则使用普通方式处理
            imagePath = getImagePath(context, uri, null);
        }
        Logger.e("4.4以下系统使用这个方法处理图片"+imagePath);
        compressImagetwo(ImgUtil.oriBitmap(imagePath,ImgUtil.getBitmapRotateAngle(imagePath)),picpath);
        return imagePath;
//        return getImage(imagePath);
    }

    public static String picpath = "/mnt/sdcard/temp.jpg";
    //4.4以下系统使用这个方法处理图片
    public static String handleImageBeforeKitKat(Context context, Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(context, uri, null);
        Logger.e("4.4以下系统使用这个方法处理图片"+imagePath);

        if(savebmp2sdcard(imagePath)){

            return picpath;
        }
        return imagePath;
    }

    public static String getImagePath(Context context, Uri uri, String selection) {
        String path = null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    //对bitmap进行质量压缩
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100 && options > 10) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }


    //传入图片路径，返回压缩后的bitmap
    public static Bitmap getImage(String srcPath) {
        if (TextUtils.isEmpty(srcPath))  //如果图片路径为空 直接返回
            return null;
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }
    public static boolean getcompressImage(String srcPath) {
        if (TextUtils.isEmpty(srcPath))  //如果图片路径为空 直接返回
            return false;
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return true;
//        return compressImagetwo(bitmap,srcPath);//压缩好比例大小后再进行质量压缩
    }

    /**
     * 获取图片的旋转角度
     * @param imgPath 图片路径
     * @return 返回旋转角度
     */
    public static int getBitmapRotateAngle(String imgPath) {
        // 判断图片方向
        int digree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imgPath);
        } catch (IOException e) {
            Log.e("tag", "getBitmapRotateAngle: Exception",e );

            exif = null;
        }
        if (exif != null) {
            int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);
            switch (ori) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    digree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    digree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    digree = 270;
                    break;
                default:
                    digree = 0;
                    break;
            }

        }
        return digree;
    }
    /**
     *
     * @param path 图片路径
     * @param digree 旋转角度
     * @return
     */
    public static Bitmap oriBitmap(String path,int digree) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        Bitmap bitmapRe;
        // 旋转图片
        Matrix m = new Matrix();
        m.postRotate(digree);
        bitmapRe = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), m, true);
        if (bitmap != bitmapRe) {
            bitmap.recycle();
        }
        return bitmapRe;
    }
    /**
     *
     * @param bitmap 图片路径
     * @param digree 旋转角度
     * @return
     */
    public static Bitmap oriBitmap(Bitmap bitmap,int digree) {
        Bitmap bitmapRe;
        // 旋转图片
        Matrix m = new Matrix();
        m.postRotate(digree);
        bitmapRe = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), m, true);
        if (bitmap != bitmapRe) {
            bitmap.recycle();
        }
        return bitmapRe;
    }

    /**
     * 将bitmap作为一张图片存入到指定路径
     *
     * @param bitmap
     * @param file
     */
    public static void storeImage(Bitmap bitmap, File file) {
        FileOutputStream os;
        try {
            os = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static boolean savebmp2sdcard(String imagePath,boolean needCover){
        int ori = ImgUtil.getBitmapRotateAngle(imagePath);
        Log.e("tag", "savebmp2sdcard: "+ori );
        Bitmap bmp = ImgUtil.oriBitmap(imagePath,ori);

//        return true;
        if(needCover){
            return compressImagetwo(bmp,imagePath);
        }
        return compressImagetwo(bmp,picpath);

    }
    public static boolean savebmp2sdcard(String imagePath){
        int ori = ImgUtil.getBitmapRotateAngle(imagePath);
        Bitmap bmp = ImgUtil.oriBitmap(imagePath,ori);


        return compressImagetwo(bmp,picpath);

    }
    public static boolean compressImagetwo(Bitmap image, String savePath) {
//        Log.e(TAG, "compressImage: bitmap"+ImgUtil.bitmap2Bytes(image,Bitmap.CompressFormat.JPEG).length );
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;
//        Log.e(TAG, "compressImage: baos"+baos.toByteArray().length );
        while (baos.toByteArray().length / 1024 > 1024) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
//            Log.e(TAG, "compressImage: baos"+baos.toByteArray().length );
            options -= 10;//每次都减少10
        }
//        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
////        Bitmap bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.toByteArray().length, null);
//        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        try {
            FileOutputStream fos = new FileOutputStream(new File(savePath));//将压缩后的图片保存的本地上指定路径中
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            Log.e("tag", "compressImage: ",e );
            return false;
        }

        if (image != null) {
            image.recycle();
        }
        return true;

    }

}
