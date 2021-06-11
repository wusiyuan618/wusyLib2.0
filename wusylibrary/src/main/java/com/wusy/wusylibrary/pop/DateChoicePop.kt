package com.wusy.wusylibrary.pop

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.wusy.wusylibrary.R
import razerdp.basepopup.BasePopupWindow

class DateChoicePop(context: Context): BasePopupWindow(context) {
    private var rlStartTime = contentView.findViewById<RelativeLayout>(R.id.rlStartTime)
    private var rlEndTime = contentView.findViewById<RelativeLayout>(R.id.rlEndTime)
    private var tvStartTime = contentView.findViewById<TextView>(R.id.tvStartTime)
    private var tvEndTime = contentView.findViewById<TextView>(R.id.tvEndTime)
    private var btnOk = contentView.findViewById<TextView>(R.id.btn_ok)
    var listener:onClickOkListener?=null
    var startPop: DateWheelChoicePop = DateWheelChoicePop(context).apply {
        init(View.OnClickListener {
            tvStartTime.text = getDate()
            dismiss()
        })
    }
    var endPop: DateWheelChoicePop = DateWheelChoicePop(context).apply {
        init(View.OnClickListener {
            tvEndTime.text = getDate()
            dismiss()
        })
    }

    init {
        setBlurBackgroundEnable(true)
        popupGravity = Gravity.CENTER

        rlStartTime.setOnClickListener {
            startPop.showPopupWindow()
        }
        rlEndTime.setOnClickListener {
            endPop.showPopupWindow()
        }
        btnOk.setOnClickListener {
            if(tvStartTime.text.isEmpty()){
                Toast.makeText(context,"请选择开始时间", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(tvEndTime.text.isEmpty()){
                Toast.makeText(context,"请选择结束时间", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            listener?.run {
                val startYear=tvStartTime.text.toString().split("-")[0].toInt()
                val startMonth=tvStartTime.text.toString().split("-")[1].toInt()
                val endYear=tvEndTime.text.toString().split("-")[0].toInt()
                val endMonth=tvEndTime.text.toString().split("-")[1].toInt()
                if(startYear>endYear){
                    Toast.makeText(context,"结束日期不能晚于开始日期", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }else if(startYear==endYear){
                    if(startMonth>endMonth){
                        Toast.makeText(context,"结束日期不能晚于开始日期", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                }
                clickOk(tvStartTime.text.toString(),tvEndTime.text.toString())
                dismiss()
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
        return createPopupById(R.layout.pop_stardate_and_enddate)
    }
    interface onClickOkListener{
        fun clickOk(startTime:String,endTime:String)
    }
}