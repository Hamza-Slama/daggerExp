package com.elyeproj.simplestappwithdagger2.module

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetPropertyDetails {

    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("result")
    @Expose
    var result: List<Result>? = null

    override fun toString() : String{
        return "$status this from codeforces."
    }

}