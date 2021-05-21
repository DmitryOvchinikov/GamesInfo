package com.classy.gamesinfo.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.classy.gamesinfo.data.model.Game
import com.classy.gamesinfo.databinding.GameLayoutBinding
import kotlin.collections.ArrayList
import android.util.Log


class MainAdapter(private val games: ArrayList<Game>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(private val itemBinding: GameLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(game: Game) {
            itemView.apply {
                itemBinding.rowLBLName.text = game.name
                Log.d("FFFF", "GamesAdapter: ${game.id}")
                Glide.with(itemBinding.rowIMGImage.context).load("https://images.igdb.com/igdb/image/upload/t_cover_small/" + game.id + ".jpg").into(itemBinding.rowIMGImage)
            }
        }
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount(): Int = games.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemBinding = GameLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(itemBinding)
    }

    fun addGames(games: ArrayList<Game>) {
        this.games.apply {
            clear()
            addAll(games)
        }
    }

}