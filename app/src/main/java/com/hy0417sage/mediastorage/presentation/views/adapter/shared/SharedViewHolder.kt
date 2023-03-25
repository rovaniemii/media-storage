package com.hy0417sage.mediastorage.presentation.views.adapter.shared

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hy0417sage.mediastorage.databinding.LayoutViewHolderBinding
import com.hy0417sage.mediastorage.domain.model.ViewData

class SharedViewHolder(
    private val binding: LayoutViewHolderBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(viewData: ViewData) {
        with(binding) {
            date.text =
                "${viewData.datetime.split('T')[0]} ${viewData.datetime.split('T')[1].split('.')[0]}"
            Glide.with(binding.root)
                .load(viewData.thumbnail)
                .into(imageView)
            if (viewData.like) {
                likeView.visibility = View.VISIBLE
            } else {
                likeView.visibility = View.GONE
            }
        }
    }
}