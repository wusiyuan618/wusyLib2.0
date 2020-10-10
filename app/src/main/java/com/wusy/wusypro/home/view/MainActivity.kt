package com.wusy.wusypro.home.view

import android.Manifest
import android.graphics.Color
import androidx.core.content.FileProvider
import com.wusy.wusylibrary.base.BaseActivity
import com.wusy.wusylibrary.util.permissions.PermissionsManager
import com.wusy.wusylibrary.util.permissions.PermissionsResultAction
import com.wusy.wusypro.R
import com.wusy.wusypro.home.view.MainDataUtil

import kotlinx.android.synthetic.main.activity_main.*


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
        bottomSelectView.setBackgroundColor(Color.WHITE)
        requestPermissions()
    }
    private fun requestPermissions() {
        if (!//写入权限
                PermissionsManager.getInstance().hasAllPermissions(
                        this,
                        arrayOf(
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CALL_PHONE,
                                Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.CAMERA,
                                Manifest.permission.RECORD_AUDIO
                        )
                )
        )
            PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this,
                    arrayOf(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CALL_PHONE,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.CAMERA,
                            Manifest.permission.RECORD_AUDIO

                    ), object :
                    PermissionsResultAction() {
                override fun onGranted() {
                    showLogInfo("权限添加成功")
                }

                override fun onDenied(permission: String) {
                    showLogError("用户拒绝添加权限---$permission")
                }
            })
    }
}
