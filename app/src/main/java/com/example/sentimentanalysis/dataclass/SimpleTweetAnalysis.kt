package com.example.sentimentanalysis.dataclass

data class SimpleTweetAnalysis(val tweet: String,
                               val sentiment: String,
                               val confidence: String,
                               val date: String,
                               val time: String,
                               val id: String)
