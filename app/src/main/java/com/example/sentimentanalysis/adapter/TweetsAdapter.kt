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
import com.example.sentimentanalysis.dataclass.TweetResponse
import com.example.sentimentanalysis.utility.DashboardPreferences
import kotlin.math.max
import kotlin.math.roundToInt

class TweetsAdapter() : RecyclerView.Adapter<TweetsAdapter.MainViewHolder>() {

//    lateinit var onItemClick : ((Int) -> Unit)

    class MainViewHolder(val binding : ItemTweetAnalysisBinding)
        : RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object: DiffUtil.ItemCallback<TweetResponse.Tweet>(){  // Created an object of DiffUtil ItemCallBack class
        override fun areItemsTheSame(oldItem: TweetResponse.Tweet, newItem: TweetResponse.Tweet): Boolean {
            return oldItem.link == newItem.link  // returns true if item id is same
        }

        override fun areContentsTheSame(oldItem: TweetResponse.Tweet, newItem: TweetResponse.Tweet): Boolean {
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
        return differ.currentList.size
//        return 10
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currentTweet = differ.currentList[position]

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


//        val drawable = Constants.EMOTION_MAP_TO_GIF[Constants.NUMBER_MAP_TO_EMOTION[currentTweet.sortedEmotions]

//        Glide.with(holder.itemView)
//            .load(drawable)
//            .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
//            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//            .into(holder.binding.ivEmotions)

        val maxSentiment = listOf(currentTweet.sortedSentiments.negative, currentTweet.sortedSentiments.neutral, currentTweet.sortedSentiments.positive)
        val m = maxSentiment.max()
        var sentimentDrawable = R.drawable.twitter
        when(m){
            currentTweet.sortedSentiments.negative -> {
//                sentimentDrawable =
                holder.binding.tvSentiment.text = "Negative ${currentTweet.sortedSentiments.negative.roundToInt()}"
                DashboardPreferences(holder.itemView.context).negativeCount++
            }
            currentTweet.sortedSentiments.neutral -> {
                holder.binding.tvSentiment.text = "Neutral ${currentTweet.sortedSentiments.neutral.roundToInt()}"
                DashboardPreferences(holder.itemView.context).neutralCount++
            }
            currentTweet.sortedSentiments.positive -> {
                holder.binding.tvSentiment.text = "Positive ${currentTweet.sortedSentiments.positive.roundToInt()}"
                DashboardPreferences(holder.itemView.context).positiveCount++
            }
        }

        val maxEmotion = listOf(currentTweet.sortedEmotions.anger, currentTweet.sortedEmotions.anticipation, currentTweet.sortedEmotions.disgust, currentTweet.sortedEmotions.fear, currentTweet.sortedEmotions.joy, currentTweet.sortedEmotions.love, currentTweet.sortedEmotions.optimism, currentTweet.sortedEmotions.pessimism, currentTweet.sortedEmotions.sadness, currentTweet.sortedEmotions.surprise, currentTweet.sortedEmotions.trust)
        val n = maxEmotion.max()
        var emotionDrawable = R.drawable.twitter
        when(n){
            currentTweet.sortedEmotions.anger -> {
                emotionDrawable = R.drawable.anger
                holder.binding.tvEmotions.text = "Anger ${currentTweet.sortedEmotions.anger.roundToInt()}"
                DashboardPreferences(holder.itemView.context).angerCount++
            }
            currentTweet.sortedEmotions.anticipation -> {
                emotionDrawable = R.drawable.anticipate
                holder.binding.tvEmotions.text = "Anticipation ${currentTweet.sortedEmotions.anticipation.roundToInt()}"
                DashboardPreferences(holder.itemView.context).anticipationCount++
            }
            currentTweet.sortedEmotions.disgust -> {
                emotionDrawable = R.drawable.disgust
                holder.binding.tvEmotions.text = "Disgust ${currentTweet.sortedEmotions.disgust.roundToInt()}"
                DashboardPreferences(holder.itemView.context).disgustCount++
            }
            currentTweet.sortedEmotions.fear -> {
                emotionDrawable = R.drawable.fear
                holder.binding.tvEmotions.text = "Fear ${currentTweet.sortedEmotions.fear.roundToInt()}"
                DashboardPreferences(holder.itemView.context).fearCount++
            }
            currentTweet.sortedEmotions.joy -> {
                emotionDrawable = R.drawable.joy
                holder.binding.tvEmotions.text = "Joy ${currentTweet.sortedEmotions.joy.roundToInt()}"
                DashboardPreferences(holder.itemView.context).joyCount++
            }
            currentTweet.sortedEmotions.love -> {
                emotionDrawable = R.drawable.love
                holder.binding.tvEmotions.text = "Love ${currentTweet.sortedEmotions.love.roundToInt()}"
                DashboardPreferences(holder.itemView.context).loveCount++
            }
            currentTweet.sortedEmotions.optimism -> {
                emotionDrawable = R.drawable.optimism
                holder.binding.tvEmotions.text = "Optimism ${currentTweet.sortedEmotions.optimism.roundToInt()}"
                DashboardPreferences(holder.itemView.context).optimismCount++
            }
            currentTweet.sortedEmotions.pessimism -> {
                emotionDrawable = R.drawable.pessimism
                holder.binding.tvEmotions.text = "Pessimism ${currentTweet.sortedEmotions.pessimism.roundToInt()}"
                DashboardPreferences(holder.itemView.context).pessimismCount++
            }
            currentTweet.sortedEmotions.sadness -> {
                emotionDrawable = R.drawable.sad
                holder.binding.tvEmotions.text = "Sadness ${currentTweet.sortedEmotions.sadness.roundToInt()}"
                DashboardPreferences(holder.itemView.context).sadnessCount++
            }
            currentTweet.sortedEmotions.surprise -> {
                emotionDrawable = R.drawable.surprise
                holder.binding.tvEmotions.text = "Surprise ${currentTweet.sortedEmotions.surprise.roundToInt()}"
                DashboardPreferences(holder.itemView.context).surpriseCount++
            }
            currentTweet.sortedEmotions.trust -> {
                emotionDrawable = R.drawable.trust
                holder.binding.tvEmotions.text = "Trust ${currentTweet.sortedEmotions.trust.roundToInt()}"
                DashboardPreferences(holder.itemView.context).trustCount++
            }
        }

        Glide.with(holder.itemView)
            .load(emotionDrawable)
            .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holder.binding.ivEmotions)

        val sentiment = currentTweet.sortedEmotions

        if(currentTweet.pictures.isNotEmpty()){
            holder.binding.ivTweetImage.visibility = View.VISIBLE
            Glide.with(holder.itemView)
                .load(currentTweet.pictures[0])
                .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(holder.binding.ivTweetImage)
        }
//        val emotion = currentTweet.sortedEmotions[0].emotion

        holder.binding.apply {
            tvName.text = currentTweet.user.name
            tvUserName.text = currentTweet.user.username

            tvTweet.text = currentTweet.text
            tvTweetTime.text = currentTweet.date

            tvRetweetCount.text = currentTweet.stats.retweets.toString()
            tvLikesCount.text = currentTweet.stats.likes.toString()
            tvQuotesCount.text = currentTweet.stats.quotes.toString()
            tvBookmarksCount.text = currentTweet.stats.comments.toString()

            Glide.with(holder.itemView)
                .load(currentTweet.user.avatar)
                .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(holder.binding.ivUserImage)
        }

//        holder.itemView.setOnClickListener {
//            onItemClick.invoke(position)
//        }

    }
}