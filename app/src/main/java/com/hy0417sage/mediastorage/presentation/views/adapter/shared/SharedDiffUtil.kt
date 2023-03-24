package com.hy0417sage.mediastorage.presentation.views.adapter.shared

import androidx.recyclerview.widget.DiffUtil
import com.hy0417sage.mediastorage.domain.model.ViewData

class SharedDiffUtil : DiffUtil.ItemCallback<ViewData>() {

    override fun areItemsTheSame(
        oldItem: ViewData,
        newItem: ViewData,
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: ViewData,
        newItem: ViewData,
    ): Boolean {
        return oldItem== newItem
    }
}