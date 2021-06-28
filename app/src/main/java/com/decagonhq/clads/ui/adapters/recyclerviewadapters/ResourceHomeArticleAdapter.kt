package com.bawonelson.ezoplayerrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.decagonhq.clads.R
import com.decagonhq.clads.data.model.ArticleModel
import com.decagonhq.clads.databinding.ResourceHomeArticleRecyclerViewItemBinding

class ResourceHomeArticleAdapter(private var articleList: ArrayList<ArticleModel>) :
    RecyclerView.Adapter<ResourceHomeArticleAdapter.ArticleViewHolder>() {

    private var context: Context? = null

    fun setClientHomeAdapterList(articleList: ArrayList<ArticleModel>) {
        this.articleList = articleList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {

        if (context == null)
            context = parent.context

        // INFLATING THE LAYOUT FOR THIS FRAGMENT
        val binding = ResourceHomeArticleRecyclerViewItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        // BIND ALL VIEWS TO THEIR VARIOUS POSITION
        holder.bindAllViews(position)
    }

    private val countLimit = 5 // SETTING LIMIT TO THE NUMBER OF ITEM DISPLAYED
    override fun getItemCount(): Int {
        if (articleList.size > countLimit) {
            return countLimit
        } else {
            return articleList.size
        }
    }

    // SET UP LIST FOR DUMMY DATA
    fun submitArticleList(allArticleList: ArrayList<ArticleModel>) {
        articleList = allArticleList
    }

    // VIEW HOLDER FOR THE RECYCLER VIEW
    inner class ArticleViewHolder(private var binding: ResourceHomeArticleRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindAllViews(position: Int) {
            val currentArticleListItem = articleList[position]

            // BINDING ALL VIEWS
            binding.apply {
                resourceHomeRecyclerViewItemArticleTitle.text = currentArticleListItem.articleTitle
            }
            val requestOption = RequestOptions()
                .placeholder(R.drawable.ic_article_placeholder_article_placeholder)
                .error(R.drawable.ic_article_placeholder_article_placeholder)
            Glide.with(binding.root.context).applyDefaultRequestOptions(requestOption)
                .load(currentArticleListItem.articleImage)
                .into(binding.resourceHomeRecyclerViewItemArticleShapeableImageView)
        }
    }
}
