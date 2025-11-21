package com.evol.movies.domain.analytics

import com.evol.movies.domain.repository.AnalyticsRepository
import javax.inject.Inject

class SendAnalyticsEventUseCase @Inject constructor(
    private val repository: AnalyticsRepository
) {

    operator fun invoke(event: String, params: Map<String, String>? = null) {
        repository.logEvent(event, params)
    }
}