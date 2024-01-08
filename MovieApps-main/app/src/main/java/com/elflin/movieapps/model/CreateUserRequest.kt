package com.elflin.movieapps.retrofit

data class CreateUserRequest(
    val name: String,
    val email: String,
    val password: String
    // Add any additional fields that your API requires for user creation
)
