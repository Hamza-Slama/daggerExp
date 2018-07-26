package com.elyeproj.simplestappwithdagger2

import com.elyeproj.simplestappwithdagger2.module.GetPropertyDetails
import retrofit2.Call
import retrofit2.http.*

const val authURL                           = "user.info?handles=DmitriyH;Fefer_Ivan"
const val BASE_URL                          = "http://codeforces.com/api/"
interface ApiInterface {
    fun goApi()
    @GET(authURL)
    fun getInfo(): Call<GetPropertyDetails>
}