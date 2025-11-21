package com.evol.movies.data.source.local.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class MovieDatabaseEntity : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var posterPath: String = ""
    var title: String = ""
    var voteAverage: Double = 0.0
    var overview: String = ""
    var backdropPath: String = ""
    var releaseDate: String = ""
    var isAdultContent: Boolean = false
}