package com.wusy.wusylibrary.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.wusy.wusylibrary.util.permissions.PermissionsManager
import com.wusy.wusylibrary.util.permissions.PermissionsResultAction
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread

/**
 * 这是一个快速实现相机拍照、照片裁剪的工具类
 */
class CameraUtil(context: Context) {

    val TAG: String = "CameraUtil"

    var mC = context

    //拍照(返回原始图)
    companion object {
        var imageFile: File? = null     //拍照后保存的照片
        var imgUri: Uri? = null         //拍照后保存的照片的uri
        val AUTHORITY = "com.wusy.wusypro.fileProvider" //FileProvider的签名
        val REQUEST_CODE_CAPTURE_RAW = 6 //startActivityForResult时的请求码
    }

    /**
     * 打开系统相机
     */
    fun gotoCaptureRawOnly(activity: Activity) {
        imageFile = createImageFile()
        imageFile?.let {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {  //如果是7.0以上，使用FileProvider，否则会报错
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                imgUri = FileProvider.getUriForFile(activity, AUTHORITY, it)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri) //设置拍照后图片保存的位置
            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(it)) //设置拍照后图片保存的位置
            }
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString()) //设置图片保存的格式
            intent.resolveActivity(activity.packageManager)?.let {
                if (PermissionsManager.getInstance().hasPermission(
                        activity,
                        Manifest.permission.CAMERA
                    )
                ) {
                    activity.startActivityForResult(intent, REQUEST_CODE_CAPTURE_RAW) //调起系统相机
                } else {
                    Toast.makeText(activity, "打开相机失败，需要获取相机权限", Toast.LENGTH_SHORT).show()
                    PermissionsManager.getInstance()
                        .requestPermissionsIfNecessaryForResult(activity,
                            arrayOf(Manifest.permission.CAMERA),
                            object : PermissionsResultAction() {
                                override fun onGranted() {
                                    activity.startActivityForResult(
                                        intent,
                                        REQUEST_CODE_CAPTURE_RAW
                                    ) //调起系统相机
                                }

                                override fun onDenied(permission: String) {

                                }
                            })
                }
            }
        }
    }

    fun gotoCaptureRawOnlyByFragment(fragment: Fragment) {
        imageFile = createImageFile()
        imageFile?.let {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {  //如果是7.0以上，使用FileProvider，否则会报错
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                imgUri = FileProvider.getUriForFile(fragment.context!!, AUTHORITY, it)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri) //设置拍照后图片保存的位置
            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(it)) //设置拍照后图片保存的位置
            }
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString()) //设置图片保存的格式
            intent.resolveActivity(fragment.activity!!.packageManager)?.let {
                if (PermissionsManager.getInstance().hasPermission(
                        fragment.activity!!,
                        Manifest.permission.CAMERA
                    )
                ) {
                    fragment.startActivityForResult(intent, REQUEST_CODE_CAPTURE_RAW) //调起系统相机
                } else {
                    Toast.makeText(fragment.activity!!, "打开相机失败，需要获取相机权限", Toast.LENGTH_SHORT)
                        .show()
                    PermissionsManager.getInstance()
                        .requestPermissionsIfNecessaryForResult(fragment.activity!!,
                            arrayOf(Manifest.permission.CAMERA),
                            object : PermissionsResultAction() {
                                override fun onGranted() {
                                    fragment.startActivityForResult(
                                        intent,
                                        REQUEST_CODE_CAPTURE_RAW
                                    ) //调起系统相机
                                }

                                override fun onDenied(permission: String) {

                                }
                            })
                }
            }
        }
    }

    fun gotoCaptureRaw(activity: Activity) {
        gotoCaptureRaw(activity)
    }

    //生成一个文件
    private fun createImageFile(isCrop: Boolean = false): File? {
        return try {
            var rootFile =
                File(FileUtil.getFileDir(mC))
            if (!rootFile.exists())
                rootFile.mkdirs()
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val fileName = if (isCrop) "IMG_${timeStamp}_CROP.jpg" else "IMG_$timeStamp.jpg"
            File(rootFile.absolutePath + File.separator + fileName)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    //压缩图片并显示
    fun compressImage(file: File, listener: CallBack) {
        thread {
            val temp = System.currentTimeMillis()
            val ori = ImgUtil.getBitmapRotateAngle(file.absolutePath)
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            val compressBitmap = BitmapUtils.decodeBitmap(bitmap, 1080, 1080)
            Log.d("TAG", "原始图片大小  ${bitmap.width} * ${bitmap.height}")
            Log.d("TAG", "压缩后图片大小  ${compressBitmap.width} * ${compressBitmap.height}")
            Log.d("TAG", "加载图片耗时 ${System.currentTimeMillis() - temp}")
            ImgUtil.oriBitmap(file.path, ImgUtil.getBitmapRotateAngle(file.path))
//            ImgUtil.storeImage(compressBitmap,file)
            val callBackFile = compressImage(compressBitmap, 800)
            val callBackBitmap = BitmapFactory.decodeFile(callBackFile.path)
            listener.compressImageComplete(ImgUtil.oriBitmap(callBackBitmap, ori), callBackFile)
        }
    }

    //压缩图片并显示
    fun compressImageMax(file: File, listener: CallBack) {
        thread {
            val ori = ImgUtil.getBitmapRotateAngle(file.path)
            Log.e("tag", "savebmp2sdcard: $ori")
            var temp = System.currentTimeMillis()
            var bitmap = BitmapFactory.decodeFile(file.absolutePath)
            BitmapUtils.decodeBitmap(bitmap, 1080, 1080, file.path)
            Log.i("wsy", file.length().toString())
            Log.e("tag", "savebmp2sdcard: " + file.path + "\n" + file.absolutePath)
//            ImgUtil.compressImage( ImgUtil.oriBitmap(file.path, ImgUtil.getBitmapRotateAngle(file.path)))
            var callBackBitmap = ImgUtil.oriBitmap(bitmap, ori)
            var callBackFile = compressImage(callBackBitmap, 800)
            Log.d("TAG", "送出图片大小  ${callBackBitmap.width} * ${callBackBitmap.height}")
            listener.compressImageComplete(callBackBitmap, callBackFile)
        }
    }


    open interface CallBack {
        fun compressImageComplete(imgBitmap: Bitmap, file: File)
    }

    /**
     * 压缩图片（质量压缩）
     * 压缩BitMap至指定大小以内，并且返回File
     * @param bitmap
     */
    fun compressImage(bitmap: Bitmap): File {
        var baos: ByteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        var options = 100
        while (baos.toByteArray().size / 1024 > 500) {  //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset()//重置baos即清空baos
            options -= 10//每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos)//这里压缩options%，把压缩后的数据存放到baos中
            var length = baos.toByteArray().size
        }
        var format: SimpleDateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        var date: Date = Date(System.currentTimeMillis())
        var filename = format.format(date)
        var file: File = File(FileUtil.getFileDir(mC), "$filename.png")
        try {
            var fos: FileOutputStream = FileOutputStream(file)
            try {
                fos.write(baos.toByteArray())
                fos.flush()
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
//        recycleBitmap(bitmap)
        return file
    }

    /**
     * 压缩图片（质量压缩）
     * 压缩BitMap至指定大小以内，并且返回File
     * @param bitmap
     */
    fun compressImage(bitmap: Bitmap, toSize: Int): File {
        var baos: ByteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        var options = 100
        while (baos.toByteArray().size / 1024 > toSize) {  //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset()//重置baos即清空baos
            options -= 10//每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos)//这里压缩options%，把压缩后的数据存放到baos中
            var length = baos.toByteArray().size
        }
        var format: SimpleDateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        var date: Date = Date(System.currentTimeMillis())
        var filename = format.format(date)
        var file: File = File(FileUtil.getFileDir(mC), "$filename.png")
        try {
            var fos: FileOutputStream = FileOutputStream(file)
            try {
                fos.write(baos.toByteArray())
                fos.flush()
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
//        recycleBitmap(bitmap)
        return file
    }

    fun recycleBitmap(bm: Bitmap) {
        if (null != bm && !bm.isRecycled) {
            bm.recycle()
        }
    }

}
