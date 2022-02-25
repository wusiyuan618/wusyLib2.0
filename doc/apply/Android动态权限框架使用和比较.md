# Android动态权限框架使用和比较 #

----------

## PermissionsDispatcher  ##
[PermissionDispatcher-Github传送门](http://https://github.com/permissions-dispatcher/PermissionsDispatcher)

**采坑日记：**
1. 在引入库的时候，Kotlin用户需要使用
```
apply plugin: 'kotlin-android'
 implementation "com.github.permissions-dispatcher:permissionsdispatcher:4.9.1"
 kapt  "com.github.permissions-dispatcher:permissionsdispatcher-processor:4.9.1"
```