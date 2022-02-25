package com.wusy.wusypro.app

import android.Manifest
import com.orhanobut.logger.Logger
import com.wusy.wusylibrary.base.BaseApplication
import com.wusy.wusylibrary.util.SharedPreferencesUtil
import com.wusy.wusylibrary.util.loggerExpand.LoggerSetting
import com.wusy.wusylibrary.util.loggerExpand.MyDiskLogAdapter
import com.wusy.wusylibrary.util.permissions.PermissionsManager
import com.wusy.wusypro.BuildConfig

class AndroidApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        initService()
    }
    private fun initService(){
        if(SharedPreferencesUtil.getInstance(this).getData(Contants.ServiceIp,"")!=""){
            URLUtil.baseUrl= SharedPreferencesUtil.getInstance(this).getData(Contants.ServiceIp,"").toString()
        }else{
            URLUtil.baseUrl = "${if (BuildConfig.BUILD_TYPE == "release") "https://www.hjlapp.com/" else "https://t.hjlapp.com/"}"
        }
    }

}
