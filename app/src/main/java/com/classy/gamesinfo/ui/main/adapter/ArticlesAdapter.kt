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
import com.classy.gamesinfo.databinding.GameLayoutBinding


class ArticlesAdapter(private val articles: ArrayList<Result>) :
    RecyclerView.Adapter<ArticlesAdapter.DataViewHolder>() {

    private object VIEW_TYPES {
        const val Header = 1
        const val Normal = 2
        const val Footer = 3
    }

    class DataViewHolder(private val itemBinding: GameLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(article: Result) {
            itemView.apply {

                if (article.expanded) {
                    itemBinding.rowExpanded.visibility = View.VISIBLE
                } else {
                    itemBinding.rowExpanded.visibility = View.GONE
                }

                itemBinding.rowLBLDescription.text =
                    android.text.Html.fromHtml(article.deck, HtmlCompat.FROM_HTML_MODE_LEGACY)
                        .toString()
                itemBinding.rowLBLTitle.text = article.title
                itemBinding.rowLBLExpandedBody.text = android.text.Html.fromHtml(article.body, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                itemBinding.rowLBLPublished.text = "Published: ${article.publish_date}"
                itemBinding.rowLBLUpdated.text = "Updated: ${article.update_date}"
                itemBinding.rowLBLAuthor.text = "By ${article.authors}"

                val options: RequestOptions = RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_error)

                itemBinding.rowIMGImage.layout(0, 0, 0, 0)
                Glide.with(itemBinding.rowIMGImage.context).load(article.image.square_small)
                    .apply(options).into(itemBinding.rowIMGImage)
            }
        }
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(articles[position])
        holder.itemView.setOnClickListener {
            articles[position].expanded = !articles[position].expanded
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = articles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemBinding =
            GameLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(itemBinding)
    }

    fun addArticles(articles: ArrayList<Result>) {
        this.articles.apply {
            clear()
            addAll(articles)
        }
    }
}