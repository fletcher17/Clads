package com.decagonhq.clads.ui.view.resourcemanagementfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.R
import com.decagonhq.clads.data.model.Article
import com.decagonhq.clads.databinding.FragmentResourceViewAllArticlesBinding
import com.decagonhq.clads.ui.adapters.recyclerviewadapters.ViewAllArticlesRecyclerViewAdapter

class ResourceViewAllArticlesFragment : Fragment(), ViewAllArticlesRecyclerViewAdapter.OnItemClickListener {

    private var _binding: FragmentResourceViewAllArticlesBinding? = null
    private val binding get() = _binding!!
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResourceViewAllArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.resourceViewAllArticlesFragmentRecyclerView
        val recyclerViewAdapter = ViewAllArticlesRecyclerViewAdapter()
        recyclerViewAdapter.setData(Article.getDefaultArticleList(), this)
        recyclerView.adapter = recyclerViewAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(article: Article, itemView: View) {

        val articleBundle = bundleOf(getString(R.string.resource_view_individual_article_fragment_article_link_key) to article.articleLink)

        view?.findNavController()?.navigate(R.id.action_resourceViewAllArticlesFragment_to_resourceViewIndividualArticleFragment, articleBundle)
    }
}
