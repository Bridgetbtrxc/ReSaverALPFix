package com.elflin.movieapps.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.elflin.movieapps.R
import com.elflin.movieapps.viewmodel.AuthViewModel

@Composable
fun GetStartedView(authViewModel: AuthViewModel? = null,
                   lifecycleOwner: LifecycleOwner? = null,
                   navController: NavController? = null,
                   onGetStartedClicked: () -> Unit = {}) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0XE5E5E5)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {


            Spacer(modifier = Modifier.padding(bottom = 10.dp))

            Image(
                painter = painterResource(id = R.drawable.pig_removebg_preview),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Text(
                    text = "The Modern Way Your Money",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 31.sp,
                    modifier = Modifier.padding(
                        top = 50.dp,
                        start = 20.dp,
                        end = 20.dp
                    )
                )

                Spacer(modifier = Modifier.padding(bottom = 18.dp))

                Text(
                    text = "Save your money by tracking and prevent impulsive buying",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp
                    ),
                    style = TextStyle(
                        lineHeight = 26.sp
                    )
                )

                Spacer(modifier = Modifier.padding(bottom = 60.dp))

                Button(
                    onClick = onGetStartedClicked, // Correctly assigned here
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD2F801)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .padding(start = 20.dp, end = 20.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Get Started",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }


            }
        }
    }

}

@Composable
fun AppContent3() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val lifecycleOwner = LocalLifecycleOwner.current

    GetStartedView(authViewModel, lifecycleOwner, navController)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GetStartedPreview() {

    GetStartedView()
}