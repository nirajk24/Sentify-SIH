package com.example.sentimentanalysis.sentiment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
import com.example.sentimentanalysis.databinding.ActivitySingleTweetAnalysisBinding
import com.example.sentimentanalysis.dataclass.SingleTweetQuery
import com.example.sentimentanalysis.dataclass.TweetResponse
import com.example.sentimentanalysis.retrofit.RetrofitInstance
import org.json.JSONObject
import kotlin.math.roundToInt

class SingleTweetAnalysisActivity : AppCompatActivity() {

    private val binding : ActivitySingleTweetAnalysisBinding by lazy {
        ActivitySingleTweetAnalysisBinding.inflate(layoutInflater)
    }

    private lateinit var currentTweet : TweetResponse.Tweet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Glide.with(this)
            .load(R.drawable.multiemotion)
            .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
            .into(binding.ivBottomgif)

        binding.btnAnalyze.setOnClickListener(){
            if(binding.etTweet.text.toString().isEmpty()){
                binding.etTweet.error = "Please enter a tweet!"
                return@setOnClickListener
            } else {
                callApi()
            }
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun callApi() {
        binding.apply {
            ivBottomgif.visibility = View.VISIBLE
            analysisLayout.visibility = View.GONE
        }
        val tweet = binding.etTweet.text.toString()
        val singleTweetQuery = SingleTweetQuery(tweet)

        Log.d("APIDATA", singleTweetQuery.toString())

        RetrofitInstance.api.getSingleTweetAnalysis(singleTweetQuery).enqueue(
            object : retrofit2.Callback<TweetResponse.Tweet?> {
                override fun onResponse(
                    call: retrofit2.Call<TweetResponse.Tweet?>,
                    response: retrofit2.Response<TweetResponse.Tweet?>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
//                        val result = response.body()
                        currentTweet = response.body()!!
                        initializeData()
                        if (result != null) {
                            println(result)
                            Log.d("APIDATA", result.toString())
                        }
                    } else {
                        val err = response.errorBody()?.string()
                        val data = JSONObject(err.toString()).getString("error")
                        Log.d("APIDATA", data.toString())
//                        Toast.makeText(this@MainActivity,data.toString(),Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: retrofit2.Call<TweetResponse.Tweet?>, t: Throwable) {
                    Log.d("APIDATA", "Response Failure")
                    Log.d("APIDATA", t.message.toString())
                }
            }
        )
    }

    private fun initializeData() {
        binding.apply {
            ivBottomgif.visibility = View.GONE
            analysisLayout.visibility = View.VISIBLE
        }

        val maxEmotion = listOf(currentTweet.sortedEmotions.anger, currentTweet.sortedEmotions.anticipation, currentTweet.sortedEmotions.disgust, currentTweet.sortedEmotions.fear, currentTweet.sortedEmotions.joy, currentTweet.sortedEmotions.love, currentTweet.sortedEmotions.optimism, currentTweet.sortedEmotions.pessimism, currentTweet.sortedEmotions.sadness, currentTweet.sortedEmotions.surprise, currentTweet.sortedEmotions.trust)
        val n = maxEmotion.max()
        var emotionDrawable = R.drawable.twitter
        when(n){
            currentTweet.sortedEmotions.anger -> {
                emotionDrawable = R.drawable.anger
                binding.tvEmotions.text = "Anger ${currentTweet.sortedEmotions.anger.roundToInt()}"
            }
            currentTweet.sortedEmotions.anticipation -> {
                emotionDrawable = R.drawable.anticipate
                binding.tvEmotions.text = "Anticipation ${currentTweet.sortedEmotions.anticipation.roundToInt()}"
            }
            currentTweet.sortedEmotions.disgust -> {
                emotionDrawable = R.drawable.disgust
                binding.tvEmotions.text = "Disgust ${currentTweet.sortedEmotions.disgust.roundToInt()}"
            }
            currentTweet.sortedEmotions.fear -> {
                emotionDrawable = R.drawable.fear
                binding.tvEmotions.text = "Fear ${currentTweet.sortedEmotions.fear.roundToInt()}"
            }
            currentTweet.sortedEmotions.joy -> {
                emotionDrawable = R.drawable.joy
                binding.tvEmotions.text = "Joy ${currentTweet.sortedEmotions.joy.roundToInt()}"
            }
            currentTweet.sortedEmotions.love -> {
                emotionDrawable = R.drawable.love
                binding.tvEmotions.text = "Love ${currentTweet.sortedEmotions.love.roundToInt()}"
            }
            currentTweet.sortedEmotions.optimism -> {
                emotionDrawable = R.drawable.optimism
                binding.tvEmotions.text = "Optimism ${currentTweet.sortedEmotions.optimism.roundToInt()}"
            }
            currentTweet.sortedEmotions.pessimism -> {
                emotionDrawable = R.drawable.pessimism
                binding.tvEmotions.text = "Pessimism ${currentTweet.sortedEmotions.pessimism.roundToInt()}"
            }
            currentTweet.sortedEmotions.sadness -> {
                emotionDrawable = R.drawable.sad
                binding.tvEmotions.text = "Sadness ${currentTweet.sortedEmotions.sadness.roundToInt()}"
            }
            currentTweet.sortedEmotions.surprise -> {
                emotionDrawable = R.drawable.surprise
                binding.tvEmotions.text = "Surprise ${currentTweet.sortedEmotions.surprise.roundToInt()}"
            }
            currentTweet.sortedEmotions.trust -> {
                emotionDrawable = R.drawable.trust
                binding.tvEmotions.text = "Trust ${currentTweet.sortedEmotions.trust.roundToInt()}"
            }
        }

        Glide.with(this)
            .load(emotionDrawable)
            .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(binding.ivEmotions)

        val maxSentiment = listOf(currentTweet.sortedSentiments.negative, currentTweet.sortedSentiments.neutral, currentTweet.sortedSentiments.positive)
        val m = maxSentiment.max()
        var sentimentDrawable = ColorDrawable(Color.RED)
        when(m){
            currentTweet.sortedSentiments.negative -> {
                binding.tvSentiment.text = "Negative ${currentTweet.sortedSentiments.negative.roundToInt()}"
                sentimentDrawable = ColorDrawable(Color.RED)
            }
            currentTweet.sortedSentiments.neutral -> {
                binding.tvSentiment.text = "Neutral ${currentTweet.sortedSentiments.neutral.roundToInt()}"
                sentimentDrawable = ColorDrawable(Color.GRAY)
            }
            currentTweet.sortedSentiments.positive -> {
                binding.tvSentiment.text = "Positive ${currentTweet.sortedSentiments.positive.roundToInt()}"
                sentimentDrawable = ColorDrawable(Color.GREEN)
            }
        }

        binding.ivSentiment.setImageDrawable(sentimentDrawable)

        setUpBarChart()
        setUpTopThreeEmotions()
    }

    private fun setUpTopThreeEmotions() {
        val topThreeEmotion = extractTop3Emotions(currentTweet.sortedEmotions)

        binding.tvEmotion1.text = topThreeEmotion[0].first
        binding.tvEmotion2.text = topThreeEmotion[1].first
        binding.tvEmotion3.text = topThreeEmotion[2].first

        binding.apply {
            Glide.with(this@SingleTweetAnalysisActivity)
                .load(Constants.EMOTION_MAP_TO_GIF_CAPS[(topThreeEmotion[0].first)])
                .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(ivEmoji1)

            Glide.with(this@SingleTweetAnalysisActivity)
                .load(Constants.EMOTION_MAP_TO_GIF_CAPS[(topThreeEmotion[1].first)])
                .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(ivEmoji2)

            Glide.with(this@SingleTweetAnalysisActivity)
                .load(Constants.EMOTION_MAP_TO_GIF_CAPS[(topThreeEmotion[2].first)])
                .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(ivEmoji3)

            pb1.setProgress(topThreeEmotion[0].second.roundToInt(), true)
            pb2.setProgress(topThreeEmotion[1].second.roundToInt(), true)
            pb3.setProgress(topThreeEmotion[2].second.roundToInt(), true)
        }
    }

    private fun setUpBarChart() {
        APIlib.getInstance().setActiveAnyChartView(binding.sentimentBarChart);
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
        pie.labels().position("outside")

        pie.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.HORIZONTAL)
            .align(Align.CENTER)

        pie.background().fill("#121212 1.0")

        binding.sentimentBarChart.setChart(pie)
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