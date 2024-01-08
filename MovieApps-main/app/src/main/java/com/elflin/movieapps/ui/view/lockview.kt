package com.elflin.movieapps.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.elflin.movieapps.R
import com.elflin.movieapps.ui.theme.MovieAppsTheme




@Composable
fun LockView(navController: NavController) {
    Column {
        TopBar3(navController)

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            LockBarLock()
        }


      NavBar2(navController)
//        NavBar2(
//           modifier = Modifier
//                .fillMaxWidth()
//                .height(70.dp)
//                .padding(bottom = 16.dp),
//            navController = navController
//        )
    }
}



@Composable
fun TopBar3(navController: NavController) {

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
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = "My Lock",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
        )

        Spacer(modifier = Modifier.width(5.dp))

        Image(

            painter = painterResource(id = R.drawable.create),
            contentDescription = "",
            modifier = Modifier
                .size(25.dp)
                .aspectRatio(1f)
                .fillMaxHeight()
                .clickable {
                    navController.navigate("lockadd_screen")
                }
        )

    }
}

@Composable
fun LockBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.green_bushes_for_the_background),
                contentDescription = "Image Compose",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(width = 275.dp, height = 175.dp)
            )

            Text(
                text = "No Lock is available...",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun LockBarLock() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {
            item {

                Text(
                    text = "Needs",
                    fontSize = 20.sp,
                    color = Color(0xFF646464),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(

                            start = 15.dp
                        )
                )

                Card(
                    colors = CardDefaults.cardColors(Color(0xFFFFFFFF)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 23.dp, start = 17.dp, end = 17.dp)
                        .border(0.5.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp) // Add padding to the whole content
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.needs),
                            contentDescription = "Image Compose",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(shape = RoundedCornerShape(100.dp))
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp) // Add bottom padding to the first row
                                    .height(IntrinsicSize.Min)
                                    .wrapContentHeight(Alignment.Top),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Text(
                                    text = "Dior carlin",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = "Rp. 50.000",
                                    fontSize = 15.sp
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(IntrinsicSize.Min)
                                    .wrapContentHeight(Alignment.Top),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Text(
                                    text = "Due : 17 November 2023 ",
                                    fontSize = 12.sp,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun NavBar2(navController: NavController) {

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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LockPreview1() {
    val navController = rememberNavController()
    MovieAppsTheme {
        LockView(navController = navController)
    }
}

