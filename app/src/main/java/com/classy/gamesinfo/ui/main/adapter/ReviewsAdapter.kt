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
import com.classy.gamesinfo.data.model.gamespot.ResultReview
import com.classy.gamesinfo.databinding.ReviewLayoutBinding

class ReviewsAdapter(private val reviews: ArrayList<ResultReview>) :
    RecyclerView.Adapter<ReviewsAdapter.BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            ReviewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (holder is ReviewsViewHolder) {
            holder.bind(reviews[position])
            holder.itemView.setOnClickListener {
                reviews[position].expanded = !reviews[position].expanded
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int = reviews.size

    inner class ReviewsViewHolder(private val itemBinding: ReviewLayoutBinding) :
        BaseViewHolder<ResultReview>(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: ResultReview) {
            itemView.apply {

                if (item.expanded) {
                    itemBinding.reviewRowExpanded.visibility = View.VISIBLE
                } else {
                    itemBinding.reviewRowExpanded.visibility = View.GONE
                }

                itemBinding.reviewRowLBLDescription.text =
                    android.text.Html.fromHtml(item.deck, HtmlCompat.FROM_HTML_MODE_LEGACY)
                        .toString()
                itemBinding.reviewRowLBLTitle.text = item.title
                itemBinding.reviewRowLBLExpandedBody.text =
                    android.text.Html.fromHtml(item.body, HtmlCompat.FROM_HTML_MODE_LEGACY)
                        .toString()
                itemBinding.reviewRowLBLPublished.text = "Published: ${item.publish_date}"
                itemBinding.reviewRowLBLUpdated.text = "Updated: ${item.update_date}"
                itemBinding.reviewRowLBLAuthor.text = "By ${item.authors}"
                itemBinding.reviewRowLBLScore.text = "SCORE: ${item.score} / 10.0"
                itemBinding.reviewRowLBLGoodPoints.text = "The Good:\n" + "• " + item.good.replace("|", ".\n• ") + "."
                itemBinding.reviewRowLBLBadPoints.text = "The Bad:\n" + "• " + item.bad.replace("|", ".\n• ") + "."

                val options: RequestOptions = RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_error)

                itemBinding.reviewRowIMGImage.layout(0, 0, 0, 0)
                Glide.with(itemBinding.reviewRowIMGImage.context).load(item.image.square_small)
                    .apply(options).into(itemBinding.reviewRowIMGImage)
            }
        }
    }

    fun addReviews(reviews: ArrayList<ResultReview>) {
        this.reviews.apply {
            addAll(reviews)
        }
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }
}