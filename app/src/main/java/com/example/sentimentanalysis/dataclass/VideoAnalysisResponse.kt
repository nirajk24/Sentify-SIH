package com.example.sentimentanalysis.dataclass

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class VideoAnalysisResponse(
    @SerializedName("emotions")
    val emotions: List<List<Emotion>>,
    @SerializedName("sentiment")
    val sentiment: String
) {
    @Keep
    data class Emotion(
        @SerializedName("label")
        val label: String,
        @SerializedName("score")
        val score: Double
    )
}