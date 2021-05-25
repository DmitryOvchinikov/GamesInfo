package com.classy.gamesinfo.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.classy.gamesinfo.databinding.GameLayoutBinding
import kotlin.collections.ArrayList
import android.util.Log
import com.classy.gamesinfo.data.model.gamespot.Article


class ArticlesAdapter(private val articles: ArrayList<Article>) : RecyclerView.Adapter<ArticlesAdapter.DataViewHolder>() {

    class DataViewHolder(private val itemBinding: GameLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(article: Article) {
            itemView.apply {
                itemBinding.rowLBLName.text = article.body
                Log.d("FFFF", "GamesAdapter: ${article.id}")
                //Glide.with(itemBinding.rowIMGImage.context).load("https://images.igdb.com/igdb/image/upload/t_cover_small/" + article.id + ".jpg").into(itemBinding.rowIMGImage)
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

    fun addArticles(articles: ArrayList<Article>) {
        this.articles.apply {
            clear()
            addAll(articles)
        }
    }

}