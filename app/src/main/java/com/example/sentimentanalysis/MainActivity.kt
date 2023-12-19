package com.example.sentimentanalysis

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
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

    }

    private fun setUpMainRecyclerView() {
        mainAdapter = MainAdapter()
        binding.rvMain.apply {
            adapter = mainAdapter
            setHasFixedSize(true)
        }

        mainAdapter.differ.submitList(mainFeaturesList)
    }
}