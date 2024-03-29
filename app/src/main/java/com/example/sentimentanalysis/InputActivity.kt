package com.example.sentimentanalysis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sentimentanalysis.databinding.ActivityInputBinding
import com.example.sentimentanalysis.sentiment.UserDetailsActivity
import com.example.sentimentanalysis.utility.DashboardPreferences

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
            if(binding.etUID.text.toString().isEmpty()){
//                binding.etUID.error="Please enter a hashtag"
                Toast.makeText(this,"Please enter a Hashtag", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if(binding.npNumber.value==0){
//                binding.npNumber.error="Please enter a number"
                Toast.makeText(this,"Please select a Number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                val bundle=Bundle()
                bundle.putInt("number",binding.npNumber.value)
                bundle.putString("username",binding.etUID.text.toString())
                val i = Intent(this@InputActivity,UserDetailsActivity::class.java)
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