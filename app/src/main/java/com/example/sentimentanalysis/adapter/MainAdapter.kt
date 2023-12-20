package com.example.sentimentanalysis.adapter

import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.sentimentanalysis.MainFeature
import com.example.sentimentanalysis.databinding.MainItemBinding

class MainAdapter() : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    lateinit var onItemClick : ((Int) -> Unit)

    class MainViewHolder(val binding : MainItemBinding)
        : RecyclerView.ViewHolder(binding.root)


    private val diffUtil = object: DiffUtil.ItemCallback<MainFeature>(){  // Created an object of DiffUtil ItemCallBack class
        override fun areItemsTheSame(oldItem: MainFeature, newItem: MainFeature): Boolean {
            return oldItem.mainFeaturesHeading == newItem.mainFeaturesHeading  // returns true if item id is same
        }

        override fun areContentsTheSame(oldItem: MainFeature, newItem: MainFeature): Boolean {
            // check if the contents of item same
            return oldItem == newItem  // returns true if the items are same
        }
    }
    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            MainItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currentFeature = differ.currentList[position]

        Glide.with(holder.itemView)
            .load(currentFeature.mainFeaturesImage)
            .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holder.binding.ivMainThumb)
        holder.binding.apply {
            tvHeading.text = currentFeature.mainFeaturesHeading
            tvMainID.text = currentFeature.position
            tvCatchyPhrase.text = currentFeature.mainFeaturesCatchyPhrase

//            val color = ColorUtils.getColorForString(pokemonType1)
//            cvMainCard.background.setTint(Color.parseColor("#FF$color"))

        }

        holder.itemView.setOnClickListener {
            onItemClick.invoke(position)
        }

    }
}