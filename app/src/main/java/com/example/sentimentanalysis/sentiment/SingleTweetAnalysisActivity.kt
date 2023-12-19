package com.example.sentimentanalysis.sentiment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sentimentanalysis.R
import com.example.sentimentanalysis.databinding.ActivitySingleTweetAnalysisBinding

class SingleTweetAnalysisActivity : AppCompatActivity() {

    private val binding : ActivitySingleTweetAnalysisBinding by lazy {
        ActivitySingleTweetAnalysisBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}