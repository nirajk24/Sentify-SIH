package com.example.sentimentanalysis.constants

import com.example.sentimentanalysis.R

object Constants {
    val EMOTION_MAP_TO_GIF = mapOf<String, Int>(
        "anger" to R.drawable.anger,
        "fear" to R.drawable.fear,
        "joy" to R.drawable.joy,
        "love" to R.drawable.love,
        "sadness" to R.drawable.sad,
        "surprise" to R.drawable.surprise,
        "optimism" to R.drawable.optimism,
        "pessimism" to R.drawable.pessimism,
        "anticipation" to R.drawable.anticipate,
        "trust" to R.drawable.trust,
        "disgust" to R.drawable.disgust
    )

    val NUMBER_MAP_TO_EMOTION = mapOf<Int, String>(
        0 to "anger",
        1 to "fear",
        2 to "joy",
        3 to "love",
        4 to "sadness",
        5 to "surprise",
        6 to "optimism",
        7 to "pessimism",
        8 to "anticipation",
        9 to "trust",
        10 to "disgust"
    )





}