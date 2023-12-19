package com.example.sentimentanalysis.retrofit

import com.example.sentimentanalysis.dataclass.Demo
import com.example.sentimentanalysis.dataclass.TweetResponse
import com.example.sentimentanalysis.dataclass.TwitterAnalysis
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {

    @POST("test")
    fun getTwitterAnalysis(@Body twitterAnalysis: TwitterAnalysis): Call<TweetResponse?>

//    @POST("test")
//    fun getTwitterAnalysis(@Body demo: Demo): Call<Demo>

}