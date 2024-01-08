package com.elflin.movieapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.elflin.movieapps.data.DataStoreManager
import com.elflin.movieapps.ui.MovieAppsRoute
import com.elflin.movieapps.viewmodel.AuthViewModel
import com.elflin.movieapps.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Obtain the AuthViewModel using the viewModel() delegate
            val authViewModel: AuthViewModel = viewModel()
            val mainViewModel: MainViewModel = viewModel()

            // Obtain the lifecycleOwner
            val lifecycleOwner = LocalLifecycleOwner.current

            // Create or obtain an instance of DataStoreManager
            val context = LocalContext.current
            val dataStore = DataStoreManager(context)
            MovieAppsRoute(authViewModel, dataStore, lifecycleOwner, context,mainViewModel)
        }
    }
}
