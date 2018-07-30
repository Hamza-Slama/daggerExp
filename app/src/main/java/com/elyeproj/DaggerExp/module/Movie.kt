package com.elyeproj.DaggerExp.module

class Movie {

        var id: Int = 0
        var title: String? = null
        var year: String? = null
        var genre: String? = null
        var poster: String? = null
    }


    class MovieResponse {

        lateinit var data: List<Movie>
    }