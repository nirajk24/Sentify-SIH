package com.example.sentimentanalysis

// Define the data class
data class MainFeature(
    val mainFeaturesHeading: String,
    val mainFeaturesCatchyPhrase: String,
    val mainFeaturesImage: Int,
    val position: String
)

// Create instances for each feature
val userProfileAnalysis = MainFeature(
    mainFeaturesHeading = "User Profile Analysis",
    mainFeaturesCatchyPhrase = "Dive Deep into User Sentiments",
    mainFeaturesImage = R.drawable.ic_profile_analysis,
//    mainFeaturesImage = R.drawable.twitter,
    "#001"
)

val hashtagAnalysis = MainFeature(
    mainFeaturesHeading = "Hashtag Analysis",
    mainFeaturesCatchyPhrase = "Uncover the Mood behind the #Trends",
    mainFeaturesImage = R.drawable.hastag_analysis,
//    mainFeaturesImage = R.drawable.twitter,
    "#002"
)

val rephrasingTweets = MainFeature(
    mainFeaturesHeading = "Tweet Rephrasing",
    mainFeaturesCatchyPhrase = "Reshape Your Words, Reflect Your Emotions",
    mainFeaturesImage = R.drawable.ic_senify,
//    mainFeaturesImage = R.drawable.twitter,
    "#003"
)

val singleTweetAnalysis = MainFeature(
    mainFeaturesHeading = "Single Tweet Analysis",
    mainFeaturesCatchyPhrase = "Insight into Every Tweet's Emotional Core",
//    mainFeaturesImage = R.drawable.single_tweet_analysis_icon,
    mainFeaturesImage = R.drawable.individual_tweet_pic,
    "#003"
)


val videoAnalysis = MainFeature(
    mainFeaturesHeading = "Youtube Shorts Analysis",
    mainFeaturesCatchyPhrase = "Check what your Shorts says",
//    mainFeaturesImage = R.drawable.single_tweet_analysis_icon,
    mainFeaturesImage = R.drawable.youtube,
    "#004"
)

val speechAnalysis = MainFeature(
    mainFeaturesHeading = "Speech Analysis",
    mainFeaturesCatchyPhrase = "Analyze the Sentiments in Your Voice",
//    mainFeaturesImage = R.drawable.ic_speech_analysis
    mainFeaturesImage = R.drawable.twitter,
    "#005"
)

val mainFeaturesList = listOf(userProfileAnalysis, hashtagAnalysis, singleTweetAnalysis, videoAnalysis)

