package com.classy.gamesinfo.ui.main.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.classy.gamesinfo.R
import com.classy.gamesinfo.data.model.gamespot.Franchise
import com.classy.gamesinfo.data.model.gamespot.Genre
import com.classy.gamesinfo.data.model.gamespot.ResultGame
import com.classy.gamesinfo.databinding.GameLayoutBinding

class GamesAdapter(private val gamesList: ArrayList<ResultGame>) :
    RecyclerView.Adapter<GamesAdapter.BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            GameLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (holder is GamesViewHolder) {
            holder.bind(gamesList[position])
            holder.itemView.setOnClickListener {
                gamesList[position].expanded = !gamesList[position].expanded
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int = gamesList.size

    inner class GamesViewHolder(private val itemBinding: GameLayoutBinding) :
        BaseViewHolder<ResultGame>(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: ResultGame) {
            itemView.apply {

                if (item.expanded) {
                    itemBinding.gameRowExpanded.visibility = View.VISIBLE
                } else {
                    itemBinding.gameRowExpanded.visibility = View.GONE
                }

                itemBinding.gameRowLBLDescription.text =
                    android.text.Html.fromHtml(item.deck, HtmlCompat.FROM_HTML_MODE_LEGACY)
                        .toString()
                itemBinding.gameRowLBLTitle.text = item.name
                if (!item.description.isEmpty()) {
                    itemBinding.gameRowLBLExpandedBody.text = android.text.Html.fromHtml(item.description, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                } else {
                    itemBinding.gameRowLBLExpandedBody.text = "We have no information about this game! Sorry!"
                }
                itemBinding.gameRowLBLGenres.text = "Genres: ${item.genres}"
                itemBinding.gameRowLBLReleaseDate.text = "Release Date: ${item.release_date}"
                itemBinding.gameRowLBLLink.text = item.site_detail_url

                val options: RequestOptions = RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_error)

                itemBinding.gameRowIMGImage.layout(0, 0, 0, 0)
                if (item.image != null) {
                    Glide.with(itemBinding.gameRowIMGImage.context).load(item.image.square_small)
                        .apply(options).into(itemBinding.gameRowIMGImage)
                } else {
                    Glide.with(itemBinding.gameRowIMGImage.context).load(R.drawable.placeholder_error).apply(options).into(itemBinding.gameRowIMGImage)
                }
            }
        }
    }

    fun addGames(games: ArrayList<ResultGame>) {
        this.gamesList.apply {
            Log.d("FFFF", "GamesAdapter: addGames()")
            clear()
            addAll(games)
        }
    }

    fun addGamesByGenre(games: ArrayList<ResultGame>, genre: Genre) {
        this.gamesList.apply {
            clear()
            Log.d("FFFF", "SIZE:" + games.size)
            Log.d("FFFF", "CHOSEN GENRE:$genre")
            for (i in 0..games.size) {
                if (genre in games[i].genres) {
                    Log.d("FFFF", "GAMES[$i] GENRE: " + games[i].genres)
                    add(games[i])
                }
            }
        }
    }

    fun addGamesByFranchise(games: ArrayList<ResultGame>, franchise: Franchise) {
        this.gamesList.apply {
            clear()
            Log.d("FFFF", "SIZE:" + games.size)
            Log.d("FFFF", "CHOSEN FRANCHISE:$franchise")
            for (i in 0..games.size) {
                if (franchise in games[i].franchises) {
                    Log.d("FFFF", "GAMES[$i] FRANCHISE: " + games[i].genres)
                    add(games[i])
                }
            }
        }
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }
}