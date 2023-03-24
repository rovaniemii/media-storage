package com.hy0417sage.mediastorage.presentation.config

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.hy0417sage.mediastorage.domain.model.ViewData

class SharedDiffUtil : DiffUtil.ItemCallback<ViewData>() {

    override fun areItemsTheSame(
        oldItem: ViewData,
        newItem: ViewData,
    ): Boolean {
        Log.d("areItemsTheSame " ,  "${oldItem == newItem}")
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: ViewData,
        newItem: ViewData,
    ): Boolean {
        Log.d("areContentsTheSame " ,  "${oldItem.thumbnail == newItem.thumbnail}")
        return oldItem.thumbnail == newItem.thumbnail
    }

}