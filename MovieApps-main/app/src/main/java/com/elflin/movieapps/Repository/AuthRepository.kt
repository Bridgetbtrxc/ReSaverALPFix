package com.elflin.movieapps.Repository

import com.elflin.movieapps.data.EndPointAPI
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api : EndPointAPI
) {

    companion object{
        var ACCESS_TOKEN = ""

    }

    suspend fun registerUser(
        user_username : String,
        user_email:String,
        user_password: String)
    = api.register(user_username,user_email,user_password)

    suspend fun loginUser(

        user_email: String,
        user_password:String)
    =api.login(user_email,user_password)

    suspend fun logoutUser(

        user_token:String
    )= api.logout(user_token)

//    suspend fun logoutUser(
//        user_email:String,
//        user_token:String
//        =api.logout()
//    )

//    suspend fun getProfile(
//        user_id : String)
//    =api.getLoginInfo(user_id)
//    )

}