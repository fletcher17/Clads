package com.decagonhq.clads.ui.adapters.recyclerviewadapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagonhq.clads.data.entity.mappedmodel.VideoResourceModel
import com.decagonhq.clads.databinding.ResourceViewIndividualVideoDesignLayoutBinding

class ResourceViewIndividualVideoFragmentAdapter(private var listener: OnItemClickListener) : RecyclerView.Adapter<ResourceViewIndividualVideoFragmentAdapter.IndividualVideoViewHolder>() {
    var listOfVideos = listOfVideos()
    private fun listOfVideos(): ArrayList<VideoResourceModel> {
        val videoList = arrayListOf<VideoResourceModel>()

        videoList.add(VideoResourceModel("https://reutersinstitute.politics.ox.ac.uk/sites/default/files/2018-01/videob2b.png", "https://html5demos.com/assets/dizzy.mp4"))
        videoList.add(VideoResourceModel("https://cdn.mos.cms.futurecdn.net/APbppGsT3coap5aQTPPJ5L-320-80.jpg", "https://html5demos.com/assets/dizzy.mp4"))
        videoList.add(
            VideoResourceModel(
                "https://media.istockphoto.com/photos/red-coat-woman-with-black-leather-handbag-beautiful-vintage-style-picture-id541149082?k=6&m=541149082&s=612x612&w=0&h=s331gfqk9udiY4INRVWjm2adH9ywJIThLxuTnYLQHDU=",
                "https://html5demos.com/assets/dizzy.mp4"
            )
        )
        videoList.add(
            VideoResourceModel(
                "https://images.unsplash.com/photo-1611162616475-46b635cb6868?ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8dmlkZW98ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60",
                "https://html5demos.com/assets/dizzy.mp4"
            )
        )
        videoList.add(
            VideoResourceModel(
                "https://images.unsplash.com/photo-1551817958-11e0f7bbea9c?ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8dmlkZW98ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60",
                "https://html5demos.com/assets/dizzy.mp4"
            )
        )
        videoList.add(VideoResourceModel("https://ethicalfashioninitiative.org/wp-content/uploads/facebookweb-1.png", "https://html5demos.com/assets/dizzy.mp4"))
        return videoList
    }

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndividualVideoViewHolder {
        if (context == null) {
            context = parent.context
        }
        val binding = ResourceViewIndividualVideoDesignLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IndividualVideoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfVideos.size
    }

    override fun onBindViewHolder(holder: IndividualVideoViewHolder, position: Int) {
        Glide.with(context!!).load(listOfVideos[position].videoThumbnail).into(holder.img)
        holder.resourceVideoFragmentCardView.setOnClickListener {
            val videoUrl = listOfVideos[position].videoUrl
            listener.onItemClick(videoUrl)
        }
    }
    inner class IndividualVideoViewHolder(
        private var binding: ResourceViewIndividualVideoDesignLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val img: ImageView = binding.resourcesViewIndividualVideoDesignLayoutPlayerView
        val resourceVideoFragmentCardView: CardView = binding.resourcesViewIndividualVideoDesignLayoutCardView
    }

    interface OnItemClickListener {
        fun onItemClick(videoUrl: String)
    }
}
