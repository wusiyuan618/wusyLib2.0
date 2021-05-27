package com.wusy.wusypro.tool.PopList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wusy.wusylibrary.base.BaseRecyclerAdapter
import com.wusy.wusypro.R

class PopListAdapter(context:Context):BaseRecyclerAdapter<PopListAdapter.PopListBean>(context){
    override fun onMyCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return BasicRecycleViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_poplist, parent, false)
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
    class PopListBean{
        var title=""
        var information=""
    }
}