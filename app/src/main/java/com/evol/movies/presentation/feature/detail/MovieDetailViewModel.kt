package com.evol.movies.presentation.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evol.movies.domain.analytics.AnalyticsConstants
import com.evol.movies.domain.analytics.SendAnalyticsEventUseCase
import com.evol.movies.domain.detail.GetMovieDetailUseCase
import com.evol.movies.presentation.mapper.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val sendAnalyticsEventUseCase: SendAnalyticsEventUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState

    private val _events = MutableSharedFlow<MovieDetailEvent>()
    val events = _events.asSharedFlow()

    fun logScreenEvent(movieId: Int?) {
        sendAnalyticsEventUseCase.invoke(
            event = AnalyticsConstants.VIEW_MOVIE_DETAIL,
            params = mapOf(
                AnalyticsConstants.MOVIE_ID to movieId.toString()
            )
        )
    }

    fun getMovieDetail(id: Int?) = viewModelScope.launch {

        if (id == null) {
            _events.emit(MovieDetailEvent.ShowError)
            return@launch
        }

        _uiState.update { it.copy(isLoading = true) }

        runCatching {
            getMovieDetailUseCase(id)
        }.onSuccess { movie ->

            if (movie != null) {
                _uiState.update { it.copy(movie = movie.toUIModel(), isLoading = false) }
            } else {
                _events.emit(MovieDetailEvent.ShowError)
                _uiState.update { it.copy(isLoading = true) }
            }
        }.onFailure { error ->
            _events.emit(MovieDetailEvent.ShowError)
            _uiState.update { it.copy(isLoading = false) }
        }
    }
}