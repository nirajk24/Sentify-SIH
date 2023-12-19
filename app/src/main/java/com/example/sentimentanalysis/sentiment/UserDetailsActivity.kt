package com.example.sentimentanalysis.sentiment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sentimentanalysis.R
import com.example.sentimentanalysis.databinding.ActivityUserDetailsBinding

class UserDetailsActivity : AppCompatActivity() {

    val binding : ActivityUserDetailsBinding by lazy {
        ActivityUserDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)




    }



}