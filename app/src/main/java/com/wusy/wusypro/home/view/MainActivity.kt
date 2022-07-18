package com.wusy.wusypro.home.view

import android.Manifest
import android.graphics.Color
import com.orhanobut.logger.Logger
import com.wusy.wusylibrary.base.BaseActivity
import com.wusy.wusylibrary.util.CameraUtil
import com.wusy.wusylibrary.util.loggerExpand.LoggerSetting
import com.wusy.wusylibrary.util.loggerExpand.MyDiskLogAdapter
import com.wusy.wusylibrary.util.permissions.PermissionsManager
import com.wusy.wusylibrary.util.permissions.PermissionsResultAction
import com.wusy.wusypro.R
import kotlinx.android.synthetic.main.activity_main.*
import permissions.dispatcher.*

@RuntimePermissions
class MainActivity : BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun findView() {

    }

    override fun init() {
        bottomSelectView.createLayout(
            this,
            MainDataUtil.getInstance(this).bottomSelectData,
            supportFragmentManager,
            R.id.contentView
        )
        showPermissionWithPermissionCheck()
        bottomSelectView.setBackgroundColor(Color.WHITE)

//        PermissionsManager.getInstance()
//            .requestPermissionsIfNecessaryForResult(this,
//                arrayOf(Manifest.permission.CAMERA),
//                object : PermissionsResultAction() {
//                    override fun onGranted() {
//                       Logger.i("onGranted")
//                    }
//
//                    override fun onDenied(permission: String) {
//                        Logger.i("msg---${permission}")
//                    }
//                })
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun showPermission() {
        initLoggerDis()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // NOTE: delegate the permission handling to generated function
        onRequestPermissionsResult(requestCode, grantResults)
        PermissionsManager.getInstance().notifyPermissionsChange(permissions,grantResults)
    }


    private fun initLoggerDis() {
        /**
         * 配置Logger存文件
         */
        if(PermissionsManager.getInstance().hasPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            LoggerSetting.fileName = "wusyProLogger"
            Logger.addLogAdapter(MyDiskLogAdapter(this))//保存到文件
            Logger.i("Logger本地日志模块初始化成功")
        }else{
            Logger.e("Logger本地日志模块初始化失败，没有给存储权限")
        }
    }
//    private fun requestPermissions() {
//        if (!//写入权限
//                PermissionsManager.getInstance().hasAllPermissions(
//                        this,
//                        arrayOf(
//                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                                Manifest.permission.CALL_PHONE,
//                                Manifest.permission.READ_PHONE_STATE,
//                                Manifest.permission.READ_EXTERNAL_STORAGE,
//                                Manifest.permission.ACCESS_FINE_LOCATION,
//                                Manifest.permission.ACCESS_COARSE_LOCATION,
//                                Manifest.permission.CAMERA,
//                                Manifest.permission.RECORD_AUDIO
//                        )
//                )
//        )
//            PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this,
//                    arrayOf(
//                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                            Manifest.permission.CALL_PHONE,
//                            Manifest.permission.READ_PHONE_STATE,
//                            Manifest.permission.READ_EXTERNAL_STORAGE,
//                            Manifest.permission.ACCESS_FINE_LOCATION,
//                            Manifest.permission.ACCESS_COARSE_LOCATION,
//                            Manifest.permission.CAMERA,
//                            Manifest.permission.RECORD_AUDIO
//
//                    ), object :
//                    PermissionsResultAction() {
//                override fun onGranted() {
//                    showLogInfo("权限添加成功")
//                }
//
//                override fun onDenied(permission: String) {
//                    showLogError("用户拒绝添加权限---$permission")
//                }
//            })
//    }
}
