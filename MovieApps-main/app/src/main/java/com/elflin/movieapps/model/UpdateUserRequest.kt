package com.elflin.movieapps.model

data class UpdateUserRequest(
    val name: String,
    val email: String,
    val password: String
    // Add other fields as needed
)
