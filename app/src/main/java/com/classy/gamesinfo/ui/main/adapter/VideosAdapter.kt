package com.classy.gamesinfo.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.classy.gamesinfo.R
import com.classy.gamesinfo.data.model.gamespot.ResultVideo
import com.classy.gamesinfo.databinding.VideoLayoutBinding
import java.io.IOException

class VideosAdapter(private val videos: ArrayList<ResultVideo>) :
    RecyclerView.Adapter<VideosAdapter.BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            VideoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideosViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (holder is VideosViewHolder) {
            holder.bind(videos[position])
            holder.itemView.setOnClickListener {
                if (videos[position].expanded) {

                }
                videos[position].expanded = !videos[position].expanded
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int = videos.size

    inner class VideosViewHolder(private val itemBinding: VideoLayoutBinding) :
        BaseViewHolder<ResultVideo>(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: ResultVideo) {
            itemView.apply {

                itemBinding.videoRowLBLDescription.text =
                    android.text.Html.fromHtml(item.deck, HtmlCompat.FROM_HTML_MODE_LEGACY)
                        .toString()
                itemBinding.videoRowLBLTitle.text = item.title
                itemBinding.videoRowLBLPublished.text = "Published: ${item.publish_date}"
                itemBinding.videoRowIMGVideo.setUp(item.hd_url, item.title)

                val options: RequestOptions = RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_error)

                itemBinding.videoRowIMGImage.layout(0, 0, 0, 0)
                Glide.with(itemBinding.videoRowIMGImage.context).load(item.image.square_small)
                    .apply(options).into(itemBinding.videoRowIMGImage)
            }
        }
    }

    fun addVideos(videos: ArrayList<ResultVideo>) {
        this.videos.apply {
            addAll(videos)
        }
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }
}