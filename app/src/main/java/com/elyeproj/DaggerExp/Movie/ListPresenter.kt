package com.elyeproj.DaggerExp.Movie

import com.elyeproj.DaggerExp.module.MovieResponse
import javax.inject.Inject

class ListPresenter @Inject constructor(val listInteractor: ListInteractor) : ListPresnterView {
    override fun attachView(view: ListView) {
        this.listView = view
    }


    var ListMovie: MovieResponse = MovieResponse()
    private var listView : ListView? = null
    fun getListPres() {
        //listView?.showProgress()
        listInteractor.getListInteractor(object : ListInteractor.onGetListFinishedListener<MovieResponse> {
            override fun onReceive() {


            }

            override fun onSuccess(result: MovieResponse) {
                listView!!.showList(result!!)
            }

            override fun onError(errorMessage: String) {

            }

        })

    }
}
