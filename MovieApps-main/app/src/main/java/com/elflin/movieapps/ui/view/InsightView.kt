package com.elflin.movieapps.ui.view

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elflin.movieapps.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsightView(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .background(color = Color(0xfff3f4f6), shape = RoundedCornerShape(50.dp))
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = Color.Black,
                    )
                }

            }

            Text(
                text = "Insights",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )

            Box(
                modifier = Modifier
                    .background(color = Color(0xfff3f4f6), shape = RoundedCornerShape(50.dp))
            ) {
                IconButton(
                    onClick = { /*TODO*/ },

                    ) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.graphicsLayer(rotationZ = 90f)
                    )
                }

            }

        }

        Spacer(modifier = Modifier.padding(vertical = 12.dp))

        Card(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF5A45FE)
            ),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Total Spending",
                        color = Color.LightGray,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.padding(vertical = 4.dp))

                    Text(
                        text = "$3,765.00",
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )


                }

                Image(
                    painter = painterResource(id = R.drawable.paper_fold_text_line),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .size(42.dp)


                )
            }

        }

        Spacer(modifier = Modifier.padding(bottom = 16.dp))

        Text(
            text = "Categories",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.padding(bottom = 16.dp))

        Card(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
            ),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = null,
                    modifier = Modifier.size(52.dp)
                )

                Column(
                    modifier = Modifier.padding(start = 20.dp, end = 30.dp)
                ) {

                    Text(
                        text = "Needs",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.padding(bottom = 6.dp))

                    Text(
                        text = "Rp 1,345,000 of Rp 1,500,000",
                        fontSize = 14.sp,
                        color = Color.LightGray
                    )

                }


                Text(
                    text = "67%",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

        }

        Spacer(modifier = Modifier.padding(bottom = 16.dp))

        Card(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
            ),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Image(
                    painter = painterResource(id = R.drawable.icon__1_),
                    contentDescription = null,
                    modifier = Modifier.size(52.dp)
                )

                Column(
                    modifier = Modifier.padding(start = 20.dp, end = 30.dp)
                ) {

                    Text(
                        text = "Wants",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.padding(bottom = 6.dp))

                    Text(
                        text = "Rp 500,000 of Rp 1,500,000",
                        fontSize = 14.sp,
                        color = Color.LightGray
                    )

                }


                Text(
                    text = "33%",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

        }

        Spacer(modifier = Modifier.padding(bottom = 16.dp))

        Card(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
            ),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Image(
                    painter = painterResource(id = R.drawable.icon__2_),
                    contentDescription = null,
                    modifier = Modifier.size(52.dp)
                )

                Column(
                    modifier = Modifier.padding(start = 20.dp, end = 30.dp)
                ) {

                    Text(
                        text = "Savings",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.padding(bottom = 6.dp))

                    Text(
                        text = "Rp 500,000 of Rp 550,000",
                        fontSize = 14.sp,
                        color = Color.LightGray
                    )

                }

                Text(
                    text = "90%",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

        }

        Spacer(modifier = Modifier.padding(bottom = 16.dp))

        Text(
            text = "My Locks",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.padding(bottom = 16.dp))

        Card(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(92.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
            ),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 16.dp)
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "You Saved Rp 500,000",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.padding(bottom = 4.dp))

                    Text(
                        text = "3 out of 5 attempts at impulsive buying were sucessfully prevented",
                        fontSize = 12.sp,
                        color = Color.LightGray
                    )
                }

                Image(
                    painter = painterResource(R.drawable.lock_line),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(0.15f)
                        .size(24.dp)
                )

            }

        }

        Spacer(modifier = Modifier.padding(bottom = 18.dp))

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
}}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun InsightPreview() {
//
//    return InsightView(navController)
//}