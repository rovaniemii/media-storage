package com.hy0417sage.mediastorage.views.search

import androidx.recyclerview.widget.RecyclerView
import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.mediastorage.base.adapter.SharedAdapter
import com.hy0417sage.mediastorage.base.adapter.SharedViewHolder

class SearchAdapter : SharedAdapter() {

    private lateinit var onItemClickListener: OnItemClickListener

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        if (holder is SharedViewHolder) {
            val searchItem = getItem(position) as SearchItem
            holder.bind(searchItem)
            holder.itemView.setOnClickListener {
                onItemClickListener.onClick(searchItem)
            }
        }
    }

    fun interface OnItemClickListener {
        fun onClick(viewData: SearchItem)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}