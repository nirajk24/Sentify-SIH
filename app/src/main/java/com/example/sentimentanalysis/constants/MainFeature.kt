package com.example.sentimentanalysis.constants

import com.example.sentimentanalysis.R

// Define the data class
data class MainFeature(
    val mainFeaturesHeading: String,
    val mainFeaturesCatchyPhrase: String,
    val mainFeaturesImage: Int // Assuming you're using resource IDs for images
)

// Create instances for each feature
val userProfileAnalysis = MainFeature(
    mainFeaturesHeading = "User Profile Analysis",
    mainFeaturesCatchyPhrase = "Dive Deep into User Sentiments",
    mainFeaturesImage = R.drawable.ic_profile_analysis
//    mainFeaturesImage = R.drawable.twitter
)

val hashtagAnalysis = MainFeature(
    mainFeaturesHeading = "Hashtag Analysis",
    mainFeaturesCatchyPhrase = "Uncover the Mood behind the #Trends",
    mainFeaturesImage = R.drawable.ic_hashtag_analysis
//    mainFeaturesImage = R.drawable.twitter
)

val rephrasingTweets = MainFeature(
    mainFeaturesHeading = "Sentiment-based Tweet Rephrasing",
    mainFeaturesCatchyPhrase = "Reshape Your Words, Reflect Your Emotions",
    mainFeaturesImage = R.drawable.ic_senify
//    mainFeaturesImage = R.drawable.twitter
)

val singleTweetAnalysis = MainFeature(
    mainFeaturesHeading = "Single Tweet Analysis",
    mainFeaturesCatchyPhrase = "Insight into Every Tweet's Emotional Core",
//    mainFeaturesImage = R.drawable.single_tweet_analysis_icon
    mainFeaturesImage = R.drawable.twitter
)

val speechAnalysis = MainFeature(
    mainFeaturesHeading = "Speech Analysis",
    mainFeaturesCatchyPhrase = "Analyze the Sentiments in Your Voice",
//    mainFeaturesImage = R.drawable.ic_speech_analysis
    mainFeaturesImage = R.drawable.twitter
)

// Create a list of the main features
val mainFeaturesList = listOf(userProfileAnalysis, hashtagAnalysis, rephrasingTweets, singleTweetAnalysis, speechAnalysis)
//val mainFeaturesHeading = mapOf<String, String>("1" to "User Profile Analysis", "2" to "Hashtag Analysis", "3" to "Sentify Tweets", "4" to "")