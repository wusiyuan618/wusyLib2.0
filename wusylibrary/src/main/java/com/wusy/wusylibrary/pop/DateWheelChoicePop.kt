package com.wusy.wusylibrary.pop

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.widget.TextView
import com.aigestudio.wheelpicker.WheelPicker
import com.wusy.wusylibrary.R
import razerdp.basepopup.BasePopupWindow
import java.util.*

class DateWheelChoicePop(context: Context) : BasePopupWindow(context) {
    private var wheelYear = findViewById<WheelPicker>(R.id.wheelYear)
    private var wheelMonth = findViewById<WheelPicker>(R.id.wheelMonth)
    private var wheelDay = findViewById<WheelPicker>(R.id.wheelDay)

    private var btnOk = findViewById<TextView>(R.id.tvOk)
    private var btnCancel = findViewById<TextView>(R.id.tvCancel)
    var isHideDayWheel=false
    init {
        setBlurBackgroundEnable(true)
        popupGravity = Gravity.BOTTOM
    }

    override fun onCreateShowAnimation(): Animation {
        return getTranslateVerticalAnimation(1f, 0f, 250)
    }

    override fun onCreateDismissAnimation(): Animation {
        return getTranslateVerticalAnimation(0f, 1f, 250)
    }

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.pop_select_date)
    }

    fun init(listen: View.OnClickListener) {
        wheelYear.data = createTimeYearList()
        wheelMonth.data = createTimeMonthList()
        wheelDay.data = createTimeDayList()

        btnOk.setOnClickListener(listen)
        btnCancel.setOnClickListener {
            dismiss()
        }
        initWheel(wheelYear)
        initWheel(wheelMonth)
        initWheel(wheelDay)
        val calendar = Calendar.getInstance()
        for (i in createTimeYearList().indices) {
            if (createTimeYearList()[i].toInt() == calendar.get(Calendar.YEAR)) {
                wheelYear.setSelectedItemPosition(i, false)
            }
        }
        for (i in createTimeMonthList().indices) {
            if (createTimeMonthList()[i].toInt() == (calendar.get(Calendar.MONTH) + 1)) {
                wheelMonth.setSelectedItemPosition(i, false)
            }
        }
        for (i in createTimeDayList().indices) {
            if (createTimeDayList()[i].toInt() == calendar.get(Calendar.DAY_OF_MONTH)) {
                wheelDay.setSelectedItemPosition(i, false)
            }
        }
    }

    private fun initWheel(wheel: WheelPicker) {
        wheel.setIndicator(true)
        wheel.itemTextColor = context.resources.getColor(R.color.wheelNormalItem)
        wheel.selectedItemTextColor = context.resources.getColor(R.color.colorText)
        wheel.visibleItemCount = 5
        wheel.setCurtain(true)
        wheel.setAtmospheric(true)
        wheel.isCurved = true
    }

    private fun createTimeYearList(): ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 2015..2050) {
            list.add(i.toString())
        }
        return list
    }

    private fun createTimeMonthList(): ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 1..12) {
            if(i<10){
                list.add("0$i")
            }else{
                list.add(i.toString())
            }
        }
        return list
    }

    private fun createTimeDayList(): ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 1..31) {
            if(i<10){
                list.add("0$i")
            }else{
                list.add(i.toString())
            }
        }

        return list
    }

    fun getDate(): String {
        return wheelYear.data[wheelYear.currentItemPosition].toString() +
                "-" + wheelMonth.data[wheelMonth.currentItemPosition].toString() +
                if(!isHideDayWheel) "-" + wheelDay.data[wheelDay.currentItemPosition].toString() else ""
    }

    fun hideDaySelect() {
        isHideDayWheel=true
        wheelDay.visibility= View.GONE
    }
}


