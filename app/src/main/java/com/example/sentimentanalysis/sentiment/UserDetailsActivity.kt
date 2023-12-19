package com.example.sentimentanalysis.sentiment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sentimentanalysis.R
import com.example.sentimentanalysis.adapter.TweetsAdapter
import com.example.sentimentanalysis.databinding.ActivityUserDetailsBinding

class UserDetailsActivity : AppCompatActivity() {

    val binding : ActivityUserDetailsBinding by lazy {
        ActivityUserDetailsBinding.inflate(layoutInflater)
    }

    private lateinit var tweetsAdapter: TweetsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setRecyclerView()
        onBackClick()

    }

    private fun onBackClick() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setRecyclerView() {
        tweetsAdapter = TweetsAdapter()
        binding.rvTweets.apply {
            adapter = tweetsAdapter
            layoutManager = LinearLayoutManager(this@UserDetailsActivity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }


}