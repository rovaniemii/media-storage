package com.hy0417sage.mediastorage.views.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hy0417sage.domain.model.ViewData
import com.hy0417sage.mediastorage.views.adapter.shared.SharedAdapter
import com.hy0417sage.mediastorage.views.adapter.shared.SharedViewHolder

class SearchAdapter : SharedAdapter() {

    private lateinit var onItemClickListener: OnItemClickListener

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        if (holder is SharedViewHolder) {
            val viewData = getItem(position) as ViewData
            holder.bind(viewData)
            holder.itemView.setOnClickListener {
                onItemClickListener.onClick(viewData)
            }
        }
    }

    fun interface OnItemClickListener {
        fun onClick(viewData: ViewData)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}