package com.elflin.movieapps.model

data class AddExpenseRequest(
    val amount: Int,
    val category_id: Int,
    val name: String
)