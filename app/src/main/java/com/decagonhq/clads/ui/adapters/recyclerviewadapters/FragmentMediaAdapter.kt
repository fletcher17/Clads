package com.decagonhq.clads.ui.adapters.recyclerviewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagonhq.clads.databinding.FragmentMediaPhotoListLayoutBinding
import com.decagonhq.clads.models.MediaModel
import com.decagonhq.clads.utils.Interface.ImageClick

class FragmentMediaAdapter(private var mediaList: MutableList<MediaModel>, var imageClick: ImageClick) :
    RecyclerView.Adapter<FragmentMediaAdapter.MediaViewHolder>() {

    inner class MediaViewHolder(val binding: FragmentMediaPhotoListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var clientImage = binding.fragmentMediaPhotoListPhotoImageView
        var imageDescription = binding.fragmentMediaPhotoListImageDescriptionTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val binding = FragmentMediaPhotoListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MediaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.imageDescription.text = mediaList[position].imageDescription
        Glide.with(holder.binding.root.context)
            .load(mediaList[position].imageUri)
            .into(holder.clientImage)

        holder.clientImage.setOnClickListener {
            imageClick.onImageClick(mediaList[position].imageUri)
        }
    }

    override fun getItemCount(): Int = mediaList.size

    fun add(media: MediaModel) {
        mediaList.add(media)
        notifyDataSetChanged()
    }
}
