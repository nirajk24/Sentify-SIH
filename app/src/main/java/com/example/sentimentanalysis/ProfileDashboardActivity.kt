package com.example.sentimentanalysis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anychart.APIlib
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.cartesian.series.Bar
import com.anychart.core.cartesian.series.JumpLine
import com.anychart.data.Mapping
import com.anychart.enums.Align
import com.anychart.enums.HoverMode
import com.anychart.enums.LegendLayout
import com.anychart.enums.TooltipDisplayMode
import com.anychart.enums.TooltipPositionMode
import com.example.sentimentanalysis.databinding.ActivityProfileDashboardBinding
import com.example.sentimentanalysis.dataclass.TweetResponse
import com.example.sentimentanalysis.utility.DashboardPreferences
import com.google.gson.Gson

class ProfileDashboardActivity : AppCompatActivity() {

    private val binding : ActivityProfileDashboardBinding by lazy {
        ActivityProfileDashboardBinding.inflate(layoutInflater)
    }

    private lateinit var tweetResponse: TweetResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val gson = Gson()
        val tweetJson = intent.getStringExtra("TWEET_RESPONSE_KEY")
        tweetResponse = gson.fromJson(tweetJson, TweetResponse::class.java)

        setUpSentimentDistributionChart()
        setUpEmotionDistributionChart()
       setUpEmotionDistributionChart()

        setUpEmotionWeightChart()
        setUpSentimentWeightChart()

        onBackClick()
    }

    private fun setUpSentimentWeightChart() {
//        binding.emotionsBarChart.setProgressBar(findViewById(R.id.progress_bar));

        APIlib.getInstance().setActiveAnyChartView(binding.sentimentBarChart);
        val vertical: Cartesian = AnyChart.vertical()

        vertical.animation(true)
            .title("Sentiment Weight Chart")

        val data: MutableList<DataEntry> = ArrayList()

        data.add(CustomDataEntry("Positive", tweetResponse.user.sentiment.positive.toInt(), 0))
        data.add(CustomDataEntry("Negative", tweetResponse.user.sentiment.negative.toInt(), 0))
        data.add(CustomDataEntry("Neutral", tweetResponse.user.sentiment.neutral.toInt(), 0))
//
//        data.add(CustomDataEntry("Positive", 10, 0))
//        data.add(CustomDataEntry("Negative", 12, 0))
//        data.add(CustomDataEntry("Neutral", 11, 0))


        val set: com.anychart.data.Set = com.anychart.data.Set.instantiate()
        set.data(data) // Use the data list here
        val barData: Mapping = set.mapAs("{ x: 'x', value: 'value' }")
        val jumpLineData: Mapping = set.mapAs("{ x: 'x', value: 'jumpLine' }")

        val bar: Bar = vertical.bar(barData)
        bar.labels().format("{%Value}%")

        val jumpLine: JumpLine = vertical.jumpLine(jumpLineData)
        jumpLine.stroke("2 #60727B")
        jumpLine.labels().enabled(false)

        vertical.yScale().minimum(0.0)

        vertical.labels(true)

        vertical.tooltip()
            .displayMode(TooltipDisplayMode.UNION)
            .positionMode(TooltipPositionMode.POINT)
            .unionFormat(
                """function() {
      return 'Plain: ' + this.points[1].value + '% ' +
        '\n' + 'Fact: ' + this.points[0].value + '% ';
    }"""
            )

        vertical.interactivity().hoverMode(HoverMode.BY_X)

        vertical.xAxis(true)
        vertical.yAxis(true)
        vertical.yAxis(0).labels().format("{%Value}%")

        vertical.background().fill("#121212 1.0")

        binding.sentimentBarChart.setChart(vertical);
    }

    private fun setUpEmotionWeightChart() {
//        binding.emotionsBarChart.setProgressBar(findViewById(R.id.progress_bar));

        APIlib.getInstance().setActiveAnyChartView(binding.emotionsBarChart);
        val vertical: Cartesian = AnyChart.vertical()

        vertical.animation(true)
            .title("Emotion Weight Chart")

        val data: MutableList<DataEntry> = ArrayList()

        if(tweetResponse.user.emotions.anger.toInt() != 0) {
            data.add(CustomDataEntry("Anger", tweetResponse.user.emotions.anger.toInt(), 0))
        }
        if(tweetResponse.user.emotions.disgust.toInt() != 0) {
            data.add(CustomDataEntry("Disgust", tweetResponse.user.emotions.disgust.toInt(), 0))
        }
        if(tweetResponse.user.emotions.fear.toInt() != 0) {
            data.add(CustomDataEntry("Fear", tweetResponse.user.emotions.fear.toInt(), 0))
        }
        if(tweetResponse.user.emotions.joy.toInt() != 0) {
            data.add(CustomDataEntry("Joy", tweetResponse.user.emotions.joy.toInt(), 0))
        }
        if(tweetResponse.user.emotions.sadness.toInt() != 0) {
            data.add(CustomDataEntry("Sadness", tweetResponse.user.emotions.sadness.toInt(), 0))
        }
        if(tweetResponse.user.emotions.surprise.toInt() != 0) {
            data.add(CustomDataEntry("Surprise", tweetResponse.user.emotions.surprise.toInt(), 0))
        }
        if(tweetResponse.user.emotions.optimism.toInt() != 0) {
            data.add(CustomDataEntry("Optimism", tweetResponse.user.emotions.optimism.toInt(), 0))
        }
        if(tweetResponse.user.emotions.pessimism.toInt() != 0) {
            data.add(CustomDataEntry("Pessimism", tweetResponse.user.emotions.pessimism.toInt(), 0))
        }
        if(tweetResponse.user.emotions.trust.toInt() != 0) {
            data.add(CustomDataEntry("Trust", tweetResponse.user.emotions.trust.toInt(), 0))
        }
        if(tweetResponse.user.emotions.anticipation.toInt() != 0) {
            data.add(CustomDataEntry("Anticipation", tweetResponse.user.emotions.anticipation.toInt(), 0))
        }
        if(tweetResponse.user.emotions.love.toInt() != 0) {
            data.add(CustomDataEntry("Love", tweetResponse.user.emotions.love.toInt(), 0))
        }

        val set: com.anychart.data.Set = com.anychart.data.Set.instantiate()
        set.data(data) // Use the data list here
        val barData: Mapping = set.mapAs("{ x: 'x', value: 'value' }")
        val jumpLineData: Mapping = set.mapAs("{ x: 'x', value: 'jumpLine' }")

        val bar: Bar = vertical.bar(barData)
        bar.labels().format("{%Value}%")

        val jumpLine: JumpLine = vertical.jumpLine(jumpLineData)
        jumpLine.stroke("2 #60727B")
        jumpLine.labels().enabled(false)

        vertical.yScale().minimum(0.0)

        vertical.labels(true)

        vertical.tooltip()
            .displayMode(TooltipDisplayMode.UNION)
            .positionMode(TooltipPositionMode.POINT)
            .unionFormat(
                """function() {
                    return 'Plain: ' + this.points[1].value + '% ' +
                    '\n' + 'Fact: ' + this.points[0].value + '% ';
                }"""
            )

        vertical.interactivity().hoverMode(HoverMode.BY_X)

        vertical.xAxis(true)
        vertical.yAxis(true)
        vertical.yAxis(0).labels().format("{%Value}%")

        vertical.background().fill("#121212 1.0")

        binding.emotionsBarChart.setChart(vertical);
    }

    private fun setUpEmotionDistributionChart() {
        APIlib.getInstance().setActiveAnyChartView(binding.emotionDistributionPieChart);
        val pie = AnyChart.pie()
        val data: MutableList<DataEntry> = ArrayList()
        if(DashboardPreferences(this).angerCount != 0) {
            data.add(ValueDataEntry("Anger", DashboardPreferences(this).angerCount))
        }
        if(DashboardPreferences(this).disgustCount != 0) {
            data.add(ValueDataEntry("Disgust", DashboardPreferences(this).disgustCount))
        }
        if(DashboardPreferences(this).fearCount != 0) {
            data.add(ValueDataEntry("Fear", DashboardPreferences(this).fearCount))
        }
        if(DashboardPreferences(this).joyCount != 0) {
            data.add(ValueDataEntry("Joy", DashboardPreferences(this).joyCount))
        }
        if(DashboardPreferences(this).sadnessCount != 0) {
            data.add(ValueDataEntry("Sadness", DashboardPreferences(this).sadnessCount))
        }
        if(DashboardPreferences(this).surpriseCount != 0) {
            data.add(ValueDataEntry("Surprise", DashboardPreferences(this).surpriseCount))
        }
        if(DashboardPreferences(this).optimismCount != 0) {
            data.add(ValueDataEntry("Optimism", DashboardPreferences(this).optimismCount))
        }
        if(DashboardPreferences(this).pessimismCount != 0) {
            data.add(ValueDataEntry("Pessimism", DashboardPreferences(this).pessimismCount))
        }
        if(DashboardPreferences(this).trustCount != 0) {
            data.add(ValueDataEntry("Trust", DashboardPreferences(this).trustCount))
        }
        if(DashboardPreferences(this).anticipationCount != 0) {
            data.add(ValueDataEntry("Anticipation", DashboardPreferences(this).anticipationCount))
        }
        if(DashboardPreferences(this).loveCount != 0) {
            data.add(ValueDataEntry("Love", DashboardPreferences(this).loveCount))
        }
        pie.data(data)
        pie.title("Emotion Distribution")
        pie.labels().position("outside")
        pie.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.HORIZONTAL)
            .align(Align.CENTER)

        pie.background().fill("#121212 1.0")

        binding.emotionDistributionPieChart.setChart(pie)
    }

    private fun setUpSentimentDistributionChart() {

        APIlib.getInstance().setActiveAnyChartView(binding.sentimentDistributionPieChart);
        val pie = AnyChart.pie()
        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("Positive", DashboardPreferences(this).positiveCount))
        data.add(ValueDataEntry("Negative", DashboardPreferences(this).negativeCount))
        data.add(ValueDataEntry("Neutral", DashboardPreferences(this).neutralCount))

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

        binding.sentimentDistributionPieChart.setChart(pie)
    }


    class CustomDataEntry(x: String?, value: Number?, jumpLine: Number?) :
        ValueDataEntry(x, value) {
        init {
            setValue("jumpLine", jumpLine)
        }
    }


    private fun onBackClick() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }
}