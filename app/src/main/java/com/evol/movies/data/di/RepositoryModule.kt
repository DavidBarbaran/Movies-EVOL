package com.evol.movies.data.di

import com.evol.movies.data.repository.AnalyticsRepositoryImpl
import com.evol.movies.data.repository.MovieRepositoryImpl
import com.evol.movies.domain.repository.AnalyticsRepository
import com.evol.movies.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    fun bindsAnalyticsRepository(impl: AnalyticsRepositoryImpl): AnalyticsRepository
}