package com.elflin.movieapps.ui.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elflin.movieapps.R
import com.elflin.movieapps.viewmodel.AuthViewModel



@Composable
fun ProfileView(navController: NavController, authViewModel: AuthViewModel, context: Context){

    Column {
        TopBar5(navController = navController)
        ProfileBar(authViewModel, context)

        Spacer(modifier = Modifier.height(320.dp))

        NavBar3(navController)
    }
}

@Composable
fun TopBar5(navController: NavController) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 40.dp)
    ){

        Image(

            painter = painterResource(id = R.drawable.chevron_left_line_2),
            contentDescription = "",
            modifier = Modifier
                .size(25.dp)
                .aspectRatio(1f)
                .fillMaxHeight()
                .clickable {
                    navController.popBackStack()
                }
        )


        Text(
            text = "Profile",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 130.dp)
        )
    }
}

@Composable
fun ProfileBar(authViewModel: AuthViewModel, context: Context){
    val userName by authViewModel.userName.observeAsState("")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Image Compose",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(shape = RoundedCornerShape(100.dp))
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = userName ,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 4.dp, top = 12.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Personal Account",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(top = 6.dp, bottom = 20.dp)
                .align(Alignment.CenterHorizontally)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {

            Text(
                text = "Accounts",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(bottom = 4.dp, top = 12.dp, start = 6.dp)
            )


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_5),
                    contentDescription = "Image Compose",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(shape = RoundedCornerShape(100.dp))
                )

                Text(
                    text = "Personal Details",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_4),
                contentDescription = "Image Compose",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = RoundedCornerShape(100.dp))
                    .clickable { authViewModel.logoutuser(context)}
            )

            Text(
                text = "Log Out",
                fontSize = 14.sp,
                color = Color.Red,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }
    }
}
@Composable
fun NavBar3(navController: NavController) {

    Row(

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,

        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)

    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            Image(

                painter = painterResource(id = R.drawable.baseline_home_23),
                contentDescription = "",
                modifier = Modifier
                    .clickable { navController.navigate("home") }
                    .size(25.dp)
                    .aspectRatio(1f)
                    .fillMaxHeight()
            )
            Text(
                text = "Home",
                fontSize = 11.sp,
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 5.dp)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            Image(

                painter = painterResource(id = R.drawable.baseline_insert_chart_outlined_24),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
                    .clickable { navController.navigate("insight") }
                    .aspectRatio(1f)
                    .fillMaxHeight()
            )
            Text(
                text = "Insights",
                color = Color.Gray,
                fontSize = 11.sp,
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 5.dp)
            )
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            Image(

                painter = painterResource(id = R.drawable.baseline_lock_24),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
                    .aspectRatio(1f)
                    .clickable { navController.navigate("lockview") }
                    .fillMaxHeight()
                    .clickable {
                        navController.navigate("lockview")
                    }
            )
            Text(
                text = "Lock",
                color = Color.Gray,
                fontSize = 11.sp,
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 5.dp)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            Image(

                painter = painterResource(id = R.drawable.baseline_person_23),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
                    .aspectRatio(1f)
                    .fillMaxHeight()
                    .clickable { navController.navigate("profileview") }
                    .clickable {
                        navController.navigate("ProfileView")
                    }
            )

            Text(
                text = "Profile",
                color = Color.Gray,
                fontSize = 11.sp,
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 5.dp)
            )
        }
    }
}

