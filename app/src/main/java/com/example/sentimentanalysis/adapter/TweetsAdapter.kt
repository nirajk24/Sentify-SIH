package com.example.sentimentanalysis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.sentimentanalysis.R
import com.example.sentimentanalysis.constants.Constants
import com.example.sentimentanalysis.databinding.ItemTweetAnalysisBinding
import com.example.sentimentanalysis.dataclass.SimpleTweetAnalysis

class TweetsAdapter() : RecyclerView.Adapter<TweetsAdapter.MainViewHolder>() {

    lateinit var onItemClick : ((Int) -> Unit)

    class MainViewHolder(val binding : ItemTweetAnalysisBinding)
        : RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object: DiffUtil.ItemCallback<SimpleTweetAnalysis>(){  // Created an object of DiffUtil ItemCallBack class
        override fun areItemsTheSame(oldItem: SimpleTweetAnalysis, newItem: SimpleTweetAnalysis): Boolean {
            return oldItem.id == newItem.id  // returns true if item id is same
        }

        override fun areContentsTheSame(oldItem: SimpleTweetAnalysis, newItem: SimpleTweetAnalysis): Boolean {
            // check if the contents of item same
            return oldItem == newItem  // returns true if the items are same
        }
    }
    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemTweetAnalysisBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
//        return differ.currentList.size
        return 10
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
//        val currentFeature = differ.currentList[position]

//        Glide.with(holder.itemView)
//            .load(currentFeature.mainFeaturesImage)
//            .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
//            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//            .into(holder.binding.ivMainThumb)
//        holder.binding.apply {
//            tvHeading.text = currentFeature.mainFeaturesHeading
//            tvMainID.text = currentFeature.position
//            tvCatchyPhrase.text = currentFeature.mainFeaturesCatchyPhrase
//
////            val color = ColorUtils.getColorForString(pokemonType1)
////            cvMainCard.background.setTint(Color.parseColor("#FF$color"))
//        }

        if(position % 2 == 0){
            holder.binding.tvTweet.text = "This is a positive tweet with a score of 0.8 and a magnitude of 0.9."
        } else {
            holder.binding.ivTweetImage.visibility = View.GONE
        }

        val drawable = Constants.EMOTION_MAP_TO_GIF[Constants.NUMBER_MAP_TO_EMOTION[position]]

        Glide.with(holder.itemView)
            .load(drawable)
            .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holder.binding.ivEmotions)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(position)
        }

    }
}