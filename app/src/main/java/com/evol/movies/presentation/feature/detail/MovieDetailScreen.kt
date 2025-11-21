package com.evol.movies.presentation.feature.detail

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.evol.movies.R
import com.evol.movies.presentation.dialogs.ErrorDialog
import com.evol.movies.presentation.model.MovieUiModel
import com.evol.movies.presentation.theme.Black25
import com.evol.movies.presentation.theme.MoviesEVOLTheme
import com.evol.movies.presentation.util.formatDate
import com.evol.movies.presentation.util.formatVoteAverage

@Composable
fun MovieDetailScreen(
    navController: NavController,
    movieId: Int?,
    viewModel: MovieDetailViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsState()

    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.logScreenEvent(movieId)
        viewModel.getMovieDetail(movieId)
    }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is MovieDetailEvent.ShowError -> {
                    errorMessage = context.getString(R.string.error_uncaught_error)
                }
            }
        }
    }

    MovieDetailScreenUI(
        movie = uiState.movie,
        isLoading = uiState.isLoading
    )

    errorMessage?.let { message ->
        ErrorDialog(
            confirmButtonText = stringResource(R.string.accept),
            message = message,
            onRetry = {
                errorMessage = null
                navController.popBackStack()
            }
        )
    }
}

@Composable
fun MovieDetailScreenUI(
    movie: MovieUiModel,
    isLoading: Boolean,
) {
    if (isLoading) {
        LoadingIndicator()
        return
    }

    Column(
        modifier = Modifier.navigationBarsPadding()
    ) {

        BackdropImage(backdropPath = movie.backdropPath)

        MovieDetailContent(movie = movie)
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
            strokeWidth = 4.dp
        )
    }
}

@Composable
fun BackdropImage(backdropPath: String) {
    val context = LocalContext.current

    val request = remember(backdropPath) {
        ImageRequest.Builder(context)
            .data(backdropPath)
            .crossfade(true)
            .placeholder(R.drawable.ic_placeholder_backdrop)
            .error(R.drawable.ic_placeholder_backdrop)

            .build()
    }

    AsyncImage(
        model = request,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .aspectRatio(16f / 9f)
    )
}

@Composable
fun MovieDetailContent(movie: MovieUiModel) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    ) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.titleLarge,
        )

        Row(
            modifier = Modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
                tint = null,
                modifier = Modifier.size(16.dp)
            )

            Text(
                text = movie.voteAverage.formatVoteAverage(),
                modifier = Modifier.padding(start = 4.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Row(
            modifier = Modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.detail_premiere),
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = formatDate(movie.releaseDate),
                modifier = Modifier.padding(start = 4.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        if (movie.isAdultContent) {
            SuggestionChip(
                onClick = { Log.d("Suggestion chip", "hello world") },
                label = {
                    Text(
                        text = stringResource(R.string.age_adult_content),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp),
            color = Black25,
            thickness = 1.dp
        )

        Text(
            text = movie.overview,
            modifier = Modifier.padding(start = 4.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                letterSpacing = 0.5.sp,
                lineHeight = 28.sp
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MovieDetailScreenUIPreview(
    @PreviewParameter(MovieDetailPreviewProvider::class)
    movie: MovieUiModel
) {
    MoviesEVOLTheme {
        MovieDetailScreenUI(
            movie = movie,
            isLoading = false
        )
    }
}