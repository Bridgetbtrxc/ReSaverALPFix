package com.elflin.movieapps.viewmodel
import com.google.gson.JsonParser
import android.content.Context
import android.util.Log
import okhttp3.ResponseBody

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


import androidx.lifecycle.viewModelScope
import com.elflin.movieapps.Application



import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.elflin.movieapps.Repository.AuthRepository
import com.elflin.movieapps.data.DataStoreManager
import com.elflin.movieapps.model.LoginRequest
import com.google.gson.Gson
import com.google.gson.JsonObject


import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository,
    private val dataStore : DataStoreManager

): ViewModel(){

//    init {
//        // Example usage: Replace 'context' with actual context if needed.
//         initAuth(context)
//    }
private val _isLoggedIn = MutableLiveData<Boolean>()

    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName

    private val _userEmail = MutableLiveData<String>()
    val userEmail: LiveData<String> get() = _userEmail

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    val _register: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    private val _token = MutableLiveData<String>()
    val token: LiveData<String> get() = _token




    private fun checkLoginStatus() = viewModelScope.launch {
        dataStore.getToken.collect { token ->
            _isLoggedIn.value = !token.isNullOrEmpty()
        }
    }

    fun initAuth(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        _token.value = sharedPreferences.getString("token", "")
        _isLoggedIn.value = !_token.value.isNullOrEmpty()
    }

  init{
      checkLoginStatus()
  }




    fun checkLoginStatus(context: Context) {
        viewModelScope.launch {
            val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val token = sharedPreferences.getString("token", "")
            _isLoggedIn.value = !token.isNullOrEmpty()
        }
    }

    fun onLoggedOut() {
        _isLoggedIn.value = false
    }



    val _login: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    val _logout: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    val register: LiveData<String>
        get() = _register

    val login : LiveData<String>
        get()=_login

    val logout: LiveData<String>
        get()=_logout

    fun registeruser(user_username: String, user_email: String, user_password: String)
    = viewModelScope.launch{
        repo.registerUser(user_username, user_email, user_password).let{
            response ->
            if(response.isSuccessful){
                _register.value = "user succesfully registered"
                Log.d("Register user", response.body().toString())
            } else {
                _register.value = response.message()
                Log.e("Register User", response.message())
            }
        }
    }


    fun setEmail(email: String) {
        _email.value = email
    }

    // Function to update password
    fun setPassword(password: String) {
        _password.value = password
    }

    fun logoutuser(user_token: String, context: Context)
    = viewModelScope.launch{
        clearToken(context)
        onLoggedOut()
        repo.logoutUser(user_token)
        _logout.value = "User successfully logged out"
    }

// In your AuthViewModel class

    fun getToken(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        // This will return an empty string if the token does not exist
        return sharedPreferences.getString("token", "") ?: ""
    }

    private fun saveToken(context: Context, token: String) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("token", token)
            apply() // or use commit() if you need to save it synchronously
        }
    }

    private fun clearToken(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            remove("token")
            apply() // or use commit() if you need to save it synchronously
        }
    }

//    private fun saveToken(context: Context, token: String) {
//        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//        sharedPreferences.edit().putString("token", token).apply()
//        Log.d("AuthViewModel", "Token saved: $token")
//
//    }

//    private fun clearToken(context: Context) {
//        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//        sharedPreferences.edit().remove("token").apply()
//        Log.d("AuthViewModel", "Token cleared")
//    }




    fun logoutuser(context: Context) = viewModelScope.launch {
        clearToken(context) // Clear token
        dataStore.clearToken()
        _logout.value = "User successfully logged out"
        _isLoggedIn.value = false
        _userEmail.value = ""


    }



    fun loginUser(user_email: String, user_password: String, context: Context, navController: NavController,

    ) = viewModelScope.launch {
        try {
            val response = repo.loginUser(user_email, user_password)
            if (response.isSuccessful) {
                // Parse the JSON response to get the token
                val tokenJson = response.body()
                print(tokenJson)
                Log.e("login error 2", tokenJson.toString())
                val token = tokenJson.toString()
                AuthRepository.ACCESS_TOKEN = tokenJson.toString()

                if (token.isNotEmpty()) {
//                    saveToken(context, token)
                    dataStore.saveToken(token)
                    _isLoggedIn.value = true
                    _userEmail.value = user_email
                    val username = extractUsernameFromEmail(user_email)
                    _userName.value = username
                    AuthRepository.ACCESS_TOKEN = tokenJson.toString()
                    _login.value = "User successfully logged in"
                    Log.e("login error 1", "aaa")

                } else {
                    Log.e("login error 2", "aaa")
                    _login.value = "Error: Empty token"
                }
            } else {
                response.errorBody()?.string()?.let { Log.e("login error 3", it) }
                print("login exception2: " + response.errorBody()?.string())
                _login.value = "Error: " + response.errorBody()?.string()
            }
        } catch (e: Exception) {
            Log.e("login error 4", "aaa")
            print("login exception: " + e.message)
            _login.value = "Exception: " + e.message
            e.message?.let { Log.e("login error 4", it) }
        }


    }

    fun loginToken(user_email: String, user_password: String) = viewModelScope.launch {
        try {
            val response = repo.loginUser(user_email, user_password)
            if (response.isSuccessful) {
                // Parse the JSON response to get the token
                val tokenJson = response.body()
                Log.d("loginToken", "Response: $tokenJson")
                val token = tokenJson.toString()
                AuthRepository.ACCESS_TOKEN = token

                if (token.isNotEmpty()) {
                    dataStore.saveToken(token)
                    _userEmail.value = user_email
                    val username = extractUsernameFromEmail(user_email)
                    _userName.value = username

                    // Log success but no navigation
                    Log.d("loginToken", "User successfully logged in with token: $token")
                } else {
                    Log.e("loginToken", "Error: Empty token")
                }
            } else {
                val errorBody = response.errorBody()?.string()
                Log.e("loginToken", "Error: $errorBody")
            }
        } catch (e: Exception) {
            Log.e("loginToken", "Exception: ${e.message}")
        }
    }



    fun retrieveTokenForBudgetSetting(user_email: String, user_password: String, onTokenRetrieved: (String) -> Unit) = viewModelScope.launch {
        try {
            val response = repo.loginUser(user_email, user_password)
            if (response.isSuccessful) {
                // Parse the JSON response to get the token
                val tokenJson = response.body() ?: return@launch
                val token = tokenJson.toString()
                if (token.isNotEmpty()) {
                    onTokenRetrieved(token)
                } else {
                    Log.e("AuthViewModel", "Empty token received")
                }
            } else {
                val errorBody = response.errorBody()?.string()
                Log.e("AuthViewModel", "Error: $errorBody")
            }
        } catch (e: Exception) {
            Log.e("AuthViewModel", "Exception: ${e.message}")
        }
    }


    private fun extractUsernameFromEmail(email: String): String {
        return email.substringBefore('@')
    }



//    fun loginUser(user_email: String, user_password: String) = viewModelScope.launch {
//        val loginRequest = LoginRequest(user_email, user_password)
//        try {
//            val response = repo.loginUser(user_email,user_password)
//            if (response.isSuccessful && response.body() != null) {
//                _login.value = "User successfully logged in"
//                Log.d("Login user", response.body().toString())
//            } else {
//                _login.value = "Error: " + response.errorBody()?.string()
//                Log.e("Login User Error", response.errorBody()?.string() ?: "Unknown Error")
//            }
//        } catch (e: Exception) {
//            _login.value = "Exception: " + e.message
//            Log.e("Login User Exception", e.message ?: "Unknown Exception")
//        }
//    }

}