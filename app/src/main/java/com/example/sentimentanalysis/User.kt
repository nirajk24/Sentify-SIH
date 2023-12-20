package com.example.sentimentanalysis

// User.kt
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class User(private var videoID: String) : BaseObservable() {

    @Bindable
    fun getName(): String {
        return videoID
    }

    fun setName(name: String) {
        this.videoID = name
    }
}
