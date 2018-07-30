package com.elyeproj.DaggerExp.di

import com.elyeproj.DaggerExp.MainActivity
import com.elyeproj.DaggerExp.Movie.ListMovie
import com.elyeproj.DaggerExp.MyApplication
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(NetworkModules::class))
interface MagicBox {
    fun poke(app: MainActivity)
    fun poke(app: MyApplication)
    fun poke(app: ListMovie)
//    fun poke(app: ListInteractor)
}
