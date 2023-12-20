package com.example.sentimentanalysis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sentimentanalysis.adapter.TweetsAdapter
import com.example.sentimentanalysis.databinding.ActivityHashtagAnalysisBinding
import com.example.sentimentanalysis.dataclass.TweetResponse
import com.example.sentimentanalysis.dataclass.TwitterAnalysis
import com.example.sentimentanalysis.retrofit.RetrofitInstance
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
        binding.tvHashtag.setOnClickListener {
            val intent = Intent(this, ProfileDashboardActivity::class.java)
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

                override fun onFailure(call: retrofit2.Call<TweetResponse?>, t: Throwable) {
                    println(t.message)
                    Log.d("APIDATA", "Response Failure")
                    Log.d("APIDATA", t.message.toString())
                }
            }
        )
    }

    private fun initializeData() {
        binding.ivLoading.visibility = View.GONE
        binding.mainLayout.visibility = View.VISIBLE

        binding.tvHashtag.text = hashtag

//        Glide.with(this)
//            .load(tweetResponse.tweet[0].user.avatar)
////            .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
//            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//            .into(binding.ivProfilePic)

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