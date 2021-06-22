package com.decagonhq.clads.ui.adapters.recyclerviewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.decagonhq.clads.R
import com.decagonhq.clads.data.entity.mappedmodel.ResourceHomeVideoModel
import com.decagonhq.clads.databinding.ResourceHomeVideoPlayerRecyclerViewItemBinding

class ResourceHomeVideoPlayerThumbnailAdapter(
    private var videoPlayerList: ArrayList<ResourceHomeVideoModel>,
    var videoItemListener: VideoItemClick
) :
    RecyclerView.Adapter<ResourceHomeVideoPlayerThumbnailAdapter.EzoPlayViewHolder>() {

    private val countLimit = 5 // SETTING LIMIT TO THE NUMBER OF ITEM DISPLAYED

    fun setClientHomeAdapterList(videoPlayerList: ArrayList<ResourceHomeVideoModel>) {
        this.videoPlayerList = videoPlayerList
    }

    override fun onCreateViewHolder(

        parent: ViewGroup,
        viewType: Int
    ): EzoPlayViewHolder {

        return EzoPlayViewHolder(
            ResourceHomeVideoPlayerRecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EzoPlayViewHolder, position: Int) {

        // BIND ALL VIEWS TO THEIR VARIOUS POSITION
        holder.bindAllThumbNailViews(videoPlayerList[position], position, videoItemListener)
    }

    override fun getItemCount(): Int {
        return if (videoPlayerList.size > countLimit) {
            countLimit
        } else {
            videoPlayerList.size
        }
    }

    // SET UP LIST FOR DUMMY DATA
    fun submitVideoList(allVideoList: ArrayList<ResourceHomeVideoModel>) {
        videoPlayerList = allVideoList
    }

    // VIEW HOLDER FOR THE RECYCLER VIEW
    inner class EzoPlayViewHolder(var binding: ResourceHomeVideoPlayerRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val linearLayout: LinearLayout = binding.videoRecyclerViewPlayerLayout

        fun bindAllThumbNailViews(resourceHomeVideoModel: ResourceHomeVideoModel, position: Int, clicklistener: VideoItemClick) {

            // BINDING ALL VIEWS
            binding.apply {
                videoThumbnailTitle.text = resourceHomeVideoModel.videoTitle
                root.setOnClickListener {
                    videoItemListener.playVideoOnClickListener(resourceHomeVideoModel.videoUrl)
                    clicklistener.playVideoOnClickListener(resourceHomeVideoModel.videoUrl)
                }
            }
            val requestOption = RequestOptions()
                .placeholder(R.drawable.resource_home_video_placeholder)
                .error(R.drawable.resource_home_video_placeholder)
            Glide.with(binding.root.context).applyDefaultRequestOptions(requestOption)
                .load(resourceHomeVideoModel.thumbNailUrl)
                .into(binding.resourceHomeVideoThumbnailImageView)
        }
    }

    interface VideoItemClick {
        fun playVideoOnClickListener(
            videoUrl: String
        )
    }
}
