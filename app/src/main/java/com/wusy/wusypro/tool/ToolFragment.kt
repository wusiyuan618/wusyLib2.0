package com.wusy.wusypro.tool

import android.graphics.Color
import android.view.View
import com.wusy.wusylibrary.base.BaseFragment
import com.wusy.wusylibrary.view.TitleView
import com.wusy.wusylibrary.view.moduleComponents.ModuleView
import com.wusy.wusypro.R

class ToolFragment : BaseFragment(){
    private lateinit var styleModule: ModuleView
    private lateinit var templateModule: ModuleView
    private lateinit var toolModule: ModuleView


    private lateinit var titleView: TitleView

    override fun findView(view: View?) {
        styleModule=view!!.findViewById(R.id.styleModule)
        titleView=view.findViewById(R.id.titleView)
        templateModule=view.findViewById(R.id.templateModule)
        toolModule=view.findViewById(R.id.toolModule)

    }

    override fun init() {
        titleView.setTitle("工具")
                .build()
        initModule()
    }

    override fun getContentViewId(): Int {
        return R.layout.fragment_tool
    }

    /**
     * 初始化 ModuleView
     */
    private fun initModule(){
        val styleModuleList= ModelViewDataUtil.initStyleModuleData()
        if(styleModuleList.size==0){
            styleModule.visibility=View.GONE
        }else{
            styleModule.visibility=View.VISIBLE
            styleModule.setTitle("样式专区", Color.BLACK)
                    .showRecycelerView(context,styleModuleList,null,null)
        }

        val templateModuleList= ModelViewDataUtil.initTemplateModuleData()
        if(styleModuleList.size==0){
            templateModule.visibility=View.GONE
        }else{
            templateModule.visibility=View.VISIBLE
            templateModule.setTitle("模板专区", Color.BLACK)
                    .showRecycelerView(context,templateModuleList,null,null)
        }

        val toolModuleList= ModelViewDataUtil.initToolModuleData()
        if(styleModuleList.size==0){
            toolModule.visibility=View.GONE
        }else{
            toolModule.visibility=View.VISIBLE
            toolModule.setTitle("工具专区", Color.BLACK)
                .showRecycelerView(context,toolModuleList,null,null)
        }
    }
}
