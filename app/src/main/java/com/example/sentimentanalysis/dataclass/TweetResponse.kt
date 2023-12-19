package com.example.sentimentanalysis.dataclass


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class TweetResponse(
    @SerializedName("tweet")
    val tweet: List<Tweet>,
    @SerializedName("user")
    val user: User
) {
    @Keep
    data class Tweet(
        @SerializedName("date")
        val date: String,
        @SerializedName("emotion_prediction")
        val emotionPrediction: List<Double>,
        @SerializedName("external-link")
        val externalLink: String,
        @SerializedName("gifs")
        val gifs: List<Any>,
        @SerializedName("is-retweet")
        val isRetweet: Boolean,
        @SerializedName("link")
        val link: String,
        @SerializedName("pictures")
        val pictures: List<String>,
        @SerializedName("quoted-post")
        val quotedPost: QuotedPost,
        @SerializedName("sentiment_prediction")
        val sentimentPrediction: List<Double>,
        @SerializedName("sorted_emotions")
        val sortedEmotions: SortedEmotions,
        @SerializedName("sorted_sentiments")
        val sortedSentiments: SortedSentiments,
        @SerializedName("stats")
        val stats: Stats,
        @SerializedName("text")
        val text: String,
        @SerializedName("user")
        val user: User,
        @SerializedName("videos")
        val videos: List<String>
    ) {
        @Keep
        data class QuotedPost(
            @SerializedName("date")
            val date: String,
            @SerializedName("gifs")
            val gifs: List<Any>,
            @SerializedName("link")
            val link: String,
            @SerializedName("pictures")
            val pictures: List<Any>,
            @SerializedName("text")
            val text: String,
            @SerializedName("user")
            val user: User,
            @SerializedName("videos")
            val videos: List<String>
        ) {
            @Keep
            data class User(
                @SerializedName("avatar")
                val avatar: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("profile_id")
                val profileId: String,
                @SerializedName("username")
                val username: String
            )
        }

        @Keep
        data class SortedEmotions(
            @SerializedName("Anger")
            val anger: Double,
            @SerializedName("Anticipation")
            val anticipation: Double,
            @SerializedName("Disgust")
            val disgust: Double,
            @SerializedName("Fear")
            val fear: Double,
            @SerializedName("Joy")
            val joy: Double,
            @SerializedName("Love")
            val love: Double,
            @SerializedName("Optimism")
            val optimism: Double,
            @SerializedName("Pessimism")
            val pessimism: Double,
            @SerializedName("Sadness")
            val sadness: Double,
            @SerializedName("Surprise")
            val surprise: Double,
            @SerializedName("Trust")
            val trust: Double
        )

        @Keep
        data class SortedSentiments(
            @SerializedName("Negative")
            val negative: Double,
            @SerializedName("Neutral")
            val neutral: Double,
            @SerializedName("Positive")
            val positive: Double
        )

        @Keep
        data class Stats(
            @SerializedName("comments")
            val comments: Int,
            @SerializedName("likes")
            val likes: Int,
            @SerializedName("quotes")
            val quotes: Int,
            @SerializedName("retweets")
            val retweets: Int
        )

        @Keep
        data class User(
            @SerializedName("avatar")
            val avatar: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("profile_id")
            val profileId: String,
            @SerializedName("username")
            val username: String
        )
    }

    @Keep
    data class User(
        @SerializedName("emotions")
        val emotions: Emotions,
        @SerializedName("sentiment")
        val sentiment: Sentiment
    ) {
        @Keep
        data class Emotions(
            @SerializedName("Anger")
            val anger: Double,
            @SerializedName("Anticipation")
            val anticipation: Double,
            @SerializedName("Disgust")
            val disgust: Double,
            @SerializedName("Fear")
            val fear: Double,
            @SerializedName("Joy")
            val joy: Double,
            @SerializedName("Love")
            val love: Double,
            @SerializedName("Optimism")
            val optimism: Double,
            @SerializedName("Pessimism")
            val pessimism: Double,
            @SerializedName("Sadness")
            val sadness: Double,
            @SerializedName("Surprise")
            val surprise: Double,
            @SerializedName("Trust")
            val trust: Double
        )

        @Keep
        data class Sentiment(
            @SerializedName("Negative")
            val negative: Double,
            @SerializedName("Neutral")
            val neutral: Double,
            @SerializedName("Positive")
            val positive: Double
        )
    }
}