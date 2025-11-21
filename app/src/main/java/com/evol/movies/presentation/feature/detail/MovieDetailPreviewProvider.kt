package com.evol.movies.presentation.feature.detail

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.evol.movies.presentation.model.MovieUiModel

class MovieDetailPreviewProvider : PreviewParameterProvider<MovieUiModel> {

    override val values: Sequence<MovieUiModel> = sequenceOf(
        MovieUiModel(
            id = 1,
            title = "Interstellar",
            voteAverage = 9.0,
            overview = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",
            releaseDate = "2014-11-06",
            isAdultContent = true
        )
    )
}