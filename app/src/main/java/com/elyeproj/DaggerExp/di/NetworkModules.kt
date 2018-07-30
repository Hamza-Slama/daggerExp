package com.elyeproj.DaggerExp.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.elyeproj.DaggerExp.MyApplication
import com.elyeproj.DaggerExp.module.Animal
import com.elyeproj.DaggerExp.module.Perso
import com.elyeproj.DaggerExp.network.ApiInterface
import com.elyeproj.DaggerExp.utils.mBaseUrl
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
 class NetworkModules (private val application: MyApplication){
    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    @Provides
    @Singleton
    fun providePerso () : Perso {
        return Perso(mBaseUrl)
    }

    @Provides @Singleton  @Named("ThisFromB")
    fun providePersoB () : Perso {
        return Perso(mBaseUrl)
    }

    @Provides @Singleton
    fun provideAnimal () : Animal {
        return Animal()
    }


    // Dagger will only look for methods annotated with @Provides
    @Provides @Named("providesShared")
    @Singleton
    fun providesSharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Provides
    @Singleton
//    @BindsInstance
      fun provideOkHttpCache( ): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(application.cacheDir, cacheSize.toLong())
        //return Cache(File(""), cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        //TODO : work with cache
        val client = OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
              .cache(cache)
        return client.build()

    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

}