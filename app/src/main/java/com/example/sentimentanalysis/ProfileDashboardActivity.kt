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