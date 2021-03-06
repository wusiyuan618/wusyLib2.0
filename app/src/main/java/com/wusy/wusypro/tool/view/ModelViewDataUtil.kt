package com.wusy.wusypro.tool.view

import com.wusy.wusylibrary.view.moduleComponents.ModuleViewBean
import com.wusy.wusypro.R
import com.wusy.wusypro.home.view.MainActivity
import com.wusy.wusypro.tool.view.PopList.PopListActivity

object ModelViewDataUtil {
    fun initStyleModuleData():ArrayList<ModuleViewBean>{
        val list=ArrayList<ModuleViewBean>()
        val jdgx=ModuleViewBean(
            R.mipmap.icon_home_selected,"弹窗样式",
            PopListActivity::class.java)
        list.add(jdgx)
        return list
    }
    fun initTemplateModuleData():ArrayList<ModuleViewBean>{
        val list=ArrayList<ModuleViewBean>()
        val jdgx=ModuleViewBean(R.mipmap.icon_home_selected,"基础列表",
                MainActivity::class.java)
        list.add(jdgx)
        return list
    }
}