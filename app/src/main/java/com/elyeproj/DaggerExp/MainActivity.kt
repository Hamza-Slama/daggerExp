package com.elyeproj.DaggerExp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.elyeproj.DaggerExp.MyApplication.Companion.magicBox
import com.elyeproj.DaggerExp.module.Animal
import com.elyeproj.DaggerExp.module.GetPropertyDetails
import com.elyeproj.DaggerExp.module.Perso
import com.elyeproj.DaggerExp.network.ApiInterface
import com.elyeproj.simplestappwithdagger2.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var person: Perso

    @Inject
    lateinit var animal: Animal

    @Inject
    lateinit var apiInterface: ApiInterface

    var mContext = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        magicBox.poke(this)

        Log.d("ok", person.go())

        animal.name = "Animal n1"
        Log.d("animal", animal.name)

        btn2.setOnClickListener{getDataFromCodeForces ()}
        btn1.setOnClickListener{getDataFromCodeForces2 ()}

    }

    fun getDataFromCodeForces (){

        apiInterface.getInfos().enqueue(object : Callback<GetPropertyDetails> {
            override fun onResponse(call: Call<GetPropertyDetails>?, response: Response<GetPropertyDetails>?) {
                val res = response!!.body()!!.status
                if (response!!.errorBody() == null && res == "ok") {


                    //TODO : do some other cool stuff here
                    Toast.makeText(mContext, response.body()!!.toString(), Toast.LENGTH_LONG).show()
                    Log.d("response", response.body()!!.toString())

                } else {

                    Log.d("response", response.body()!!.toString())
                    Toast.makeText(mContext, response.body()!!.toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<GetPropertyDetails>?, t: Throwable?) {
                Toast.makeText(mContext, t!!.localizedMessage, Toast.LENGTH_LONG).show()

            }
        })
    }

    fun getDataFromCodeForces2 (){

        apiInterface.getInfo().subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> Toast.makeText(mContext, response.toString(), Toast.LENGTH_LONG).show() },
                        {error -> Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show() })
    }
}





