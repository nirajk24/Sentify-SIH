package com.example.sentimentanalysis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sentimentanalysis.databinding.ActivityInputBinding
import com.example.sentimentanalysis.sentiment.UserDetailsActivity

class InputActivity : AppCompatActivity() {
    private lateinit var binding:ActivityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.npNumber.minValue=0;
        binding.npNumber.maxValue=50
        Glide.with(this@InputActivity)
            .load(R.drawable.multiemotion)
//            .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(binding.ivBottomgif)

        binding.btnGo.setOnClickListener {
            val i= Intent(this@InputActivity,UserDetailsActivity::class.java)
            startActivity(i)
            this@InputActivity.finish()
        }


    }
}