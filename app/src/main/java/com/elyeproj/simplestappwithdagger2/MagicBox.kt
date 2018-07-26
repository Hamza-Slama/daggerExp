package com.elyeproj.simplestappwithdagger2

import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(NetworkModules::class))
interface MagicBox {
    fun poke(app: MainActivity)
    fun poke(app: MyApplication)
}
