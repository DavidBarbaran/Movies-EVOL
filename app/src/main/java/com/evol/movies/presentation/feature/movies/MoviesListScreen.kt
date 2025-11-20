package com.evol.movies.presentation.feature.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.evol.movies.R
import com.evol.movies.presentation.dialogs.ErrorDialog
import com.evol.movies.presentation.model.MovieUiModel
import com.evol.movies.presentation.ui.theme.MoviesEVOLTheme
import com.evol.movies.presentation.util.shimmer

@Composable
fun MoviesListScreen(
    viewModel: ListMovieViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsState()

    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.loadNextPage()
    }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is MoviesEvent.ShowError -> {
                    errorMessage = event.message
                }
            }
        }
    }

    MoviesListScreenUI(
        movies = uiState.movies,
        isLoading = uiState.isLoading,
        onLoadMore = {
            viewModel.loadNextPage()
        },
        onClickMovie = {}
    )

    errorMessage?.let { message ->
        ErrorDialog(
            message = message,
            onRetry = {
                errorMessage = null
                viewModel.loadNextPage()
            }
        )
    }
}

@Composable
fun MoviesListScreenUI(
    movies: List<MovieUiModel>,
    isLoading: Boolean,
    onClickMovie: (MovieUiModel) -> Unit,
    onLoadMore: () -> Unit,
) {
    Column(
        modifier = Modifier
            .systemBarsPadding()
            .padding(horizontal = 16.dp)
    ) {

        Header()

        if (movies.isEmpty() && isLoading) {
            MoviesListShimmer()
            return
        }

        MovieList(
            movies = movies,
            isLoading = isLoading,
            onClickMovie = onClickMovie,
            onLoadMore = onLoadMore
        )
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(R.drawable.ic_popcorn),
            contentDescription = null,
            tint = null,
            modifier = Modifier.size(44.dp)
        )

        Text(
            text = stringResource(R.string.title_movies),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun MovieList(
    movies: List<MovieUiModel>,
    isLoading: Boolean,
    onClickMovie: (MovieUiModel) -> Unit,
    onLoadMore: () -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(top = 12.dp)
    ) {

        items(items = movies, key = { it.id }) { movie ->

            MovieItem(movie = movie, onClickMovie = onClickMovie)

            if (movies.indexOf(movie) == movies.lastIndex) {
                onLoadMore()
            }
        }

        if (isLoading && movies.isNotEmpty()) {
            items(3) {
                MovieShimmerItem()
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: MovieUiModel,
    onClickMovie: (MovieUiModel) -> Unit,
) {
    val context = LocalContext.current

    val request = remember {
        ImageRequest.Builder(context)
            .data(movie.posterPath)
            .crossfade(true)
            .placeholder(R.drawable.ic_placeholder_poster)
            .error(R.drawable.ic_placeholder_poster)
            .build()
    }

    Column(
        modifier = Modifier
            .clickable { onClickMovie(movie) }
    ) {
        AsyncImage(
            model = request,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(16.dp)),
        )
        Text(
            modifier = Modifier.padding(top = 2.dp, bottom = 2.dp),
            text = movie.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
                tint = null,
                modifier = Modifier.size(16.dp)
            )

            Text(
                text = movie.voteAverage.toString(),
                modifier = Modifier.padding(start = 4.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}

@Composable
fun MoviesListShimmer() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(top = 12.dp)
    ) {
        items(12) { MovieShimmerItem() }
    }
}

@Composable
fun MovieShimmerItem() {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(16.dp))
                .shimmer()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(20.dp)
                .clip(RoundedCornerShape(6.dp))
                .shimmer()
        )

        Spacer(modifier = Modifier.height(4.dp))

        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(16.dp)
                .clip(RoundedCornerShape(6.dp))
                .shimmer()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ListMovieScreenScreenPreview(
    @PreviewParameter(MoviesPreviewProvider::class)
    movies: List<MovieUiModel>,
) {

    MoviesEVOLTheme {
        MoviesListScreenUI(
            movies = movies,
            isLoading = false,
            onLoadMore = {},
            onClickMovie = {}
        )
    }
}