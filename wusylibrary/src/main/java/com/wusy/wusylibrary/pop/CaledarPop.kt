package com.wusy.wusylibrary.pop

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import com.wusy.wusylibrary.R
import razerdp.basepopup.BasePopupWindow

class CaledarPop(context: Context, listener: OnUpdateListener) : BasePopupWindow(context) {
    private var ivCancel = findViewById<ImageView>(R.id.ivCancel)
    private var ivTurnUp = findViewById<ImageView>(R.id.ivTurnUp)
    private var ivTurnDown = findViewById<ImageView>(R.id.ivTurnDown)
    private var tvDate = findViewById<TextView>(R.id.tvDate)
    private var calendarView = findViewById<CalendarView>(R.id.calendarView)
    var isCanSelectFluther = false

    init {
        setBlurBackgroundEnable(true)
        popupGravity = Gravity.CENTER
        tvDate.text = "${calendarView.curYear}年${calendarView.curMonth}月"
        ivCancel.setOnClickListener {
            dismiss()
        }
        ivTurnDown.setOnClickListener {
            calendarView.scrollToNext()
        }
        ivTurnUp.setOnClickListener {
            calendarView.scrollToPre()
        }
        calendarView.setOnMonthChangeListener { year, month ->
            tvDate.text = "${year}年${month}月"
        }
        calendarView.setOnCalendarSelectListener(object : CalendarView.OnCalendarSelectListener {
            override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {

                if ((System.currentTimeMillis() < calendar?.timeInMillis ?: 0) && !isCanSelectFluther) {//未来
                    Toast.makeText(context, "不能超过今天哦", Toast.LENGTH_SHORT).show()
                } else {
                    listener.run {
                        updateView(calendar)
                    }
                    dismiss()
                }
            }

            override fun onCalendarOutOfRange(calendar: Calendar?) {
            }

        })
    }

    fun getPopCalendarView(): CalendarView {
        return calendarView
    }


    override fun onCreateShowAnimation(): Animation {
        return getDefaultAlphaAnimation(true)
    }

    override fun onCreateDismissAnimation(): Animation {
        return getDefaultAlphaAnimation(false)
    }

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.pop_caledar)
    }

    interface OnUpdateListener {
        fun updateView(calendar: Calendar?)
    }
}


