package com.elyeproj.DaggerExp.Movie

import android.util.Log
import com.elyeproj.DaggerExp.network.ApiInterface
import com.elyeproj.DaggerExp.module.MovieResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListInteractor @Inject constructor(private val apiMovies: ApiInterface) {
    private var disposables: CompositeDisposable = CompositeDisposable()
    interface onGetListFinishedListener<T> {
        fun onReceive()
        fun onSuccess(result:T)
        fun onError(errorMessage:String)
    }

    fun getListInteractor( listener: onGetListFinishedListener<MovieResponse>) {
        disposables.add(apiMovies.getMovies()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listener.onSuccess(it)
                    Log.d("TAG", it.data[0].genre.toString()) },
                        {
                            error-> listener.onError(error.localizedMessage)
                        }))
    }
}