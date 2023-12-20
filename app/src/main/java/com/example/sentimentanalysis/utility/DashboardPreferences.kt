package com.example.sentimentanalysis.utility

import android.content.Context
import android.preference.PreferenceManager

class DashboardPreferences(context: Context?) {

    companion object {
        private const val NEGATIVE_COUNT = "negativeCount"
        private const val POSITIVE_COUNT = "positiveCount"
        private const val NEUTRAL_COUNT = "neutralCount"

        private const val JOY = "joy"
        private const val SADNESS = "sadness"
        private const val ANGER = "anger"
        private const val FEAR = "fear"
        private const val DISGUST = "disgust"
        private const val SURPRISE = "surprise"
        private const val TRUST = "trust"
        private const val ANTICIPATION = "anticipation"
        private const val OPTIMISM = "optimism"
        private const val PESSIMISM = "pessimism"
        private const val LOVE = "love"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var negativeCount: Int
        get() = preferences.getInt(NEGATIVE_COUNT, 0)
        set(value) = preferences.edit().putInt(NEGATIVE_COUNT, value).apply()

    var positiveCount: Int
        get() = preferences.getInt(POSITIVE_COUNT, 0)
        set(value) = preferences.edit().putInt(POSITIVE_COUNT, value).apply()

    var neutralCount: Int
        get() = preferences.getInt(NEUTRAL_COUNT, 0)
        set(value) = preferences.edit().putInt(NEUTRAL_COUNT, value).apply()


    var joyCount: Int
        get() = preferences.getInt(JOY, 0)
        set(value) = preferences.edit().putInt(JOY, value).apply()

    var sadnessCount: Int
        get() = preferences.getInt(SADNESS, 0)
        set(value) = preferences.edit().putInt(SADNESS, value).apply()

    var angerCount: Int
        get() = preferences.getInt(ANGER, 0)
        set(value) = preferences.edit().putInt(ANGER, value).apply()

    var fearCount: Int
        get() = preferences.getInt(FEAR, 0)
        set(value) = preferences.edit().putInt(FEAR, value).apply()

    var disgustCount: Int
        get() = preferences.getInt(DISGUST, 0)
        set(value) = preferences.edit().putInt(DISGUST, value).apply()

    var surpriseCount: Int
        get() = preferences.getInt(SURPRISE, 0)
        set(value) = preferences.edit().putInt(SURPRISE, value).apply()

    var trustCount: Int
        get() = preferences.getInt(TRUST, 0)
        set(value) = preferences.edit().putInt(TRUST, value).apply()

    var anticipationCount: Int
        get() = preferences.getInt(ANTICIPATION, 0)
        set(value) = preferences.edit().putInt(ANTICIPATION, value).apply()

    var optimismCount: Int
        get() = preferences.getInt(OPTIMISM, 0)
        set(value) = preferences.edit().putInt(OPTIMISM, value).apply()

    var pessimismCount: Int
        get() = preferences.getInt(PESSIMISM, 0)
        set(value) = preferences.edit().putInt(PESSIMISM, value).apply()

    var loveCount: Int
        get() = preferences.getInt(LOVE, 0)
        set(value) = preferences.edit().putInt(LOVE, value).apply()

    fun clearAll() {
        preferences.edit().clear().apply()
    }
}