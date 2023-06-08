package com.hy0417sage.mediastorage.base.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hy0417sage.core.model.SearchItem

class SharedDiffUtil : DiffUtil.ItemCallback<SearchItem>() {

    override fun areItemsTheSame(
        oldItem: SearchItem,
        newItem: SearchItem,
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: SearchItem,
        newItem: SearchItem,
    ): Boolean {
        return oldItem == newItem
    }
}