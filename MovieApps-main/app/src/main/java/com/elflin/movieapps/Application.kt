package com.elflin.movieapps

import android.app.Application
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.elflin.movieapps.viewmodel.AuthViewModel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application: Application() {

}