package com.elyeproj.DaggerExp

import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(NetworkModules::class))
interface MagicBox {
    fun poke(app: MainActivity)
    fun poke(app: MyApplication)
    fun poke(app: ListMovie)
}
