package com.decagonhq.clads.ui.view.resourcemanagementfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.data.entity.mappedmodel.ResourceHomeVideoModel
import com.decagonhq.clads.data.model.DataSource
import com.decagonhq.clads.databinding.FragmentResourceViewAllVideosBinding
import com.decagonhq.clads.ui.adapters.recyclerviewadapters.ExoPlayerUIVideosAdapter
import com.decagonhq.clads.utils.helpers.ExoPlayerVideosClickListener

class ResourceViewAllVideosFragment : Fragment(), ExoPlayerVideosClickListener {

    private var _binding: FragmentResourceViewAllVideosBinding? = null
    private val binding get() = _binding!!

    lateinit var recyclerviewLayout: RecyclerView
    lateinit var exoplayerAdapter: ExoPlayerUIVideosAdapter
    private var videoArrayList: ArrayList<ResourceHomeVideoModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResourceViewAllVideosBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        videoArrayList = DataSource.createVideoDataSet()

        recyclerviewLayout = binding.fragmentResourceViewAllVideosRecyclerView

        exoplayerAdapter = ExoPlayerUIVideosAdapter(this, videoArrayList)
        recyclerviewLayout.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerviewLayout.adapter = exoplayerAdapter

        return view
    }

    // destroys the back stack
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClickVideo(resourceHomeVideoModel: ResourceHomeVideoModel) {
        val videoUrlLink = ResourceViewAllVideosFragmentDirections.actionResourceViewAllVideosFragmentToResourceViewIndividualVideoFragment(resourceHomeVideoModel.videoUrl)
        findNavController().navigate(videoUrlLink)
    }
}
