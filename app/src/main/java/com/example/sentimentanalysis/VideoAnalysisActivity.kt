package com.example.sentimentanalysis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sentimentanalysis.databinding.ActivitySingleTweetAnalysisBinding
import com.example.sentimentanalysis.databinding.ActivityVideoAnalysisBinding
import com.example.sentimentanalysis.sentiment.UserDetailsActivity

class VideoAnalysisActivity : AppCompatActivity() {

    private val binding : ActivityVideoAnalysisBinding by lazy {
        ActivityVideoAnalysisBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Glide.with(this@VideoAnalysisActivity)
            .load(R.drawable.multiemotion)
//            .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(binding.ivBottomgif)

        binding.btnGo.setOnClickListener {
            if(binding.etUID.text.toString().isEmpty()){
//                binding.etUID.error="Please enter a hashtag"
                Toast.makeText(this,"Please enter a Hashtag", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                val bundle=Bundle()
                bundle.putString("url",binding.etUID.text.toString())
                val i = Intent(this@VideoAnalysisActivity, VideoViewActivity::class.java)
                i.putExtras(bundle)
                startActivity(i)
            }
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

    }
}