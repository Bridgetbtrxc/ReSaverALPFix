package com.elflin.movieapps.model

import com.google.gson.annotations.SerializedName

data class Wishlist(
    @SerializedName("id")
    val id: Int,

    @SerializedName("created_at")
    val created_at: String,

    @SerializedName("updated_at")
    val updated_at: String,

    @SerializedName("waiting_period")
    val waiting_period: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("unlock")
    val unlock: String,

    @SerializedName("bought")
    val bought: String,

    @SerializedName("user_id")
    val user_id: Int
)
