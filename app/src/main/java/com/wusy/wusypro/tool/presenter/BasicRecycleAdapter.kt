package com.wusy.wusyproject.tool.presenter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wusy.wusylibrary.base.BaseRecyclerAdapter
import com.wusy.wusypro.R

class BasicRecycleAdapter(context:Context):BaseRecyclerAdapter<BasicRecycleAdapter.BasicRecycleBean>(context){
    override fun onMyCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return BasicRecycleViewHolder(
                LayoutInflater.from(context)
                        .inflate(R.layout.item_basic_recycl, parent, false)
        )
    }

    override fun onMyBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if(holder is BasicRecycleViewHolder) {
            val thisHolder = holder as BasicRecycleViewHolder
            list[position].run {
                thisHolder.tvTitle.text=title
                thisHolder.tvInformation.text=information
            }

        }
    }
    class BasicRecycleViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tvTitle: TextView =view.findViewById(R.id.tvTitle)
        var tvInformation: TextView =view.findViewById(R.id.tvInformation)

    }
    class BasicRecycleBean{
        var title=""
        var information=""
    }
}
