# Android动态权限框架使用和比较 #

----------

## PermissionsDispatcher  ##
[PermissionDispatcher-Github传送门](http://https://github.com/permissions-dispatcher/PermissionsDispatcher)

**采坑日记：**
1. 在引入库的时候，Kotlin用户需要使用以下方案导入，否则在Make Project的时候无法生成对应的辅助类

	apply plugin: 'kotlin-android'

	implementation "com.github.permissions-dispatcher:permissionsdispatcher:4.9.1"
	kapt  "com.github.permissions-dispatcher:permissionsdispatcher-processor:4.9.1"


2.`onRequestPermissionsResult`记得重写，加入`onRequestPermissionsResult`回调，否则各个注解的方法不会起作用


	override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // NOTE: delegate the permission handling to generated function
        onRequestPermissionsResult(requestCode, grantResults)
       
    }

##PermissionsManager##
[源码链接](https://github.com/wusiyuan618/wusyLib2.0/tree/master/wusylibrary/src/main/java/com/wusy/wusylibrary/util/permissions)

**采坑日记：**
1.`onRequestPermissionsResult`记得重写，加入` PermissionsManager.getInstance().notifyPermissionsChange(permissions,grantResults)`回调.否则Action不执行。
2.没有在权限改变时发生的回调，可以在`onRequestPermissionsResult`中对这类需求进行处理。`onGranted`是在确定有权限时触发。

	PermissionsManager.getInstance()
            .requestPermissionsIfNecessaryForResult(this,
                arrayOf(Manifest.permission.CAMERA),
                object : PermissionsResultAction() {
                    override fun onGranted() {
                       Logger.i("onGranted")
                    }

                    override fun onDenied(permission: String) {
                        Logger.i("msg---${permission}")
                    }
                })

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
