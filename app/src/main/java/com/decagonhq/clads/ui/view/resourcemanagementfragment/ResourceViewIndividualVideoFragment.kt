package com.decagonhq.clads.ui.view.resourcemanagementfragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.databinding.FragmentResourceViewIndividualVideoBinding
import com.decagonhq.clads.ui.adapters.recyclerviewadapters.ResourceViewIndividualVideoFragmentAdapter
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class ResourceViewIndividualVideoFragment :
    Fragment(),
    Player.EventListener,
    ResourceViewIndividualVideoFragmentAdapter.OnItemClickListener {
    private var _binding: FragmentResourceViewIndividualVideoBinding? = null
    private val binding get() = _binding!!

    lateinit var args: ResourceViewIndividualVideoFragmentArgs
    private lateinit var mp4Url: String
    private lateinit var urlList: List<Pair<String, String>>
    private lateinit var simpleExoplayer: SimpleExoPlayer
    private var playbackPosition: Long = 0
    private var currentWindow = 0
    private val dataSourceFactory: DataSource.Factory by lazy {
        DefaultDataSourceFactory(requireContext(), "exoplayer-sample")
    }
    private lateinit var adapter: ResourceViewIndividualVideoFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResourceViewIndividualVideoBinding.inflate(inflater, container, false)
        arguments?.let {
            args = ResourceViewIndividualVideoFragmentArgs.fromBundle(it)
            val resourceHomeVideoModel = args.resourceVideos

            mp4Url = args.videoLink ?: (resourceHomeVideoModel?.videoUrl ?: "")

            urlList = listOf(mp4Url to "default")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ResourceViewIndividualVideoFragmentAdapter(this)
        val recyclerView: RecyclerView = binding.fragmentResourceViewIndividualVideoRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    // Method to start media file
    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT < 24) {
            initializePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    // Initialize player
    private fun initializePlayer() {
        simpleExoplayer = SimpleExoPlayer.Builder(requireContext()).build()
        val randomUrl = urlList.random()
        randomUrl.first?.let { preparePlayer(it) }
        val playerView = binding.fragmentResourceViewIndividualVideoPlayerView
        playerView.player = simpleExoplayer
        simpleExoplayer.seekTo(playbackPosition)
        simpleExoplayer.playWhenReady = true
    }

    // Build the media source
    private fun buildMediaSource(uri: Uri): MediaSource {
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }

    // Prepare player
    private fun preparePlayer(videoUrl: String) {
        val uri = Uri.parse(videoUrl)
        val mediaSource = buildMediaSource(uri)
        simpleExoplayer.prepare(mediaSource)
    }

    // Release player
    private fun releasePlayer() {
        playbackPosition = simpleExoplayer.currentPosition
        currentWindow = simpleExoplayer.currentWindowIndex
        simpleExoplayer.release()
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        if (playbackState == Player.STATE_BUFFERING)
            binding.progressBar.visibility = View.VISIBLE
        else if (playbackState == Player.STATE_READY || playbackState == Player.STATE_ENDED)
            binding.progressBar.visibility = View.INVISIBLE
    }

    // on Item click
    override fun onItemClick(videoUrl: String) {
        var media = MediaItem.fromUri(videoUrl)
        simpleExoplayer.addMediaItem(media)
        simpleExoplayer.setMediaItem(media)
        simpleExoplayer.play()
        Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
    }
}
