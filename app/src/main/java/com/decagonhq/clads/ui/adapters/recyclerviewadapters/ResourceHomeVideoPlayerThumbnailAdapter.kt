package com.decagonhq.clads.ui.adapters.recyclerviewadapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.decagonhq.clads.R
import com.decagonhq.clads.data.entity.mappedmodel.ResourceHomeVideoModel
import com.decagonhq.clads.databinding.ResourceHomeVideoPlayerRecyclerViewItemBinding

class ResourceHomeVideoPlayerThumbnailAdapter(private var videoPlayerList: ArrayList<ResourceHomeVideoModel>) :
    RecyclerView.Adapter<ResourceHomeVideoPlayerThumbnailAdapter.EzoPlayViewHolder>() {

    private var context: Context? = null

    fun setClientHomeAdapterList(videoPlayerList: ArrayList<ResourceHomeVideoModel>) {
        this.videoPlayerList = videoPlayerList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EzoPlayViewHolder {
        if (context == null) {
            context = parent.context
        }
        return EzoPlayViewHolder(
            ResourceHomeVideoPlayerRecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EzoPlayViewHolder, position: Int) {

        // BIND ALL VIEWS TO THEIR VARIOUS POSITION
        holder.bindAllThumbNailViews(position)
    }

    private val countLimit = 5 // SETTING LIMIT TO THE NUMBER OF ITEM DISPLAYED
    override fun getItemCount(): Int {
        if (videoPlayerList.size > countLimit) {
            return countLimit
        } else {
            return videoPlayerList.size
        }
    }

    // SET UP LIST FOR DUMMY DATA
    fun submitVideoList(allVideoList: ArrayList<ResourceHomeVideoModel>) {
        videoPlayerList = allVideoList
    }

    // VIEW HOLDER FOR THE RECYCLER VIEW
    inner class EzoPlayViewHolder(var binding: ResourceHomeVideoPlayerRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindAllThumbNailViews(position: Int) {
            val currentVideoListItem = videoPlayerList[position]

            // BINDING ALL VIEWS
            binding.apply {
                videoThumbnailTitle.text = currentVideoListItem.videoTitle
            }
            val requestOption = RequestOptions()
                .placeholder(R.drawable.resource_home_video_placeholder)
                .error(R.drawable.resource_home_video_placeholder)
            Glide.with(context!!).applyDefaultRequestOptions(requestOption)
                .load(currentVideoListItem.thumbNailUrl)
                .into(binding.resourceHomeVideoThumbnailImageView)
        }
    }
}
