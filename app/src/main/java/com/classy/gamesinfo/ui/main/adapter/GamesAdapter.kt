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
import com.classy.gamesinfo.data.model.gamespot.ResultGame
import com.classy.gamesinfo.data.model.gamespot.ResultReview
import com.classy.gamesinfo.databinding.GameLayoutBinding
import com.classy.gamesinfo.databinding.ReviewLayoutBinding

class GamesAdapter(private val games: ArrayList<ResultGame>) :
    RecyclerView.Adapter<GamesAdapter.BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            GameLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (holder is GamesViewHolder) {
            holder.bind(games[position])
            holder.itemView.setOnClickListener {
                games[position].expanded = !games[position].expanded
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int = games.size

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
//                itemBinding.gameRowLBLTitle.text = item.title
//                itemBinding.gameRowLBLExpandedBody.text =
//                    android.text.Html.fromHtml(item.body, HtmlCompat.FROM_HTML_MODE_LEGACY)
//                        .toString()
//                itemBinding.gameRowLBLPublished.text = "Published: ${item.publish_date}"
//                itemBinding.gameRowLBLUpdated.text = "Updated: ${item.update_date}"
//                itemBinding.gameRowLBLAuthor.text = "By ${item.authors}"
//                itemBinding.gameRowLBLScore.text = "SCORE: ${item.score} / 10.0"
//                itemBinding.gameRowLBLGoodPoints.text = "The Good:\n" + "• " + item.good.replace("|", ".\n• ") + "."
//                itemBinding.gameRowLBLBadPoints.text = "The Bad:\n" + "• " + item.bad.replace("|", ".\n• ") + "."

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
        this.games.apply {
            clear()
            addAll(games)
        }
    }

    fun addMoreGames(games: ArrayList<ResultGame>) {
        this.games.apply {
            addAll(games)
        }
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }
}