package com.example.sentimentanalysis.retrofit

import com.example.sentimentanalysis.dataclass.SingleTweetQuery
import com.example.sentimentanalysis.dataclass.TweetResponse
import com.example.sentimentanalysis.dataclass.TwitterAnalysis
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

}