package com.hy0417sage.mediastorage.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.mediastorage.databinding.LayoutViewHolderBinding

class SharedViewHolder(
    private val binding: LayoutViewHolderBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(searchItem: SearchItem) {
        with(binding) {
            date.text =
                "${searchItem.datetime.split('T')[0]} ${searchItem.datetime.split('T')[1].split('.')[0]}"
            Glide.with(binding.root)
                .load(searchItem.imageUrl)
                .into(imageView)
            if (searchItem.bookmark) {
                likeView.visibility = View.VISIBLE
            } else {
                likeView.visibility = View.GONE
            }
        }
    }
}