package com.example.sentimentanalysis.retrofit

import com.example.sentimentanalysis.dataclass.SingleTweetQuery
import com.example.sentimentanalysis.dataclass.TweetResponse
import com.example.sentimentanalysis.dataclass.TwitterAnalysis
import com.example.sentimentanalysis.dataclass.URL
import com.example.sentimentanalysis.dataclass.VideoAnalysisResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {

    @POST("test")
    fun getTwitterAnalysis(@Body twitterAnalysis: TwitterAnalysis): Call<TweetResponse?>

    @POST("predict")
    fun getSingleTweetAnalysis(@Body singleTweetQuery: SingleTweetQuery): Call<TweetResponse.Tweet?>

//    @POST("test")
//    fun getTwitterAnalysis(@Body demo: Demo): Call<Demo>

    @POST("video")
    fun getVideoAnalysis(@Body url: URL): Call<VideoAnalysisResponse?>

}