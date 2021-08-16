package com.wusy.wusypro.tool

import android.content.Intent
import android.graphics.Bitmap
import android.text.method.ScrollingMovementMethod
import com.google.gson.Gson
import com.huantansheng.easyphotos.EasyPhotos
import com.wusy.wusylibrary.base.BaseActivity
import com.wusy.wusylibrary.pop.ChoiceFilePop
import com.wusy.wusylibrary.util.CameraUtil
import com.wusy.wusylibrary.util.ImgUtil
import com.wusy.wusylibrary.util.OkHttpUtil
import com.wusy.wusypro.R
import com.wusy.wusypro.app.UploadFileBean
import kotlinx.android.synthetic.main.activity_sign.*
import okhttp3.Call
import okhttp3.Response
import org.json.JSONObject
import java.io.File
import java.io.IOException
import com.huantansheng.easyphotos.models.album.entity.Photo
import com.orhanobut.logger.Logger
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


class SignActivity : BaseActivity() {
    private var upLoadImageUrl =
        "https://www.hjlapp.com/cgProgramApi/fileUpload/upload?&type=8&platform=Android"
    private lateinit var log: StringBuffer
    private var signUrl = "https://www.hjlapp.com/cgProgramApi/labourerSign/addOrUpdate"
    lateinit var pop: ChoiceFilePop
    lateinit var jsonUp: JSONObject
    lateinit var random: Random
    override fun getContentViewId(): Int {
        return R.layout.activity_sign
    }

    override fun findView() {

    }

    override fun init() {
        EasyPhotos.preLoad(this)
        log = StringBuffer()
        random = Random()
        pop = ChoiceFilePop(this)
        ivHeader.setOnClickListener {
            pop.showPopupWindow()
        }
        tvLog.movementMethod = ScrollingMovementMethod.getInstance()

        jsonUp = JSONObject()
        jsonUp.put("img", "")
        jsonUp.put("deviceMac", "00:00:a4:ff:ff:7d")
        jsonUp.put("labourerId", "bf6b955cd85640f2b192b6394c9efc67")
        jsonUp.put("programId", "cc0b9d96b4c044f7b3067158afb258fd")
        jsonUp.put("temperature", "0.0")
        addLog("当前设备mac:${jsonUp.getString("deviceMac")}")
        addLog("劳工Id:${jsonUp.getString("labourerId")}")
        addLog("项目Id:${jsonUp.getString("programId")}")

        tvSubmitInto.setOnClickListener {
            if (jsonUp.get("img") == "") {
                showToast("请先上传图片")
            } else {
                initDataInto()
                submit()
            }
        }
        tvSubmitOut.setOnClickListener {
            if (jsonUp.get("img") == "") {
                showToast("请先上传图片")
            } else {
                initDataOut()
                submit()
            }
        }
    }

    private fun initDataInto() {
        val date = SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis())
        val time = "08:${50 + random.nextInt(9)}:${10 + random.nextInt(49)}"
        jsonUp.put("signTime", "$date $time")
    }

    private fun initDataOut() {
        val date = SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis())
        val time = "17:${40 + random.nextInt(19)}:${10 + random.nextInt(49)}"
        jsonUp.put("signTime", "$date $time")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && pop.checkAlnum == requestCode) {
            val resultPhotos = data?.getParcelableArrayListExtra<Photo>(EasyPhotos.RESULT_PHOTOS)
            ivHeader.setImageBitmap(ImgUtil.getImage(resultPhotos!![0].path))
            requestImg(File(resultPhotos[0].path))
        } else if (resultCode == RESULT_OK && requestCode == CameraUtil.REQUEST_CODE_CAPTURE_RAW) {
            CameraUtil.imageFile?.let {
                CameraUtil(this).compressImageMax(it, object : CameraUtil.CallBack {
                    override fun compressImageComplete(imgBitmap: Bitmap, file: File) {
                        runOnUiThread {
                            ivHeader.setImageBitmap(imgBitmap)
                            requestImg(file)
                        }
                    }
                })
            }
        }
    }

    fun requestImg(file: File) {
        OkHttpUtil.getInstance().upLoadFile(
            upLoadImageUrl,
            "file",
            file,
            HashMap<String, String>(),
            object : OkHttpUtil.ResultCallBack {
                override fun successListener(call: Call?, response: Response?) {
                    val json = response!!.body()!!.string()
                    val bean = Gson().fromJson(json, UploadFileBean::class.java)
                    jsonUp.put("img", bean.data)
                    addLog("上传成功，图片Id：${bean.data}")

                }

                override fun failListener(call: Call?, e: IOException?, message: String?) {
                    addLog("上传失败，图片路径：${file.path}")
                }

            })
    }

    fun submit() {
        OkHttpUtil.getInstance()
            .asynPost(signUrl, jsonUp.toString(), object : OkHttpUtil.ResultCallBack {
                override fun failListener(call: Call?, e: IOException?, message: String?) {
                    addLog("失败")
                }

                override fun successListener(call: Call?, response: Response?) {
                    val json = response!!.body()!!.string()
                    addLog("$json")
                }

            })
    }

    private fun addLog(str: String) {
        runOnUiThread {
            tvLog.append(SimpleDateFormat("hh:mm:ss").format(Date()) + " : " + str + "\n")
            val offset = tvLog.lineCount * tvLog.lineHeight
            if (offset > tvLog.height) {
                tvLog.scrollTo(0, offset - tvLog.height + tvLog.lineHeight * 2)
            }
        }
    }
}
