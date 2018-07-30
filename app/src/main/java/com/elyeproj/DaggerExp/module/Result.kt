package com.elyeproj.DaggerExp.module

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("lastName")
    @Expose
    var lastName: String? = null
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("lastOnlineTimeSeconds")
    @Expose
    var lastOnlineTimeSeconds: Int? = null
    @SerializedName("city")
    @Expose
    var city: String? = null
    @SerializedName("rating")
    @Expose
    var rating: Int? = null
    @SerializedName("friendOfCount")
    @Expose
    var friendOfCount: Int? = null
    @SerializedName("titlePhoto")
    @Expose
    var titlePhoto: String? = null
    @SerializedName("handle")
    @Expose
    var handle: String? = null
    @SerializedName("avatar")
    @Expose
    var avatar: String? = null
    @SerializedName("firstName")
    @Expose
    var firstName: String? = null
    @SerializedName("contribution")
    @Expose
    var contribution: Int? = null
    @SerializedName("organization")
    @Expose
    var organization: String? = null
    @SerializedName("rank")
    @Expose
    var rank: String? = null
    @SerializedName("maxRating")
    @Expose
    var maxRating: Int? = null
    @SerializedName("registrationTimeSeconds")
    @Expose
    var registrationTimeSeconds: Int? = null
    @SerializedName("maxRank")
    @Expose
    var maxRank: String? = null

}


