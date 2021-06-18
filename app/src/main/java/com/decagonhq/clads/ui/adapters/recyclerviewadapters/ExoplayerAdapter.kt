package com.decagonhq.clads.ui.adapters.recyclerviewadapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.databinding.ResourceHomeVideoPlayerRecyclerViewItemBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer

class ExoplayerAdapter : RecyclerView.Adapter<ExoplayerAdapter.EzoPlayViewHolder>() {

    private var videoPlayerList =
        mutableListOf(
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
            "http://d10.o2tv.org/Suits/Season%2003/Suits%20-%20S03E15%20(O2TvSeries.Com).mp4",
            "http://d8.o2tv.org/Suits/Season%2003/Suits%20-%20S03E13%20(O2TvSeries.Com).mp4",
            "http://d1.o2tv.org/Suits/Season%2003/Suits%20-%20S03E11%20(O2TvSeries.Com).mp4",
            "http://d11.o2tv.org/Suits/Season%2003/Suits%20-%20S03E14%20(O2TvSeries.Com).mp4",
            "http://d4.o2tv.org/Suits/Season%2003/Suits%20-%20S03E12%20(O2TvSeries.Com).mp4"
        )
    private var context: Context? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EzoPlayViewHolder {
        if (context == null)
            context = parent.context
        // INFLATING THE LAYOUT FOR THIS FRAGMENT
        val binding = ResourceHomeVideoPlayerRecyclerViewItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return EzoPlayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EzoPlayViewHolder, position: Int) {

        holder.binding.resourceHomeRecyclerViewExoplayerView.player = holder.simpleExoPlayer
        val mediaItem = MediaItem.fromUri(videoPlayerList[position])
        holder.simpleExoPlayer.addMediaItem(mediaItem)
        holder.simpleExoPlayer.prepare()
    }

    private val countLimit = 5 // SETTING LIMIT TO THE NUMBER OF ITEM DISPLAYED
    override fun getItemCount(): Int {
        if (videoPlayerList.size > countLimit) {
            return countLimit
        } else {
            return videoPlayerList.size
        }
    }

    // VIEW HOLDER FOR THE RECYCLER VIEW
    inner class EzoPlayViewHolder(var binding: ResourceHomeVideoPlayerRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var simpleExoPlayer: SimpleExoPlayer = SimpleExoPlayer.Builder(context!!).build()
    }
}
