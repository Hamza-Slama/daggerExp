package com.elyeproj.simplestappwithdagger2

import android.app.Application
import android.location.LocationManager
import javax.inject.Inject



class MyApplication : Application() {

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var magicBox: MagicBox
    }

    override fun onCreate() {
        super.onCreate()
       magicBox = DaggerMagicBox
                .builder()
                .networkModules(NetworkModules(BASE_URL))
                .build()
        magicBox.poke(this)


        //TODO do some other cool stuff here
    }
}