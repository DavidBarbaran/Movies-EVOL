package com.evol.movies.presentation.feature.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evol.movies.domain.movie.GetMoviesUseCase
import com.evol.movies.presentation.common.ErrorMapper
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
class MoviesListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val errorMapper: ErrorMapper,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MoviesUiState())
    val uiState: StateFlow<MoviesUiState> = _uiState

    private val _events = MutableSharedFlow<MoviesEvent>()
    val events = _events.asSharedFlow()

    fun loadNextPage() {
        if (_uiState.value.isLoading) return

        val page = _uiState.value.page

        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            runCatching {
                getMoviesUseCase(page)
            }.onSuccess { movies ->
                _uiState.update {
                    it.copy(
                        movies = it.movies + movies.map { movie -> movie.toUIModel() },
                        page = it.page + 1,
                        isLoading = false
                    )
                }
            }.onFailure { error ->
                _events.emit(MoviesEvent.ShowError(errorMapper.map(error)))
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}