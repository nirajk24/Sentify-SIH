package com.example.sentimentanalysis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout
import com.example.sentimentanalysis.databinding.ActivityProfileDashboardBinding
import com.example.sentimentanalysis.utility.DashboardPreferences


class ProfileDashboardActivity : AppCompatActivity() {

    private val binding : ActivityProfileDashboardBinding by lazy {
        ActivityProfileDashboardBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpSentimentDistributionChart()

       setUpEmotionDistributionChart()

        onBackClick()
    }

    private fun setUpEmotionDistributionChart() {
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
        binding.EmotionDistributionPieChart.setChart(pie)

    }

    private fun setUpSentimentDistributionChart() {
        val pie = AnyChart.pie()
        val data: MutableList<DataEntry> = ArrayList()
//
        data.add(ValueDataEntry("Positive", DashboardPreferences(this).positiveCount))
        data.add(ValueDataEntry("Negative", DashboardPreferences(this).negativeCount))
        data.add(ValueDataEntry("Neutral", DashboardPreferences(this).neutralCount))

        pie.data(data)

        pie.title("Sentiment Distribution")

        pie.labels().position("outside")

//        pie.legend().title().enabled(true)
//        pie.legend().title()
//            .text("Retail channels")
//            .padding(0.0, 0.0, 10.0, 0.0)

        pie.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.HORIZONTAL)
            .align(Align.CENTER)

        binding.sentimentDistributionPieChart.setChart(pie)
    }


    private fun onBackClick() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }
}