package com.example.sentimentanalysis.sentiment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.sentimentanalysis.ProfileDashboardActivity
import com.example.sentimentanalysis.R
import com.example.sentimentanalysis.adapter.TweetsAdapter
import com.example.sentimentanalysis.databinding.ActivityHashtagAnalysisBinding
import com.example.sentimentanalysis.dataclass.TweetResponse
import com.example.sentimentanalysis.dataclass.TwitterAnalysis
import com.example.sentimentanalysis.retrofit.RetrofitInstance
import com.google.gson.Gson
import org.json.JSONObject

class HashtagAnalysisActivity : AppCompatActivity() {

    private val binding : ActivityHashtagAnalysisBinding by lazy {
        ActivityHashtagAnalysisBinding.inflate(layoutInflater)
    }

    private lateinit var tweetsAdapter: TweetsAdapter
    private lateinit var tweetResponse: TweetResponse

    private var hashtag = ""
    private var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Glide.with(this)
            .load(R.drawable.ic_loading)
            .into(binding.ivLoading)

        val bundle = intent.extras
        if (bundle != null) {
            hashtag = bundle.getString("hashtag")!!
            number = bundle.getInt("number")
        }

        setRecyclerView()
        onBackClick()

        apiCall()
        goToDashboard()
    }

    private fun goToDashboard() {
        binding.btnToDashBoard.setOnClickListener {
            val intent = Intent(this, ProfileDashboardActivity::class.java)
            val gson = Gson()
            val tweetJson = gson.toJson(tweetResponse)
            intent.putExtra("TWEET_RESPONSE_KEY", tweetJson)
            startActivity(intent)
        }
    }

    private fun apiCall() {
        val twitterAnalysis = TwitterAnalysis(hashtag, "hashtag", number)
        RetrofitInstance.api.getTwitterAnalysis(twitterAnalysis).enqueue(
            object : retrofit2.Callback<TweetResponse?> {
                override fun onResponse(
                    call: retrofit2.Call<TweetResponse?>,
                    response: retrofit2.Response<TweetResponse?>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
//                        val result = response.body()
                        tweetResponse = response.body()!!
                        initializeData()
                        if (result != null) {
                            Log.d("APIDATA", result.toString())
                        }
                    } else {
//                        val err = response.errorBody()?.string()
//                        val data = JSONObject(err.toString()).getString("error")
//                        Log.d("APIDATA", data.toString())
                        Toast.makeText(this@HashtagAnalysisActivity, response.message().toString(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: retrofit2.Call<TweetResponse?>, t: Throwable) {
                    Log.d("APIDATA", "Response Failure")
                    Log.d("APIDATA", t.message.toString())
                }
            }
        )
    }

    private fun initializeData() {
        binding.ivLoading.visibility = View.GONE
        binding.tvLoadingText.visibility = View.GONE
        binding.mainLayout.visibility = View.VISIBLE

        binding.apply {
            tvHashtag.text = "Hastag Used : #$hashtag"
            tvNumberOfTweets.text = "Number of Tweets : ${number}"
        }
        tweetsAdapter.differ.submitList(tweetResponse.tweet)
    }

    private fun setRecyclerView() {
        tweetsAdapter = TweetsAdapter()
        binding.rvTweets.apply {
            adapter = tweetsAdapter
            layoutManager = LinearLayoutManager(this@HashtagAnalysisActivity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    private fun onBackClick() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

}