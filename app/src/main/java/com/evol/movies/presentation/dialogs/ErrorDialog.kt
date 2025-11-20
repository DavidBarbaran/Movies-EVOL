package com.evol.movies.presentation.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.evol.movies.R

@Composable
fun ErrorDialog(
    message: String,
    onRetry: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {},
        text = {
            Text(text = message, style = MaterialTheme.typography.bodyLarge)
        },
        confirmButton = {
            Text(
                text = stringResource(R.string.retry),
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .clickable { onRetry() }
            )
        },
    )
}