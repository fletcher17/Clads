package com.decagonhq.clads.ui.view.resourcemanagementfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.databinding.FragmentResourceViewIndividualVideoBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

class ResourceViewIndividualVideoFragment : Fragment() {
    private var _binding: FragmentResourceViewIndividualVideoBinding? = null
    private val binding get() = _binding!!
    private lateinit var simpleExoPlayer: SimpleExoPlayer
    lateinit var playerView: PlayerView
    lateinit var mediaItem: MediaItem
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentResourceViewIndividualVideoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        simpleExoPlayer = SimpleExoPlayer.Builder(requireContext()).build()
        playerView = binding.playerView
        playerView.player = simpleExoPlayer
        mediaItem = MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4")
        simpleExoPlayer.addMediaItem(mediaItem)
        simpleExoPlayer.prepare()
        val adapter = ResourceViewIndividualVideoFragmentAdapter()
        val recyclerView: RecyclerView = binding.recyclerviewLayout
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

    }
}
