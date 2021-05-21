package com.classy.gamesinfo.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.classy.gamesinfo.R
import com.classy.gamesinfo.data.model.Game
import kotlin.collections.ArrayList

//TODO: update game_layout.xml to feature games

class MainAdapter(private val games: ArrayList<Game>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(game: Game) {
            itemView.apply {
                //TODO: apply game information (name/pic/etc...)
                //textViewUserName.text = game.name
            }
        }
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount(): Int = games.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.game_layout, parent, false))

    fun addGames(games: ArrayList<Game>) {
        this.games.apply {
            clear()
            addAll(games)
        }
    }

}