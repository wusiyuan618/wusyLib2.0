package com.wusy.wusylibrary.util
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

/**
 *
 * author : ChenSen
 * data : 2018/3/16
 * desc:
 */
object BitmapUtils {

    fun toByteArray(bitmap: Bitmap): ByteArray {
        var os = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
        return os.toByteArray()
    }

    fun mirror(rawBitmap: Bitmap): Bitmap {
        var matrix = Matrix()
        matrix.postScale(-1f, 1f)
        return Bitmap.createBitmap(rawBitmap, 0, 0, rawBitmap.width, rawBitmap.height, matrix, true)
    }

    fun rotate(rawBitmap: Bitmap, degree: Float): Bitmap {
        var matrix = Matrix()
        matrix.postRotate(degree)
        return Bitmap.createBitmap(rawBitmap, 0, 0, rawBitmap.width, rawBitmap.height, matrix, true)
    }

    fun decodeBitmap(bitmap: Bitmap, reqWidth: Int, reqHeight: Int): Bitmap {
        var options = BitmapFactory.Options()
        options.inJustDecodeBounds = true

        var bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)
        BitmapFactory.decodeByteArray(bos.toByteArray(), 0, bos.size(), options)

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeByteArray(bos.toByteArray(), 0, bos.size(), options)
    }
    fun decodeBitmap(bitmap: Bitmap, reqWidth: Int, reqHeight: Int,savePath:String){
        var options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        options.inJustDecodeBounds = false
        var bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)
        BitmapFactory.decodeByteArray(bos.toByteArray(), 0, bos.size(), options)
        var quality = 90
        while (bos.toByteArray().size / 1024 > 512) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            bos.reset()//重置baos即清空baos
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos)//这里压缩options%，把压缩后的数据存放到baos中
            quality -= 10//每次都减少10
        }

        try {
            val fos = FileOutputStream(File(savePath))//将压缩后的图片保存的本地上指定路径中
            fos.write(bos.toByteArray())
            fos.flush()
            fos.close()
        } catch (e: Exception) {
            Log.e("wsy", "compressImage: ", e)
        }
//        return BitmapFactory.decodeByteArray(bos.toByteArray(), 0, bos.size(), options)
    }
    fun decodeBitmapFromFile(path: String, reqWidth: Int, reqHeight: Int): Bitmap {
        var options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, options)

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(path, options)
    }

    fun decodeBitmapFromResource(res: Resources, resId: Int, reqWidth: Int, reqHeight: Int): Bitmap {
        var options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(res, resId, options)

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(res, resId, options)
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val rawWidth = options.outWidth
        val rawHeight = options.outHeight
        var inSampleSize = 1

        if (rawWidth > reqWidth || rawHeight > reqHeight) {
            val halfWidth = rawWidth / 2
            val halfHeight = rawHeight / 2

            while ((halfWidth / inSampleSize) > reqWidth && (halfHeight / inSampleSize) > reqHeight) {
                inSampleSize *= 2  //设置inSampleSize为2的幂是因为解码器最终还是会对非2的幂的数进行向下处理，获取到最靠近2的幂的数
            }
        }
        return inSampleSize
    }
}