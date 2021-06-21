package com.decagonhq.clads.ui.adapters.recyclerviewadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.R
import com.decagonhq.clads.utils.Interface.ExoPlayerVideosClickListener

class ExoPlayerUIVideosAdapter(var clickVideo : ExoPlayerVideosClickListener): RecyclerView.Adapter<ExoPlayerUIVideosAdapter.ExpoPlayerUIVideosViewHolder>() {

    var thumbnail = arrayOf(R.drawable.exoplayer_thumbnail, R.drawable.exoplayer_thumbnail, R.drawable.exoplayer_thumbnail, R.drawable.exoplayer_thumbnail, R.drawable.exoplayer_thumbnail, R.drawable.exoplayer_thumbnail)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpoPlayerUIVideosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exoplayer_ui_layout, parent, false)

        return ExpoPlayerUIVideosViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpoPlayerUIVideosViewHolder, position: Int) {
        holder.exoImageBackground.setBackgroundResource(thumbnail[position])
    }

    override fun getItemCount(): Int {
        return thumbnail.size
    }

    inner class ExpoPlayerUIVideosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var exoImageBackground : ImageView

        init {
            exoImageBackground = itemView.findViewById(R.id.exoplayer_ui_image_view)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickVideo.onClickVideo(absoluteAdapterPosition)
        }
    }
}