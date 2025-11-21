package com.evol.movies.presentation.feature.movies

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.evol.movies.presentation.model.MovieUiModel

class MoviesPreviewProvider : PreviewParameterProvider<List<MovieUiModel>> {

    override val values: Sequence<List<MovieUiModel>> = sequenceOf(
        listOf(
            createSampleMovie("Interstellar"),
            createSampleMovie("Avatar 3"),
            createSampleMovie("Spider-Man: New Dimensions"),
            createSampleMovie("Interstellar"),
            createSampleMovie("Avatar 3"),
            createSampleMovie("Spider-Man: New Dimensions"),
            createSampleMovie("Interstellar"),
            createSampleMovie("Avatar 3"),
            createSampleMovie("Spider-Man: New Dimensions"),
            createSampleMovie("Interstellar"),
            createSampleMovie("Avatar 3"),
            createSampleMovie("Spider-Man: New Dimensions"),
        )
    )

    private fun createSampleMovie(title: String) = MovieUiModel(
        id = randomId(),
        title = title,
    )

    private fun randomId(): Int = (1000..9999).random()
}
