package com.elflin.movieapps.model

data class UserResponse(
    val status: Int,
    val message: String,
    val data: Any // Use a more specific type or leave as Any
)
