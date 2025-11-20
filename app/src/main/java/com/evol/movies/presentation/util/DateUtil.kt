package com.evol.movies.presentation.util

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Locale

fun formatDate(dateString: String): String {
    try {
        val input = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val output = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val date = input.parse(dateString)
        return output.format(date!!)
    } catch (e: Exception) {
        return dateString
    }
}
