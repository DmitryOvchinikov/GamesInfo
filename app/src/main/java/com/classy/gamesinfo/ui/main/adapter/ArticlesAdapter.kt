package com.classy.gamesinfo.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.classy.gamesinfo.R
import com.classy.gamesinfo.data.model.gamespot.Result
import com.classy.gamesinfo.databinding.ArticleLayoutBinding
import com.classy.gamesinfo.databinding.ItemLoadingLayoutBinding


class ArticlesAdapter(private val articles: ArrayList<Result>) :
    RecyclerView.Adapter<ArticlesAdapter.BaseViewHolder<*>>() {

    private object VIEW_TYPES {
        const val ITEM = 0
        const val LOADING = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        if (viewType == VIEW_TYPES.ITEM) {
            val itemBinding = ArticleLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ArticleViewHolder(itemBinding)
        } else {
            val itemBinding = ItemLoadingLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return LoadingViewHolder(itemBinding)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (holder is ArticleViewHolder) {
            holder.bind(articles[position])
            holder.itemView.setOnClickListener {
                articles[position].expanded = !articles[position].expanded
                notifyItemChanged(position)
            }
        } else if (holder is LoadingViewHolder) {
            showLoadingView(holder, position)
        }
    }

    private fun showLoadingView(holder: LoadingViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = articles.size

    override fun getItemViewType(position: Int): Int {
        return if (articles!![position] == null) VIEW_TYPES.LOADING else VIEW_TYPES.ITEM
    }

    inner class ArticleViewHolder(private val itemBinding: ArticleLayoutBinding) :
        BaseViewHolder<Result>(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: Result) {
            itemView.apply {

                if (item.expanded) {
                    itemBinding.rowExpanded.visibility = View.VISIBLE
                } else {
                    itemBinding.rowExpanded.visibility = View.GONE
                }

                itemBinding.rowLBLDescription.text =
                    android.text.Html.fromHtml(item.deck, HtmlCompat.FROM_HTML_MODE_LEGACY)
                        .toString()
                itemBinding.rowLBLTitle.text = item.title
                itemBinding.rowLBLExpandedBody.text = android.text.Html.fromHtml(item.body, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                itemBinding.rowLBLPublished.text = "Published: ${item.publish_date}"
                itemBinding.rowLBLUpdated.text = "Updated: ${item.update_date}"
                itemBinding.rowLBLAuthor.text = "By ${item.authors}"

                val options: RequestOptions = RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_error)

                itemBinding.rowIMGImage.layout(0, 0, 0, 0)
                Glide.with(itemBinding.rowIMGImage.context).load(item.image.square_small)
                    .apply(options).into(itemBinding.rowIMGImage)
            }
        }
    }

    inner class LoadingViewHolder(private val itemBinding: ItemLoadingLayoutBinding) :
        BaseViewHolder<Int>(itemBinding.root) {

            override fun bind(item: Int) {
                itemView.apply {
                    itemBinding.progressBar.visibility = View.GONE
                }
            }
        }

    fun addArticles(articles: ArrayList<Result>) {
        this.articles.apply {
            addAll(articles)
        }
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }
}