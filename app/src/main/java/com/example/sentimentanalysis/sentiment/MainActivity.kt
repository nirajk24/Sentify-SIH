package com.example.sentimentanalysis.sentiment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sentimentanalysis.AudioInputActivity
import com.example.sentimentanalysis.HashInputActivity
import com.example.sentimentanalysis.InputActivity
import com.example.sentimentanalysis.adapter.MainAdapter
import com.example.sentimentanalysis.databinding.ActivityMainBinding
import com.example.sentimentanalysis.dataclass.Demo
import com.example.sentimentanalysis.dataclass.TweetResponse
import com.example.sentimentanalysis.dataclass.TwitterAnalysis
import com.example.sentimentanalysis.mainFeaturesList
import com.example.sentimentanalysis.retrofit.RetrofitInstance
import com.example.sentimentanalysis.utility.DashboardPreferences
import com.google.gson.Gson
import okhttp3.ResponseBody
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
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
                    val intent = Intent(this, InputActivity::class.java)
                    startActivity(intent)
                }
                1 -> {
                    val intent = Intent(this, HashInputActivity::class.java)
                    startActivity(intent)
                }
                2 -> {
                    // Analytics
                }
                3 -> {

                }
                4 -> {
                    // Speech
                    val intent = Intent(this, AudioInputActivity::class.java)
                    startActivity(intent)
                }


                else -> {
                    // Do nothing
                }
            }
        }
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

    override fun onResume() {
        super.onResume()
        DashboardPreferences(this).clearAll()
    }
}