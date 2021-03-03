package com.wusy.wusylibrary.pop

class PopSelectBean{

    var name=""
    var isSelect=false
    companion object{
        fun createTestData():ArrayList<PopSelectBean>{
            val list=ArrayList<PopSelectBean>()
            list.add(PopSelectBean().apply {
                name="萨尔"
            })
            list.add(PopSelectBean().apply {
                name="沃金"
            })
            list.add(PopSelectBean().apply {
                name="希尔安维纳斯"
            })
            list.add(PopSelectBean().apply {
                name="凯恩血蹄"
            })
            list.add(PopSelectBean().apply {
                name="贝恩血蹄"
            })
            list.add(PopSelectBean().apply {
                name="加兹鲁维"
            })
            list.add(PopSelectBean().apply {
                name="吉安娜"
            })
            list.add(PopSelectBean().apply {
                name="瓦里安"
            })
            list.add(PopSelectBean().apply {
                name="安度因"
            })
            list.add(PopSelectBean().apply {
                name="卡德加"
            })
            list.add(PopSelectBean().apply {
                name="麦迪文"
            })
            list.add(PopSelectBean().apply {
                name="萨格拉斯"
            })
            return list
        }
    }
}
