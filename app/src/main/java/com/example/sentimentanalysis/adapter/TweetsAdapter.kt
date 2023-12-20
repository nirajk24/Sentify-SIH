package com.example.sentimentanalysis.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anychart.APIlib
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout
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

        holder.binding.ivTweetImage.visibility = View.GONE

        val maxSentiment = listOf(currentTweet.sortedSentiments.negative, currentTweet.sortedSentiments.neutral, currentTweet.sortedSentiments.positive)
        val m = maxSentiment.max()
        var sentimentDrawable = ColorDrawable(Color.RED)
        when(m){
            currentTweet.sortedSentiments.negative -> {
                holder.binding.tvSentiment.text = "Negative ${currentTweet.sortedSentiments.negative.roundToInt()}%"
                DashboardPreferences(holder.itemView.context).negativeCount++
                sentimentDrawable = ColorDrawable(Color.RED)
            }
            currentTweet.sortedSentiments.neutral -> {
                holder.binding.tvSentiment.text = "Neutral ${currentTweet.sortedSentiments.neutral.roundToInt()}%"
                DashboardPreferences(holder.itemView.context).neutralCount++
                sentimentDrawable = ColorDrawable(Color.GRAY)
            }
            currentTweet.sortedSentiments.positive -> {
                holder.binding.tvSentiment.text = "Positive ${currentTweet.sortedSentiments.positive.roundToInt()}%"
                DashboardPreferences(holder.itemView.context).positiveCount++
                sentimentDrawable = ColorDrawable(Color.GREEN)
            }
        }

        holder.binding.ivSentiment.setImageDrawable(sentimentDrawable)

        val maxEmotion = listOf(currentTweet.sortedEmotions.anger, currentTweet.sortedEmotions.anticipation, currentTweet.sortedEmotions.disgust, currentTweet.sortedEmotions.fear, currentTweet.sortedEmotions.joy, currentTweet.sortedEmotions.love, currentTweet.sortedEmotions.optimism, currentTweet.sortedEmotions.pessimism, currentTweet.sortedEmotions.sadness, currentTweet.sortedEmotions.surprise, currentTweet.sortedEmotions.trust)
        val n = maxEmotion.max()
        var emotionDrawable = R.drawable.twitter
        when(n){
            currentTweet.sortedEmotions.anger -> {
                emotionDrawable = R.drawable.anger
                holder.binding.tvEmotions.text = "Anger ${currentTweet.sortedEmotions.anger.roundToInt()}%"
                DashboardPreferences(holder.itemView.context).angerCount++
            }
            currentTweet.sortedEmotions.anticipation -> {
                emotionDrawable = R.drawable.anticipate
                holder.binding.tvEmotions.text = "Anticipation ${currentTweet.sortedEmotions.anticipation.roundToInt()}%"
                DashboardPreferences(holder.itemView.context).anticipationCount++
            }
            currentTweet.sortedEmotions.disgust -> {
                emotionDrawable = R.drawable.disgust
                holder.binding.tvEmotions.text = "Disgust ${currentTweet.sortedEmotions.disgust.roundToInt()}%"
                DashboardPreferences(holder.itemView.context).disgustCount++
            }
            currentTweet.sortedEmotions.fear -> {
                emotionDrawable = R.drawable.fear
                holder.binding.tvEmotions.text = "Fear ${currentTweet.sortedEmotions.fear.roundToInt()}%"
                DashboardPreferences(holder.itemView.context).fearCount++
            }
            currentTweet.sortedEmotions.joy -> {
                emotionDrawable = R.drawable.joy
                holder.binding.tvEmotions.text = "Joy ${currentTweet.sortedEmotions.joy.roundToInt()}%"
                DashboardPreferences(holder.itemView.context).joyCount++
            }
            currentTweet.sortedEmotions.love -> {
                emotionDrawable = R.drawable.love
                holder.binding.tvEmotions.text = "Love ${currentTweet.sortedEmotions.love.roundToInt()}%"
                DashboardPreferences(holder.itemView.context).loveCount++
            }
            currentTweet.sortedEmotions.optimism -> {
                emotionDrawable = R.drawable.optimism
                holder.binding.tvEmotions.text = "Optimism ${currentTweet.sortedEmotions.optimism.roundToInt()}%"
                DashboardPreferences(holder.itemView.context).optimismCount++
            }
            currentTweet.sortedEmotions.pessimism -> {
                emotionDrawable = R.drawable.pessimism
                holder.binding.tvEmotions.text = "Pessimism ${currentTweet.sortedEmotions.pessimism.roundToInt()}%"
                DashboardPreferences(holder.itemView.context).pessimismCount++
            }
            currentTweet.sortedEmotions.sadness -> {
                emotionDrawable = R.drawable.sad
                holder.binding.tvEmotions.text = "Sadness ${currentTweet.sortedEmotions.sadness.roundToInt()}%"
                DashboardPreferences(holder.itemView.context).sadnessCount++
            }
            currentTweet.sortedEmotions.surprise -> {
                emotionDrawable = R.drawable.surprise
                holder.binding.tvEmotions.text = "Surprise ${currentTweet.sortedEmotions.surprise.roundToInt()}%"
                DashboardPreferences(holder.itemView.context).surpriseCount++
            }
            currentTweet.sortedEmotions.trust -> {
                emotionDrawable = R.drawable.trust
                holder.binding.tvEmotions.text = "Trust ${currentTweet.sortedEmotions.trust.roundToInt()}%"
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
                .into(holder.binding.ivUserImage)
        }

//        holder.itemView.setOnClickListener {
//            onItemClick.invoke(position)
//        }

        setUpBarChart(currentTweet, holder)
        setUpTopThreeEmotions(currentTweet, holder)

        holder.binding.ivDropDown.setOnClickListener(){
            if(holder.binding.detailedAnalysis.visibility == View.GONE){
                holder.binding.detailedAnalysis.visibility = View.VISIBLE
                holder.binding.ivDropDown.setImageResource(R.drawable.ic_arrow_drop_up)
            }else{
                holder.binding.detailedAnalysis.visibility = View.GONE
                holder.binding.ivDropDown.setImageResource(R.drawable.ic_arrow_drop_down)
            }
        }
    }

    private fun setUpTopThreeEmotions(currentTweet: TweetResponse.Tweet, holder: TweetsAdapter.MainViewHolder) {
        val topThreeEmotion = extractTop3Emotions(currentTweet.sortedEmotions)

        holder.binding.apply {
            tvEmotion1.text = topThreeEmotion[0].first
            tvEmotion2.text = topThreeEmotion[1].first
            tvEmotion3.text = topThreeEmotion[2].first

            Glide.with(holder.itemView)
                .load(Constants.EMOTION_MAP_TO_GIF_CAPS[(topThreeEmotion[0].first)])
                .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(ivEmoji1)

            Glide.with(holder.itemView)
                .load(Constants.EMOTION_MAP_TO_GIF_CAPS[(topThreeEmotion[1].first)])
                .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(ivEmoji2)

            Glide.with(holder.itemView)
                .load(Constants.EMOTION_MAP_TO_GIF_CAPS[(topThreeEmotion[2].first)])
                .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(ivEmoji3)

            pb1.setProgress(topThreeEmotion[0].second.roundToInt(), true)
            pb2.setProgress(topThreeEmotion[1].second.roundToInt(), true)
            pb3.setProgress(topThreeEmotion[2].second.roundToInt(), true)
        }
    }

    private fun setUpBarChart(currentTweet: TweetResponse.Tweet, holder: TweetsAdapter.MainViewHolder) {
        APIlib.getInstance().setActiveAnyChartView(holder.binding.sentimentBarChart);
        val pie = AnyChart.pie()
        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("Positive", currentTweet.sortedSentiments.positive))
        data.add(ValueDataEntry("Negative", currentTweet.sortedSentiments.negative))
        data.add(ValueDataEntry("Neutral", currentTweet.sortedSentiments.neutral))

//        data.add(ValueDataEntry("Positive", 10))
//        data.add(ValueDataEntry("Negative", 20))
//        data.add(ValueDataEntry("Neutral", 15))

        pie.data(data)

        pie.title("Sentiment Distribution")
        pie.labels().position("inside")

        pie.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.HORIZONTAL)
            .align(Align.CENTER)

        pie.background().fill("#3C3C3C 1.0")

        holder.binding.sentimentBarChart.setChart(pie)
    }

    private fun extractTop3Emotions(emotions: TweetResponse.Tweet.SortedEmotions): Array<Pair<String, Double>> {
        // Create a map of emotion names to their corresponding values
        val emotionMap = mapOf(
            "Anger" to emotions.anger,
            "Anticipation" to emotions.anticipation,
            "Disgust" to emotions.disgust,
            "Fear" to emotions.fear,
            "Joy" to emotions.joy,
            "Love" to emotions.love,
            "Optimism" to emotions.optimism,
            "Pessimism" to emotions.pessimism,
            "Sadness" to emotions.sadness,
            "Surprise" to emotions.surprise,
            "Trust" to emotions.trust
        )

        // Sort the map by values in descending order
        val sortedEmotions = emotionMap.entries.sortedByDescending { it.value }

        // Take the top 3 emotions and convert Map.Entry instances to Pairs
        val top3Emotions = sortedEmotions.take(3).map { it.key to it.value }.toTypedArray()

        return top3Emotions
    }
}