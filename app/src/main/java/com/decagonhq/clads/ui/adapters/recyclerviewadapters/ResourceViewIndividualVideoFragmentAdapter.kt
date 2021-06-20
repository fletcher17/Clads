package com.decagonhq.clads.ui.adapters.recyclerviewadapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagonhq.clads.R
import com.decagonhq.clads.data.entity.mappedmodel.VideoResouceModel

class ResourceViewIndividualVideoFragmentAdapter(private var listener: OnItemClickListener) : RecyclerView.Adapter<ResourceViewIndividualVideoFragmentAdapter.MyViewHolder>() {
    var listOfVideos = listOfVideos()
    private fun listOfVideos(): ArrayList<VideoResouceModel> {
        val videoList = arrayListOf<VideoResouceModel>()

        videoList.add(VideoResouceModel("https://img.youtube.com/vi/s_D5C5e2Uu0/1.jpg", "https://html5demos.com/assets/dizzy.mp4"))
        videoList.add(VideoResouceModel("https://img.youtube.com/vi/s_D5C5e2Uu0/1.jpg", "https://html5demos.com/assets/dizzy.mp4"))
        videoList.add(VideoResouceModel("https://img.youtube.com/vi/s_D5C5e2Uu0/1.jpg", "https://html5demos.com/assets/dizzy.mp4"))
        videoList.add(VideoResouceModel("https://img.youtube.com/vi/s_D5C5e2Uu0/1.jpg", "https://html5demos.com/assets/dizzy.mp4"))
        videoList.add(VideoResouceModel("https://img.youtube.com/vi/s_D5C5e2Uu0/1.jpg", "https://html5demos.com/assets/dizzy.mp4"))
        videoList.add(VideoResouceModel("https://img.youtube.com/vi/s_D5C5e2Uu0/1.jpg", "https://html5demos.com/assets/dizzy.mp4"))
        return videoList
//            mutableListOf("https://img.youtube.com/vi/s_D5C5e2Uu0/1.jpg",
//                "https://img.youtube.com/vi/s_D5C5e2Uu0/1.jpg",
//                "https://img.youtube.com/vi/s_D5C5e2Uu0/1.jpg",
//                "https://img.youtube.com/vi/s_D5C5e2Uu0/1.jpg",
//                "https://img.youtube.com/vi/s_D5C5e2Uu0/1.jpg",
//                "https://img.youtube.com/vi/s_D5C5e2Uu0/1.jpg")
    }

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        if (context == null) {
            context = parent.context
        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.resource_view_individual_video_design_layout, parent, false)
        return MyViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return listOfVideos.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val mediaItem = MediaItem.fromUri(videoList[position])
//        holder.simpleExoPlayer.addMediaItem(mediaItem)
        Glide.with(context!!).load(listOfVideos[position].videoThumbnail).into(holder.img)
        holder.resourceVideoFragmentCardView.setOnClickListener {
            val videoUrl = listOfVideos[position].videoUrl
            listener.onItemClick(videoUrl)
        }
    }
    inner class MyViewHolder(
        itemView: View,
        listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView) {
//        var simpleExoPlayer: SimpleExoPlayer =
//            SimpleExoPlayer.Builder(itemView.context).build()
        val img: ImageView = itemView.findViewById(R.id.resources_view_individual_video_design_layout_playerView_)
        val resourceVideoFragmentCardView: CardView = itemView.findViewById(R.id.resources_view_individual_video_design_layout_card_view)
    }

    interface OnItemClickListener {
        fun onItemClick(videoUrl: String)
    }
}
