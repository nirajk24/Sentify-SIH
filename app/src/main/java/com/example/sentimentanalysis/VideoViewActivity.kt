package com.example.sentimentanalysis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.sentimentanalysis.databinding.ActivityVideoViewBinding

class VideoViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding:ViewDataBinding = DataBindingUtil.setContentView(this@VideoViewActivity,R.layout.activity_video_view)

        val user = User("TcfEXRUQ4ZQ")
        binding.setVariable(1,user)
    }
}