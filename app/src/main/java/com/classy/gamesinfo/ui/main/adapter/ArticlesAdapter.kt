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
import com.classy.gamesinfo.data.model.gamespot.ResultArticle
import com.classy.gamesinfo.databinding.ArticleLayoutBinding

class ArticlesAdapter(private val articles: ArrayList<ResultArticle>) :
    RecyclerView.Adapter<ArticlesAdapter.BaseViewHolder<*>>() {

//    private object VIEW_TYPES {
//        const val ITEM = 0
//        const val LOADING = 1
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            ArticleLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (holder is ArticleViewHolder) {
            holder.bind(articles[position])
            holder.itemView.setOnClickListener {
                articles[position].expanded = !articles[position].expanded
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int = articles.size

    inner class ArticleViewHolder(private val itemBinding: ArticleLayoutBinding) :
        BaseViewHolder<ResultArticle>(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: ResultArticle) {
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
                itemBinding.rowLBLExpandedBody.text =
                    android.text.Html.fromHtml(item.body, HtmlCompat.FROM_HTML_MODE_LEGACY)
                        .toString()
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

//    inner class LoadingViewHolder(private val itemBinding: ItemLoadingLayoutBinding) :
//        BaseViewHolder<Int>(itemBinding.root) {
//
//            override fun bind(item: Int) {
//                itemView.apply {
//                    itemBinding.progressBar.visibility = View.GONE
//                }
//            }
//        }

    fun addArticles(articles: ArrayList<ResultArticle>) {
        this.articles.apply {
            addAll(articles)
        }
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }
}