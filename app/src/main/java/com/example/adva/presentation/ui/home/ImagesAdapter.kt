package com.example.adva.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.adva.data.model.ImageItem
import com.example.adva.databinding.ImageItemBinding

class ImagesAdapter : PagingDataAdapter<ImageItem, ImagesAdapter.ImageViewHolder>(DIFF_CALLBACK) {

    class ImageViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(image: ImageItem?) {
            binding.imageItem=image
            binding.img.setOnClickListener {
                val actionHomeFragmentToDetailsFragment=HomeFragmentDirections.actionHomeFragmentToDetailsFragment()
                actionHomeFragmentToDetailsFragment.url = image!!.url
                itemView
                    .findNavController()
                    .navigate(actionHomeFragmentToDetailsFragment)
            }

        }

    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<ImageItem>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(
                oldConcert: ImageItem,
                newConcert: ImageItem
            ) = oldConcert.id == newConcert.id

            override fun areContentsTheSame(
                oldConcert: ImageItem,
                newConcert: ImageItem
            ) = oldConcert == newConcert
        }

    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image: ImageItem? = getItem(position)

        // Note that "concert" is a placeholder if it's null.
        holder.bindTo(image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ImageViewHolder(binding)
    }
}