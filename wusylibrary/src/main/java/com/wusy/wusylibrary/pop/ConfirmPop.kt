package com.wusy.wusylibrary.pop

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import com.wusy.wusylibrary.R
import razerdp.basepopup.BasePopupWindow

class ConfirmPop(context: Context) : BasePopupWindow(context) {
    private var tvTitle = contentView.findViewById<TextView>(R.id.tvTitle)
    private var tvContent = contentView.findViewById<TextView>(R.id.tvContent)
    private var btnOk = contentView.findViewById<TextView>(R.id.btn_ok)
    private var btnCancel = contentView.findViewById<TextView>(R.id.btn_cancel)
    private var ivHide = contentView.findViewById<ImageView>(R.id.ivHide)

    var listener:OnBtnClickListener?=null
    var disListener:OnDismissListener?=null

    init {
        setBlurBackgroundEnable(true)
        popupGravity = Gravity.CENTER
        btnCancel.setOnClickListener {
            listener?.run {
                clickCancel()
            }
        }

        ivHide.setOnClickListener {
            dismiss()
        }
        btnOk.setOnClickListener {
            listener?.run {
                clickOk()
            }
        }
    }

    override fun onCreateShowAnimation(): Animation {
        return getDefaultAlphaAnimation(true)

    }

    override fun onCreateDismissAnimation(): Animation {
        return getDefaultAlphaAnimation(false)
    }


    override fun onCreateContentView(): View {
        return createPopupById(R.layout.pop_confirm)
    }
    fun setOkBtnText(str:String){
        btnOk.text = str
    }
    fun setCancelBtnText(str:String){
        btnCancel.text=str
    }
    fun setTitle(title:String){
        tvTitle.text=title
    }
    fun setContent(content:String){
        tvContent.text=content
    }
    fun setCancelBtnVisible(visible:Int){
        btnCancel.visibility=visible
    }

    override fun dismiss() {
        super.dismiss()
        if(disListener!=null) disListener!!.onDismiss()
    }
    interface OnBtnClickListener{
        fun clickOk()
        fun clickCancel()
    }
    interface OnDismissListener{
        fun onDismiss()
    }
}
