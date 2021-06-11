package com.wusy.wusylibrary.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun setVisibility(visibility: Int) {
        itemView.visibility = visibility
        val params = itemView.layoutParams as (RecyclerView.LayoutParams)
        if (visibility == View.VISIBLE) {
            params.width = RecyclerView.LayoutParams.MATCH_PARENT
            params.height = RecyclerView.LayoutParams.WRAP_CONTENT
        } else {
            params.width = 0
            params.height = 0
        }
        itemView.layoutParams = params
    }
}
