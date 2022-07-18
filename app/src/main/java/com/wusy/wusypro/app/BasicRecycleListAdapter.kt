package com.wusy.wusypro.app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wusy.wusylibrary.base.BaseRecyclerAdapter
import com.wusy.wusylibrary.base.BaseViewHolder
import com.wusy.wusypro.R

class BasicRecycleListAdapter(context: Context) : BaseRecyclerAdapter<String>(context) {
    override fun onMyCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return BasicRecycleListViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_basic_recycl, parent, false)
        )
    }

    override fun onMyBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        if (holder is BasicRecycleListViewHolder) {
            val thisHolder = holder as BasicRecycleListViewHolder
            list[position]?.run {
                thisHolder.tvName.text=this
            }
        }
    }
    inner class BasicRecycleListViewHolder(itemView: View): BaseViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
    }
}
