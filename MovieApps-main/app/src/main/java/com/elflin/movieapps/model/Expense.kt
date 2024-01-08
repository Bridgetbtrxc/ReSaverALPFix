package com.elflin.movieapps.model

import com.google.gson.annotations.SerializedName

data class Expense(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("amount")
    val amount: String, // or Double if you handle it as numeric
    @SerializedName("date")
    val date: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("category_id")
    val categoryId: Int
)
