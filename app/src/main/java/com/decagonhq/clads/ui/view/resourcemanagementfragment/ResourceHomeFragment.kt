package com.decagonhq.clads.ui.view.resourcemanagementfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bawonelson.ezoplayerrecyclerview.ArticleAdapter
import com.decagonhq.clads.R
import com.decagonhq.clads.data.model.ArticleModel
import com.decagonhq.clads.data.model.DataSource
import com.decagonhq.clads.databinding.FragmentResourceHomeBinding
import com.decagonhq.clads.ui.adapters.recyclerviewadapters.ExoplayerAdapter

class ResourceHomeFragment : Fragment() {
    private var _binding: FragmentResourceHomeBinding? = null
    private val binding get() = _binding!!
    private var articleHomeArrayList: ArrayList<ArticleModel> = ArrayList()
    private lateinit var ezoPlayerHomeFragmentAdapter: ExoplayerAdapter
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResourceHomeBinding.inflate(inflater, container, false)
        inflateVideoRecyclerView()
        inflateArticleRecyclerView()
        addDataSet()
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

    private fun addDataSet() {
        val data = DataSource.createDataSet()
        articleAdapter.submitList(data)
    }

    private fun inflateVideoRecyclerView() {
        binding.resourceHomeVideoRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        ezoPlayerHomeFragmentAdapter = ExoplayerAdapter()
        binding.resourceHomeVideoRecyclerView.adapter = ezoPlayerHomeFragmentAdapter
    }

    private fun inflateArticleRecyclerView() {
        binding.resourceHomeArticleRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        articleAdapter = ArticleAdapter(articleHomeArrayList)
        binding.resourceHomeArticleRecyclerView.adapter = articleAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
