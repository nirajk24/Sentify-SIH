package com.example.sentimentanalysis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.anychart.APIlib
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.cartesian.series.Bar
import com.anychart.core.cartesian.series.JumpLine
import com.anychart.data.Mapping
import com.anychart.enums.HoverMode
import com.anychart.enums.TooltipDisplayMode
import com.anychart.enums.TooltipPositionMode
import com.bumptech.glide.Glide
import com.example.sentimentanalysis.databinding.ActivityVideoViewBinding
import com.example.sentimentanalysis.dataclass.URL
import com.example.sentimentanalysis.dataclass.VideoAnalysisResponse
import com.example.sentimentanalysis.dataclass.VideoURL
import com.example.sentimentanalysis.retrofit.RetrofitInstance
import java.util.regex.Pattern

class VideoViewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVideoViewBinding

    private var url = ""
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityVideoViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        url = bundle?.getString("url")!!



        Glide.with(this)
            .load(R.drawable.ic_loading)
            .into(binding.ivLoading)

        var id = ""

//        val videoURL = VideoURL(id)
//        binding.videoURL = videoURL

        val x = youtubeParser(url)
        Log.d("APIDATA", "onCreate: $x")
        if(youtubeParser(url)!= null){
            id = extractVideoId(url)!!
            id = "http://img.youtube.com/vi/$id/0.jpg"
            Glide.with(this)
                .load(id)
                .into(binding.ivVideoView)
        }

//        binding.youtubePlayerView.videoId = id

        apiCall()
    }

    private fun initializeData(response: VideoAnalysisResponse) {

        binding.ivLoading.visibility = View.GONE
        binding.tvLoadingText.visibility = View.GONE

        binding.tvSentiment.text = response.sentiment
            APIlib.getInstance().setActiveAnyChartView(binding.anyChartView)
            val vertical: Cartesian = AnyChart.vertical()

            vertical.animation(true)
                .title("Emotion Weight Chart")

            val data: MutableList<DataEntry> = ArrayList()

            for (emotionList in response.emotions) {
                for (emotion in emotionList) {
                    data.add(
                        ProfileDashboardActivity.CustomDataEntry(
                            emotion.label,
                            (emotion.score*100).toInt(),
                            0
                        )
                    )
                }
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

            binding.anyChartView.setChart(vertical);
    }

    private class CustomDataEntry(x: String?, value: Number?, jumpLine: Number?) :
        ValueDataEntry(x, value) {
        init {
            setValue("jumpLine", jumpLine)
        }
    }

    private fun apiCall() {
        val videoURL = URL(url)

        RetrofitInstance.api.getVideoAnalysis(videoURL).enqueue(object : retrofit2.Callback<VideoAnalysisResponse?>{
            override fun onResponse(call: retrofit2.Call<VideoAnalysisResponse?>, response: retrofit2.Response<VideoAnalysisResponse?>) {
                if(response.isSuccessful){
                    val response = response.body()
                    initializeData(response!!)
                }
            }

            override fun onFailure(call: retrofit2.Call<VideoAnalysisResponse?>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }

//    fun getYoutubeVideoId(youtubeUrl: String): String? {
//        val regex = Regex("""(?:https?:\/\/)?(?:www\.)?(?:youtube\.com\/(?:[^\/\n\s]+\/\S+\/|(?:v|e(?:mbed)?)\/|\S*?[?&]v=)|youtu\.be\/)([a-zA-Z0-9_-]{11})""")
//        val matchResult = regex.find(youtubeUrl)
//        return matchResult?.groups?.get(1)?.value
//    }

//    fun extractVideoId(youtubeUrl: String): String? {
//        val pattern = "([^#\\&\\?\\n]*)"
//        val compiledPattern = Pattern.compile(pattern)
//        val matcher = compiledPattern.matcher(youtubeUrl)
//        return if (matcher.find()) {
//            matcher.group(1)
//        } else {
//            null
//        }
//    }

    fun youtubeParser(url: String): String? {
        val regExp = Regex("^.*((youtu.be\\/)|(v\\/)|(\\/u\\/\\w\\/)|(embed\\/)|(watch\\?))\\??v?=?([^#&?]*).*")
        val matchResult = regExp.find(url)
        return if (matchResult != null && matchResult.groups[7]?.value?.length == 11) {
            matchResult.groups[7]?.value
        } else {
            null
        }
    }

    fun extractVideoId(youtubeUrl: String): String? {
        val indexOfEqualSign = youtubeUrl.indexOf('=')
        return if (indexOfEqualSign != -1) {
            val substringAfterEqual = youtubeUrl.substring(indexOfEqualSign + 1)
            val indexOfSlash = substringAfterEqual.indexOf('/')
            if (indexOfSlash != -1) {
                substringAfterEqual.substring(0, indexOfSlash)
            } else {
                substringAfterEqual
            }
        } else {
            null
        }
    }
}