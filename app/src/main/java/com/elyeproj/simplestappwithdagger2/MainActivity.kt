package com.elyeproj.simplestappwithdagger2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.elyeproj.simplestappwithdagger2.MyApplication.Companion.magicBox
import com.elyeproj.simplestappwithdagger2.module.Animal
import com.elyeproj.simplestappwithdagger2.module.GetPropertyDetails
import com.elyeproj.simplestappwithdagger2.module.Perso
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

        getDataFromCodeForces ()

    }

    fun getDataFromCodeForces (){

        apiInterface.getInfo().enqueue(object : Callback<GetPropertyDetails> {
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
}





