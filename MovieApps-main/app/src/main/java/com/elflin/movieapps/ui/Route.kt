package com.elflin.movieapps.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController

import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elflin.movieapps.data.DataStoreManager

import com.elflin.movieapps.ui.view.GetStartedView
import com.elflin.movieapps.ui.view.LoginView

import com.elflin.movieapps.ui.view.GetStartedView
import com.elflin.movieapps.ui.view.LoginView
import com.elflin.movieapps.ui.view.HomeView
import com.elflin.movieapps.ui.view.InsightView

import com.elflin.movieapps.viewmodel.AuthViewModel
import com.elflin.movieapps.ui.view.LockView
import com.elflin.movieapps.ui.view.ProfileView
import com.elflin.movieapps.ui.view.RegisterView
import com.elflin.movieapps.ui.view.SetBudgetView
import com.elflin.movieapps.ui.view.TopUpAddView
import com.elflin.movieapps.ui.view.TopUpEditView
import com.elflin.movieapps.viewmodel.MainViewModel


@Composable
fun MovieAppsRoute(authViewModel: AuthViewModel, dataStore: DataStoreManager, lifecycleOwner: LifecycleOwner,context: Context,mainViewModel: MainViewModel) {
  val navController = rememberNavController()
  val isLoggedIn by authViewModel.isLoggedIn.observeAsState()

  // Determine start destination
  val startDestination = if (isLoggedIn == true) "home" else "getStarted"

  NavHost(navController = navController, startDestination = startDestination) {

    composable("getStarted") {
      GetStartedView(onGetStartedClicked = { navController.navigate("login") })
    }

    composable("register") {
      RegisterView(authViewModel,
        lifecycleOwner,
        navController,
        dataStore)
    }

    composable("setBudget") {
      SetBudgetView(authViewModel,
        mainViewModel,
        lifecycleOwner,
        navController,
        dataStore)
    }

    composable("topupadd_screen") {
      TopUpAddView(navController,authViewModel, mainViewModel)  // Replace with your actual TopUpAddView composable
    }

    composable("login") {
      LoginView(authViewModel,
        lifecycleOwner,
        navController,
        dataStore)
    }
    composable("home") {
      HomeView(navController, authViewModel, mainViewModel)
    }

    composable("Add") {
      HomeView(navController, authViewModel, mainViewModel)
    }

    composable("insight") {
      InsightView(navController)  // Replace with your InsightView Composable
    }
    composable("lockview") {
      LockView(navController)  // Replace with your LockView Composable
    }
    composable("profileview") {
      ProfileView(navController,authViewModel, context)  // Replace with your ProfileView Composable
    }

    composable("topupeditview/{expenseId}/{expenseName}") { backStackEntry ->
      // Retrieve the arguments
      val expenseId = backStackEntry.arguments?.getString("expenseId") ?: "-1"
      val expenseName = backStackEntry.arguments?.getString("expenseName") ?: ""

      // Pass them to the TopUpEditView
      TopUpEditView(navController, authViewModel, mainViewModel, expenseId.toInt(), expenseName)
    }

  }

  // Respond to changes in isLoggedIn
  LaunchedEffect(isLoggedIn) {
    isLoggedIn?.let {
      if (it) {
        navController.navigate("home") {
          popUpTo("login") { inclusive = true }
        }
      } else {
        navController.navigate("getStarted")
      }
    }
  }
}



//@Composable
//fun MovieAppsRoute(authViewModel: AuthViewModel) {
//  val navController = rememberNavController()
//  val isLoggedIn by authViewModel.isLoggedIn.observeAsState()
//
//  val startDestination = if (isLoggedIn == true) {
//    "home"
//  } else {
//    "getStarted"
//  }
//
//  NavHost(navController = navController, startDestination = startDestination) {
//    composable("getStarted") {
//      GetStartedView(onGetStartedClicked = { navController.navigate("login") })
//    }
//    composable("login") {
//      // Assuming LoginView requires these parameters
//      LoginView(authViewModel, navController)
//    }
//    composable("home") {
//      // Directly using the imported HomeView
//      HomeView(navController)
//    }
//  }
//}



@Composable
fun AppContent() {
  val authViewModel: AuthViewModel = viewModel()
  val mainViewModel: MainViewModel = viewModel()

  // Obtain the lifecycleOwner
  val lifecycleOwner = LocalLifecycleOwner.current

  // Create or obtain an instance of DataStoreManager
  val context = LocalContext.current
  val dataStore = DataStoreManager(context)
  MovieAppsRoute(authViewModel, dataStore, lifecycleOwner, context,mainViewModel)
}

