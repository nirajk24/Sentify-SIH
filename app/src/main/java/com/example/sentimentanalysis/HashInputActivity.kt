package com.example.sentimentanalysis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.sentimentanalysis.databinding.ActivityHashInputBinding
import com.example.sentimentanalysis.sentiment.HashtagAnalysisActivity
import com.example.sentimentanalysis.utility.DashboardPreferences

class HashInputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHashInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHashInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load(R.drawable.multiemotion)
            .into(binding.ivBottomgif)

        binding.npNumber.minValue=0;
        binding.npNumber.maxValue=50
        binding.btnGo.setOnClickListener {
            val bundle=Bundle()
            bundle.putInt("number",binding.npNumber.value)
            bundle.putString("hashtag",binding.etUID.text.toString())
            val i = Intent(this@HashInputActivity, HashtagAnalysisActivity::class.java)
            startActivity(i)
        }
        binding.btnGo.setOnClickListener {
            if(binding.etUID.text.toString().isEmpty()){
//                binding.etUID.error="Please enter a hashtag"
                Toast.makeText(this,"Please enter a Hashtag",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if(binding.npNumber.value==0){
//                binding.npNumber.error="Please enter a number"
                Toast.makeText(this,"Please select a Number",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                val bundle=Bundle()
                bundle.putInt("number",binding.npNumber.value)
                bundle.putString("hashtag",binding.etUID.text.toString())
                val i = Intent(this@HashInputActivity, HashtagAnalysisActivity::class.java)
                i.putExtras(bundle)
                startActivity(i)
            }
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        DashboardPreferences(this).clearAll()
    }
}