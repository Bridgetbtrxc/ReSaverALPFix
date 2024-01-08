package com.elflin.movieapps.ui.view
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff
//import androidx.compose.material3.Button
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.SnackbarHost
//import androidx.compose.material3.SnackbarHostState
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.LifecycleOwner
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.elflin.movieapps.R
//import com.elflin.movieapps.viewmodel.AuthViewModel
//import kotlinx.coroutines.launch
//import java.util.regex.Pattern
//
//
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun LoginView(
////    authViewModel: AuthViewModel,
////    lifecycleOwner: LifecycleOwner,
////    navController: NavHostController
//){
//    val context = LocalContext.current
//    val loginResult by authViewModel.login.observeAsState()
//
//    val token = remember { mutableStateOf("") }
//
//    val isLoggedIn by authViewModel.isLoggedIn.observeAsState()
//    val userEmail by authViewModel.userEmail.observeAsState()
//
//
//    var name by rememberSaveable { mutableStateOf("") }
//    var phone by rememberSaveable { mutableStateOf("") }
//    var age by rememberSaveable { mutableStateOf("") }
//    var email by rememberSaveable { mutableStateOf("") }
//    var password by rememberSaveable { mutableStateOf("") }
//
//    var isEmailValid by rememberSaveable { mutableStateOf(true) }
//    var isPasswordValid by rememberSaveable { mutableStateOf(true) }
//
//    val snackbarHostState = remember {SnackbarHostState()}
//    val scope = rememberCoroutineScope()
//
//    LaunchedEffect(key1 = isLoggedIn) {
//        if (isLoggedIn == true) {
//            token.value = authViewModel.getToken(context)
//        }
//    }
//
//    if (isLoggedIn == true) {
//        Text(text = "Logged in as: $userEmail")
//        Text(text = "Token: ${token.value}") // Displaying the token
//        Button(
//            onClick = {
//                authViewModel.logoutuser(context)
//            },
//            modifier = Modifier.padding(top = 16.dp)
//        ) {
//            Text("Logout")
//        }
//    } else {
//
//        Scaffold(
//            snackbarHost = {SnackbarHost(snackbarHostState)},
//            content = {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .verticalScroll(rememberScrollState()),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.ic_profile),
//                        contentDescription = "Profile Picture",
//                        modifier = Modifier
//                            .size(100.dp)
//                            .clip(CircleShape)
//                            .border(2.dp, Color.Gray, CircleShape)
//                    )
//                    CustomEmailField(
//                        value = email,
//                        onValueChanged = {email = it} ,
//                        text = "Email",
//                        keyboardOptions = KeyboardOptions.Default.copy(
//                            keyboardType = KeyboardType.Email,
//                            imeAction = ImeAction.Next
//                        ),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 16.dp, vertical = 8.dp),
//                        isEmailValid = isEmailValid
//                    )
//                    CustomPasswordField2(
//                        value = password,
//                        onValueChanged = {password = it} ,
//                        text = "Password",
//                        keyboardOptions = KeyboardOptions.Default.copy(
//                            keyboardType = KeyboardType.Password,
//                            imeAction = ImeAction.Done
//                        ),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 16.dp, vertical = 8.dp),
//                        isPasswordValid = isPasswordValid
//                    )
//
//                    Button(
//                        onClick = {
//                            authViewModel.loginUser(email, password, context)
//                            loginResult?.let {
//                                scope.launch {
//                                    snackbarHostState.showSnackbar(it)
//                                    if (it == "User successfully logged in") {
//
//                                    }
//                                }
//                            }
//
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 16.dp, vertical = 16.dp),
//                        enabled =email.isNotBlank()&&password.isNotBlank()
//                    )
//                    {
//                        Text(text = "Submit")
//                    }
//
//                    @Composable
//                    fun AppContent() {
//                        val navController = rememberNavController()
//                        val authViewModel: AuthViewModel = viewModel()
//                        val lifecycleOwner = LocalLifecycleOwner.current
//
//                        LoginView(authViewModel, lifecycleOwner, navController)
//                    }
//                }
//            }
//        )
//    }
//
//
//
//}
//
//// Function to validate email using regex
//fun isValidEmail2(email: String): Boolean {
//    val emailPattern = Pattern.compile(
//        "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$",
//        Pattern.CASE_INSENSITIVE
//    )
//    return emailPattern.matcher(email).matches()
//}
//
//// Function to validate password
//fun isValidPassword2(password: String): Boolean {
//    val passwordPattern = Pattern.compile(
//        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=!])(?=\\S+\$).{8,}\$"
//    )
//    return passwordPattern.matcher(password).matches()
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CustomTextField2(
//    value: String,
//    onValueChanged: (String) -> Unit,
//    text: String,
//    keyboardOptions: KeyboardOptions,
//    modifier: Modifier = Modifier
//){
//    OutlinedTextField(
//        value = value,
//        onValueChange = onValueChanged,
//        label = { Text(text = text)},
//        keyboardOptions = keyboardOptions,
//        modifier = modifier
//    )
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CustomEmailField2(
//    value: String,
//    onValueChanged: (String) -> Unit,
//    text: String,
//    keyboardOptions: KeyboardOptions,
//    modifier: Modifier = Modifier,
//    isEmailValid: Boolean
//){
//    OutlinedTextField(
//        value = value,
//        onValueChange = onValueChanged,
//        label = { Text(text = text)},
//        keyboardOptions = keyboardOptions,
//        modifier = modifier,
//        isError = !isEmailValid
//    )
//
//    if (!isEmailValid){
//        Text(
//            text = "Invalid email format",
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
//            Color.Red
//        )
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CustomPasswordField2(
//    value: String,
//    onValueChanged: (String) -> Unit,
//    text: String,
//    keyboardOptions: KeyboardOptions,
//    modifier: Modifier = Modifier,
//    isPasswordValid: Boolean
//){
//
//    var isPasswordVisible by remember { mutableStateOf(false)}
//
//    OutlinedTextField(
//        value = value,
//        onValueChange = onValueChanged,
//        label = { Text(text = text)},
//        keyboardOptions = keyboardOptions,
//        modifier = modifier,
//        isError = !isPasswordValid,
//        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
//        trailingIcon = {
//            IconButton(
//                onClick = { isPasswordVisible = !isPasswordVisible }
//            ) {
//                Icon(
//                    imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
//                    contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
//                )
//            }
//        },
//    )
//
////    if (!isPasswordValid){
////        Text(
////            text = "Password must be 8 characters long and contain uppercase, lowercase, number, and special character",
////            modifier = Modifier
////                .fillMaxWidth()
////                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
////            Color.Red
////        )
////    }
//}
//
//@Composable
//fun AppContent2() {
//    val navController = rememberNavController()
//    val authViewModel: AuthViewModel = viewModel()
//    val lifecycleOwner = LocalLifecycleOwner.current
//
//    LoginView(authViewModel, lifecycleOwner, navController)
//}
//
////@Preview(showBackground = true, showSystemUi = true, )
////@Composable
////private fun LoginPreview(){
////    val navController = rememberNavController()
////    val authViewModel: AuthViewModel = viewModel()
////    val lifecycleOwner = LocalLifecycleOwner.current
////
////    LoginView(authViewModel, lifecycleOwner, navController)
////}
//
