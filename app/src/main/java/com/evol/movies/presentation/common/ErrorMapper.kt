package com.evol.movies.presentation.common

import android.content.Context
import com.evol.movies.R
import com.evol.movies.data.exception.NetworkUnavailableException
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ErrorMapper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun map(error: Throwable): String = when (error) {
        is NetworkUnavailableException -> context.getString(R.string.error_network_unavailable)
        else -> context.getString(R.string.error_uncaught_error)
    }
}