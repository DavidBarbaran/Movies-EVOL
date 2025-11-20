package com.evol.movies.data.di

import com.evol.movies.data.source.local.entity.MovieDatabaseEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRealmDatabase(): Realm {
        return Realm.open(
            RealmConfiguration.Builder(
                schema = setOf(MovieDatabaseEntity::class)
            ).build()
        )
    }
}