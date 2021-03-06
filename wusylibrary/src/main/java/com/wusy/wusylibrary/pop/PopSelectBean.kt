package com.wusy.wusylibrary.pop

class PopSelectBean {
    /**
     * 当前Id
     */
    var id = ""
    /**
     * 当前名字
     */
    var name = ""
    /**
     * 父级Id
     */
    var parentId=""
    /**
     * 父级名字
     */
    var parentName=""
    /**
     * 是否选中
     */
    var isSelect = false
    /**
     * 子级集合
     */
    var childList: ArrayList<PopSelectBean>? = null

    companion object {
        fun createTestData(): ArrayList<PopSelectBean> {
            return ArrayList<PopSelectBean>().apply {
                add(PopSelectBean().apply {
                    id ="bl"
                    name = "部落"
                    childList =ArrayList<PopSelectBean>().apply {
                        add(PopSelectBean().apply {
                            name = "萨尔"
                            id ="se"
                            parentId = "bl"
                            parentName ="部落"
                        })
                        add(PopSelectBean().apply {
                            name = "希尔瓦娜斯"
                            id ="xrwns"
                            parentId = "bl"
                            parentName ="部落"
                        })
                        add(PopSelectBean().apply {
                            name = "凯恩血蹄"
                            id ="knxt"
                            parentId = "bl"
                            parentName ="部落"
                        })
                        add(PopSelectBean().apply {
                            name = "伊利丹"
                            id ="yld"
                            parentId = "bl"
                            parentName ="部落"
                        })
                        add(PopSelectBean().apply {
                            name = "贝恩血蹄"
                            id ="bnxt"
                            parentId = "bl"
                            parentName ="部落"
                        })
                        add(PopSelectBean().apply {
                            name = "洛瑟玛塞隆"
                            id ="lsmsl"
                            parentId = "bl"
                            parentName ="部落"
                        })
                    }
                })
                add(PopSelectBean().apply {
                    id ="lm"
                    name = "联盟"
                    childList =ArrayList<PopSelectBean>().apply {
                        add(PopSelectBean().apply {
                            name = "吉安娜"
                            id ="jan"
                            parentId = "lm"
                            parentName ="联盟"
                        })
                        add(PopSelectBean().apply {
                            name = "安度因乌瑞尔"
                            id ="adywre"
                            parentId = "lm"
                            parentName ="联盟"
                        })
                        add(PopSelectBean().apply {
                            name = "瓦里安乌瑞尔"
                            id ="wlawre"
                            parentId = "lm"
                            parentName ="联盟"
                        })
                        add(PopSelectBean().apply {
                            name = "吉恩格雷迈恩"
                            id ="jnglmn"
                            parentId = "lm"
                            parentName ="联盟"
                        })
                        add(PopSelectBean().apply {
                            name = "维纶"
                            id ="wl"
                            parentId = "lm"
                            parentName ="联盟"
                        })
                    }
                })
                add(PopSelectBean().apply {
                    id ="gs"
                    name = "古神"
                    childList =ArrayList<PopSelectBean>().apply {
                        add(PopSelectBean().apply {
                            name = "尤格萨隆"
                            id ="ygsl"
                            parentId = "gs"
                            parentName ="古神"
                        })
                        add(PopSelectBean().apply {
                            name = "恩佐斯"
                            id ="ezs"
                            parentId = "gs"
                            parentName ="古神"
                        })
                        add(PopSelectBean().apply {
                            name = "克苏恩"
                            id ="kse"
                            parentId = "gs"
                            parentName ="古神"
                        })
                        add(PopSelectBean().apply {
                            name = "亚萨极"
                            id ="ysj"
                            parentId = "gs"
                            parentName ="古神"
                        })

                    }
                })
            }
        }
    }
}
