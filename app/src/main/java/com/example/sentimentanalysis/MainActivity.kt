package com.example.sentimentanalysis

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sentimentanalysis.adapter.MainAdapter
import com.example.sentimentanalysis.databinding.ActivityMainBinding

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
                    // Twitter Sentiment - User profile, Hashtags
                }

                1 -> {
                    // Sentence Rephrase and Generate
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