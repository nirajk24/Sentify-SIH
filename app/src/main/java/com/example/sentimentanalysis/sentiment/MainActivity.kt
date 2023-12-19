package com.example.sentimentanalysis.sentiment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sentimentanalysis.InputActivity
import com.example.sentimentanalysis.adapter.MainAdapter
import com.example.sentimentanalysis.databinding.ActivityMainBinding
import com.example.sentimentanalysis.dataclass.Demo
import com.example.sentimentanalysis.dataclass.TweetResponse
import com.example.sentimentanalysis.dataclass.TwitterAnalysis
import com.example.sentimentanalysis.mainFeaturesList
import com.example.sentimentanalysis.retrofit.RetrofitInstance
import com.google.gson.Gson
import okhttp3.ResponseBody
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        setUpMainRecyclerView()

        navigateTo()
    }

    private fun navigateTo() {
        mainAdapter.onItemClick = {
            when (it) {
                0 -> {
                    // Twitter Analysis
                    val intent = Intent(this, UserDetailsActivity::class.java)
                    startActivity(intent)
                }
                1 -> {
                    // Sentence Rephrase and Generate
                    demofunction()
                }
                2 -> {
                    // Analytics
                }
                3 -> {

                }
                else -> {
                    // Do nothing
                }
            }
        }
    }

//    private fun demofunction() {
//        Toast.makeText(this, "Demo Function", Toast.LENGTH_SHORT).show()
//        val twitterAnalysis = Demo(1.5f)
//        RetrofitInstance.api.getTwitterAnalysis(twitterAnalysis).enqueue(
//            object : retrofit2.Callback<Demo> {
//                override fun onResponse(
//                    call: retrofit2.Call<Demo>,
//                    response: retrofit2.Response<Demo>
//                ) {
//                    Log.d("APIDATA", "Inside enqueue")
//                    if (response.isSuccessful) {
//                        Log.d("APIDATA", "Response Successful")
//                        val result = response.body()
//                        if (result != null) {
//                            Log.d("APIDATA", result.toString())
//                        }
//                    } else {
//                        Log.d("APIDATA", "Response Unsuccessful")
//                        val err = response.errorBody()?.string()
//                        val data = JSONObject(err.toString()).getString("error")
//                        Log.d("APIDATA", data.toString())
////                        Toast.makeText(this@MainActivity,data.toString(),Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onFailure(call: retrofit2.Call<Demo>, t: Throwable) {
//                    println(t.message)
//                    Log.d("APIDATA", "Response Failure")
//                    Log.d("APIDATA", t.message.toString())
//                }
//            }
//        )
//    }

    private fun demofunction() {
        val twitterAnalysis = TwitterAnalysis("JeffBezos", "user", 10)
        RetrofitInstance.api.getTwitterAnalysis(twitterAnalysis).enqueue(
            object : retrofit2.Callback<TweetResponse?> {
                override fun onResponse(
                    call: retrofit2.Call<TweetResponse?>,
                    response: retrofit2.Response<TweetResponse?>
                ) {
                    if (response.isSuccessful) {
//                        val result = response.body()
                        val result = response.body()
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

    private fun setUpMainRecyclerView() {
        mainAdapter = MainAdapter()
        binding.rvMain.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = mainAdapter
            setHasFixedSize(true)
        }

        mainAdapter.differ.submitList(mainFeaturesList)
    }
}