package com.decagonhq.clads.ui.adapters.recyclerviewadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagonhq.clads.data.model.Article
import com.decagonhq.clads.databinding.FragmentResourceViewAllArticlesItemBinding

class ViewAllArticlesRecyclerViewAdapter :
    RecyclerView.Adapter<ViewAllArticlesRecyclerViewAdapter.ArticlesViewHolder>() {

    private var articlesList = arrayListOf<Article>()
    private lateinit var clickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(article: Article, itemView: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val viewBinding = FragmentResourceViewAllArticlesItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticlesViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val article = articlesList[position]
        holder.bind(article, clickListener)
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    class ArticlesViewHolder(private val itemBinding: FragmentResourceViewAllArticlesItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(article: Article, clickListener: OnItemClickListener) = with(itemBinding) {
            resourceViewAllArticlesItemFragmentArticleTitleTextView.text = article.title
            resourceViewAllArticlesItemFragmentArticleAuthorTextView.text = article.author

            Glide.with(this.root.context)
                .load(article.imageUrl)
                .into(resourceViewAllArticlesItemFragmentArticleCoverImageView)
            this.root.setOnClickListener {
                clickListener.onItemClick(article, it)
            }
        }
    }

    fun setData(listOfArticles: ArrayList<Article>, clickListener: OnItemClickListener) {
        this.articlesList = listOfArticles
        this.clickListener = clickListener
        notifyDataSetChanged()
    }
}
