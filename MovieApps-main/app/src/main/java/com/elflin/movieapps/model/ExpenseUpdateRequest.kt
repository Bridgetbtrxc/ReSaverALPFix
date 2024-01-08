package com.elflin.movieapps.model

data class ExpenseUpdateRequest(
    val id: String,
    val name: String,
    val amount: String,
    val category: String,
    val date: String
)
