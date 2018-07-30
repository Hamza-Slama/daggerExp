package com.elyeproj.DaggerExp

import android.app.Application
import com.elyeproj.DaggerExp.di.DaggerMagicBox
import com.elyeproj.DaggerExp.di.MagicBox
import com.elyeproj.DaggerExp.di.NetworkModules


class MyApplication : Application() {

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var magicBox: MagicBox
    }

    override fun onCreate() {
        super.onCreate()
       magicBox = DaggerMagicBox.builder()
                .networkModules(NetworkModules(this))
                .build()
        magicBox.poke(this)


        //TODO do some other cool stuff here
    }
}