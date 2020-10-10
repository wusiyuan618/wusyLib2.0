package com.wusy.wusypro.app

import android.Manifest
import com.orhanobut.logger.Logger
import com.wusy.wusylibrary.base.BaseApplication
import com.wusy.wusylibrary.util.loggerExpand.LoggerSetting
import com.wusy.wusylibrary.util.loggerExpand.MyDiskLogAdapter
import com.wusy.wusylibrary.util.permissions.PermissionsManager

class AndroidApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        initLoggerDis()
    }
    private fun initLoggerDis() {
        /**
         * 配置Logger存文件
         */
        if(PermissionsManager.getInstance().hasPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            LoggerSetting.changeSavePackage("wusyPro")
            LoggerSetting.fileName = "wusyProLogger"
            Logger.addLogAdapter(MyDiskLogAdapter())//保存到文件
            Logger.i("Logger本地日志模块初始化成功")
        }else{
            Logger.e("Logger本地日志模块初始化失败，没有给存储权限")
        }
    }
}
