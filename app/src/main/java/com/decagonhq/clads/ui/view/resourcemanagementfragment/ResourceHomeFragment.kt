package com.decagonhq.clads.ui.view.resourcemanagementfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bawonelson.ezoplayerrecyclerview.ResourceHomeArticleAdapter
import com.decagonhq.clads.R
import com.decagonhq.clads.data.entity.mappedmodel.ResourceHomeVideoModel
import com.decagonhq.clads.data.model.ArticleModel
import com.decagonhq.clads.data.model.DataSource
import com.decagonhq.clads.databinding.FragmentResourceHomeBinding
import com.decagonhq.clads.ui.adapters.recyclerviewadapters.ResourceHomeVideoPlayerThumbnailAdapter

class ResourceHomeFragment : Fragment(){
    private var _binding: FragmentResourceHomeBinding? = null
    private val binding get() = _binding!!
    private var articleHomeArrayList: ArrayList<ArticleModel> = ArrayList()
    private var videoHomeArrayList:ArrayList<ResourceHomeVideoModel> = ArrayList()
    private lateinit var resourceHomeFragmentVideoAdapter: ResourceHomeVideoPlayerThumbnailAdapter
    private lateinit var resourceHomeArticleAdapter: ResourceHomeArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResourceHomeBinding.inflate(inflater, container, false)
        inflateVideoRecyclerView()
        inflateArticleRecyclerView()
        addVideoDataSet()
        addArticleDataSet()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resourceHomeScreenVideoViewAllTextView.setOnClickListener {
            findNavController().navigate(R.id.action_resourceHomeFragment_to_resourceViewAllVideosFragment)
        }
        binding.resourceHomeScreenArticleViewAllTextView.setOnClickListener {
            findNavController().navigate(R.id.action_resourceHomeFragment_to_resourceViewAllArticlesFragment)
        }
    }

    private fun addArticleDataSet() {
        val articleData = DataSource.createDataSet()
        resourceHomeArticleAdapter.submitArticleList(articleData)
    }
    private fun addVideoDataSet(){
        val videoData = DataSource.createVideoDataSet()
        resourceHomeFragmentVideoAdapter.submitVideoList(videoData)
    }

    private fun inflateVideoRecyclerView() {
        binding.resourceHomeVideoRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        resourceHomeFragmentVideoAdapter = ResourceHomeVideoPlayerThumbnailAdapter(videoHomeArrayList)
        binding.resourceHomeVideoRecyclerView.adapter = resourceHomeFragmentVideoAdapter
    }

    private fun inflateArticleRecyclerView() {
        binding.resourceHomeArticleRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        resourceHomeArticleAdapter = ResourceHomeArticleAdapter(articleHomeArrayList)
        binding.resourceHomeArticleRecyclerView.adapter = resourceHomeArticleAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
