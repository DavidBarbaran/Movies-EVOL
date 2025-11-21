package com.evol.movies.data.repository

import android.os.Bundle
import com.evol.movies.domain.repository.AnalyticsRepository
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

class AnalyticsRepositoryImpl @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics,
) : AnalyticsRepository {

    override fun logEvent(
        event: String,
        params: Map<String, String>?,
    ) {
        val bundle = Bundle()

        params?.forEach { (key, value) ->
            bundle.putString(key, value)
        }

        firebaseAnalytics.logEvent(event, bundle)
    }
}