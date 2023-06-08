package com.hy0417sage.mediastorage.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.mediastorage.databinding.LayoutViewHolderBinding

abstract class SharedAdapter : ListAdapter<SearchItem, RecyclerView.ViewHolder>(SharedDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        return SharedViewHolder(
            LayoutViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        if (holder is SharedViewHolder) {
            val searchItem = getItem(position) as SearchItem
            holder.bind(searchItem)
        }
    }
}