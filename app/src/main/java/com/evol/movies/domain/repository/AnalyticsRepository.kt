package com.evol.movies.domain.repository

interface AnalyticsRepository {
    fun logEvent(event: String, params: Map<String, String>? = null)
}