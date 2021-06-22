package com.decagonhq.clads.ui.adapters.recyclerviewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagonhq.clads.data.entity.mappedmodel.ResourceHomeVideoModel
import com.decagonhq.clads.databinding.ExoplayerUiLayoutBinding
import com.decagonhq.clads.utils.helpers.ExoPlayerVideosClickListener

class ExoPlayerUIVideosAdapter(
    var clickVideo: ExoPlayerVideosClickListener,
    var videoPlayerList: ArrayList<ResourceHomeVideoModel>
) : RecyclerView.Adapter<ExoPlayerUIVideosAdapter.ExpoPlayerUIVideosViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpoPlayerUIVideosViewHolder {
        val binding = ExoplayerUiLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ExpoPlayerUIVideosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpoPlayerUIVideosViewHolder, position: Int) {
        val videoList = videoPlayerList[position]

        holder.bind(videoList, clickVideo)
    }

    override fun getItemCount(): Int {
        return videoPlayerList.size
    }

    inner class ExpoPlayerUIVideosViewHolder(private val binding: ExoplayerUiLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(resourceHomeVideoModel: ResourceHomeVideoModel, clickListener: ExoPlayerVideosClickListener) {

            Glide.with(binding.root.context)
                .load(resourceHomeVideoModel.thumbNailUrl)
                .into(binding.exoplayerUiImageView)

            binding.root.setOnClickListener {
                clickListener.onClickVideo(resourceHomeVideoModel)
            }
        }
    }
}
