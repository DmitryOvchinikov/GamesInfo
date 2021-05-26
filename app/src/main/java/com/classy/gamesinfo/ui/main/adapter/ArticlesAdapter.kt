package com.classy.gamesinfo.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.classy.gamesinfo.R
import com.classy.gamesinfo.data.model.gamespot.Result
import com.classy.gamesinfo.databinding.GameLayoutBinding


class ArticlesAdapter(private val articles: ArrayList<Result>) : RecyclerView.Adapter<ArticlesAdapter.DataViewHolder>() {

    class DataViewHolder(private val itemBinding: GameLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(article: Result) {
            itemView.apply {
                //itemBinding.rowLBLName.text = android.text.Html.fromHtml(article.body, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                itemBinding.rowLBLName.text = android.text.Html.fromHtml(article.deck, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                itemBinding.rowLBLTitle.text = article.title

                val options: RequestOptions = RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_error)

                Glide.with(itemBinding.rowIMGImage.context).load(article.image.square_small).apply(options).into(itemBinding.rowIMGImage)

            }
        }
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemBinding = GameLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(itemBinding)
    }

    fun addArticles(articles: ArrayList<Result>) {
        this.articles.apply {
            clear()
            addAll(articles)
        }
    }

}