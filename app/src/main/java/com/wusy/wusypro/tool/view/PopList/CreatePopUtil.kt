package com.wusy.wusypro.tool.view.PopList

import com.wusy.wusylibrary.base.BaseActivity
import com.wusy.wusylibrary.pop.*

class CreatePopUtil(activity:BaseActivity){
    var mA=activity

    fun showConfirmPop() {
       val confirmPop=ConfirmPop(mA)
        confirmPop.listener = object : ConfirmPop.OnBtnClickListener {
            override fun clickOk() {
                mA.showToast("点击了确认按钮")
                confirmPop.dismiss()
            }

            override fun clickCancel() {
                mA.showToast("点击了取消按钮")
                confirmPop.dismiss()
            }
        }
        confirmPop.disListener = object : ConfirmPop.OnDismissListener {
            override fun onDismiss() {
                mA.showToast("弹窗关闭")
            }
        }
        confirmPop.showPopupWindow()
    }

    fun showSingleRecyclerChoicePop() {
        val singleRecyclerChoicePop = SingleRecyclerChoicePop(mA)
        singleRecyclerChoicePop.listener=object: SingleRecyclerChoicePop.OnItemClickListener{
            override fun onItemClick(bean: PopSelectBean) {
                mA.showToast("您选中了：${bean.name}")
            }
        }
        singleRecyclerChoicePop.showPopupWindow()
    }

    fun showSingleEasyChoicePop() {
        val singleEasyChoicePop = SingleEasyChoicePop(mA)
        singleEasyChoicePop.listener=object: SingleEasyChoicePop.OnItemClickListener{
            override fun onClickTop() {
                mA.showToast("您点击了同意")
            }

            override fun onClickBottom() {
                mA.showToast("您点击了拒绝")
            }
        }
        singleEasyChoicePop.showPopupWindow()
    }
    fun showCardChoicePop() {
        val cardChoicePop =  CardChoicePop(mA)
        cardChoicePop.listener=object: CardChoicePop.OnItemClickListener{
            override fun onItemClick(bean: PopSelectBean, position: Int) {
                mA.showToast("你选中了：${bean.name}")
            }
        }
        cardChoicePop.showPopupWindow()
    }
    fun showWheelChoicePop(showCount:Int) {
        val wheelChoicePop =  WheelChoicePop(mA)
        wheelChoicePop.init(PopSelectBean.createTestData(),showCount)
        wheelChoicePop.listener=object: WheelChoicePop.OnItemClickListener{
            override fun onClickOk(bean: PopSelectBean?, showCount: Int) {
                mA.showToast("你选中了:${bean?.name?:""}")
                wheelChoicePop.dismiss()
            }
        }
        wheelChoicePop.showPopupWindow()
    }
    fun showSelectItemFromListPop() {
        val selectItemFromListPop =  SelectItemFromListPop(mA)
        selectItemFromListPop.init()
        selectItemFromListPop.listener=object: SelectItemFromListPop.OnItemClickListener{
            override fun onItemClick(list: ArrayList<SelectItemFromListPop.ACMSelectPopBean>) {
                mA.showToast("你选中了:${list.size}个")
            }
        }
        selectItemFromListPop.showPopupWindow()
    }
}
