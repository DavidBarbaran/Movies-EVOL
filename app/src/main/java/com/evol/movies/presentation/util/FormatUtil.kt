package com.evol.movies.presentation.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun Double.formatVoteAverage() : String {
    try {
        val symbols = DecimalFormatSymbols(Locale.US)
        val formatter = DecimalFormat("0.00", symbols)
        return formatter.format(this)
    } catch (e: Exception) {
        e.printStackTrace()
        return this.toString()
    }
}
