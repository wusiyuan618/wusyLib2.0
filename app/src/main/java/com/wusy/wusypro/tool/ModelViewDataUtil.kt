package com.wusy.wusypro.tool

import com.wusy.wusylibrary.view.moduleComponents.ModuleViewBean
import com.wusy.wusypro.R
import com.wusy.wusypro.home.view.MainActivity
import com.wusy.wusypro.tool.BasicRecycleList.BasicRecycleListActivity
import com.wusy.wusypro.tool.PopList.PopListActivity

object ModelViewDataUtil {
    fun initStyleModuleData():ArrayList<ModuleViewBean>{
        val list=ArrayList<ModuleViewBean>()
        val jdgx=ModuleViewBean(
            R.mipmap.icon_home_selected,"弹窗样式",
            PopListActivity::class.java)
        list.add(jdgx)

        val cardStyle=ModuleViewBean(R.mipmap.icon_home_selected,"卡片样式",
            CardStyleActivity::class.java)
        list.add(cardStyle)

        return list
    }
    fun initTemplateModuleData():ArrayList<ModuleViewBean>{
        val list=ArrayList<ModuleViewBean>()
        val jdgx=ModuleViewBean(R.mipmap.icon_home_selected,"基础列表",
                BasicRecycleListActivity::class.java)
        list.add(jdgx)

        val twsc=ModuleViewBean(R.mipmap.icon_home_selected,"附件上传",
            UploadFileActivity::class.java)
        list.add(twsc)

        val wzzyy=ModuleViewBean(R.mipmap.icon_home_selected,"文字转语音",
            BasicRecycleListActivity::class.java)
        list.add(wzzyy)

        val ble=ModuleViewBean(R.mipmap.icon_home_selected,"BLE蓝牙工具",
            BasicRecycleListActivity::class.java)
        list.add(ble)

        val ckts=ModuleViewBean(R.mipmap.icon_home_selected,"串口调试工具",
            BasicRecycleListActivity::class.java)
        list.add(ckts)

        val udp=ModuleViewBean(R.mipmap.icon_home_selected,"UDP调试工具",
            BasicRecycleListActivity::class.java)
        list.add(udp)

        val socket=ModuleViewBean(R.mipmap.icon_home_selected,"Socket实例",
            BasicRecycleListActivity::class.java)
        list.add(socket)
        return list
    }
}