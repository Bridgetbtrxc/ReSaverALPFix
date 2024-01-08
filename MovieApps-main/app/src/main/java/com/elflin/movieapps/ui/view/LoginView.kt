package com.elflin.movieapps.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.elflin.movieapps.R
import com.elflin.movieapps.Repository.AuthRepository
import com.elflin.movieapps.data.DataStoreManager
import com.elflin.movieapps.helper.Const
import com.elflin.movieapps.viewmodel.AuthViewModel
import java.util.prefs.Preferences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(
    authViewModel: AuthViewModel,
    lifecycleOwner: LifecycleOwner,
    navController: NavHostController,
    dataStore: DataStoreManager
) {




    val context = LocalContext.current
    val loginResult by authViewModel.login.observeAsState()
    val token = remember { mutableStateOf("") }
    val isLoggedIn by authViewModel.isLoggedIn.observeAsState()
    val userEmail by authViewModel.userEmail.observeAsState()
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = isLoggedIn) {
        if (isLoggedIn == true) {
            token.value = authViewModel.getToken(context)
            AuthRepository.ACCESS_TOKEN = token.value
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp),
        verticalArrangement = Arrangement.Center
    )
    {
        Image(
            painter = painterResource(R.drawable.r_pict),
            contentDescription = null
        )

        Spacer(modifier = Modifier.padding(bottom = 28.dp))

        Text(
            text = "Welcome back",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(bottom = 14.dp))

        Text(
            text = "Sign in to your account",
            fontSize = 18.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.padding(bottom = 24.dp))

        Text(
            text = "Your email",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.padding(bottom = 12.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.LightGray,
                textColor = Color.DarkGray,
                focusedBorderColor = Color(0XFFFAFAFA),
                unfocusedBorderColor = Color(0XFFFAFAFA)
            ),
            placeholder = {
                Text(
                    text = "",
                    color = Color.Gray
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
        )


        Spacer(modifier = Modifier.padding(bottom = 12.dp))

        Text(
            text = "Password",
            fontSize = 16.sp,
            color = Color.Gray,
        )

        Spacer(modifier = Modifier.padding(bottom = 12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = if (passwordVisibility) KeyboardType.Text else KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.LightGray,
                textColor = Color.DarkGray,
                focusedBorderColor = Color(0XFFFAFAFA),
                unfocusedBorderColor = Color(0XFFFAFAFA)
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {

                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        painter = if (passwordVisibility) painterResource(id = R.drawable.visibility) else painterResource(id = R.drawable.visibilityoff),
                        contentDescription = "Visibility Icon",
                        modifier = Modifier.height(24.dp).width(24.dp)
                    )
                }
            }
        )

//        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
//            Icon(
//                imageVector = if (passwordVisibility) Icons.Default.VisibilityOff else Icons.Default.Visibility,
//                contentDescription = "Toggle Password Visibility"
//            )
//        }


        Spacer(modifier = Modifier.padding(bottom = 20.dp))

        Button(
            onClick = {
                authViewModel.loginUser(email, password, context, navController)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD2F801)),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Sign In",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.padding(bottom = 6.dp))

        Button(
            onClick = { navController.navigate("register") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Dont have an account? Click here",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Blue
            )
        }

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Forgot Password?",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Blue
            )
        }

        Spacer(modifier = Modifier.padding(vertical = 32.dp))

        Text(
            text = "Or sign in with",
            fontSize = 16.sp,
            color = Color(0xFF737373),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                border = BorderStroke(width = 1.dp, color = Color.LightGray),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.width(155.5.dp).height(56.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(R.drawable.apple),
                        contentDescription = null
                    )

                    Spacer(Modifier.padding(end = 8.dp))

                    Text(
                        text = "Apple",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
            }

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                border = BorderStroke(width = 1.dp, color = Color.LightGray),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.width(155.5.dp).height(56.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(R.drawable.google),
                        contentDescription = null
                    )

                    Spacer(Modifier.padding(end = 8.dp))

                    Text(
                        text = "Google",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }

            }


        }
    }
}

//@Composable
//fun AppContent2() {
//    val navController = rememberNavController()
//    val authViewModel: AuthViewModel = viewModel()
//    val lifecycleOwner = LocalLifecycleOwner.current
//
//    LoginView(authViewModel, lifecycleOwner, navController)
//}

//@Preview(showBackground = true)
//@Composable
//fun LoginPreview() {
//    return LoginView()
//}
