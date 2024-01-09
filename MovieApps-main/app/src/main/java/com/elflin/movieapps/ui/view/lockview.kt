package com.elflin.movieapps.ui.view

import android.util.Log
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.elflin.movieapps.model.Expense
import com.elflin.movieapps.model.Wishlist
import com.elflin.movieapps.model.WishlistList
import com.elflin.movieapps.ui.theme.MovieAppsTheme
import com.elflin.movieapps.viewmodel.MainViewModel


@Composable
fun LockView(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    // Observing LiveData from MainViewModel
    val wishlistList by mainViewModel.wishlistList.observeAsState(initial = emptyList())

    var activeDeleteId by remember { mutableStateOf<Int?>(null) }
    var showDeleteConfirmationDialog by remember { mutableStateOf(false) }

    val confirmDelete = { id: Int ->
        activeDeleteId = id
        showDeleteConfirmationDialog = true
        Log.d("LockView", "Confirm delete for ID: $id")
    }


    LaunchedEffect(Unit) {
        mainViewModel.loadWishlist()
        mainViewModel.getSavingsBudgetInsight()
        mainViewModel.getWantsBudgetInsight()
        mainViewModel.getSavingsBudgetInsight()
    }

    ConfirmDeleteDialog(
        showDialog = showDeleteConfirmationDialog,
        onConfirm = {
            activeDeleteId?.let { mainViewModel.deleteWishlist(it) }
            showDeleteConfirmationDialog = false
        },
        onDismiss = {
            showDeleteConfirmationDialog = false
        }
    )


    Column {
        TopBar3(navController)

        // Using Box as a container for the list
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            LazyColumn {
                items(wishlistList) { wishlist ->
                    TransactionCard(
                        wishlist = wishlist,
                        onDeleteConfirm = { id ->

                            confirmDelete(id)
                        },
                        onWishlistSelect = { name ->
                            // Implement expense selection logic here
                        }
                    )
                }
            }
        }

        NavBar(navController)
    }
}


@Composable
fun ConfirmDeleteDialog2(
    showDialog: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text("Confirm Delete") },
            text = { Text("Are you sure you want to delete this expense?") },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        )
    }
}


@Composable
fun DeleteConfirmationDialog2(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Confirm Deletion") },
        text = { Text("Are you sure you want to delete this item?") },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
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
                .clickable {  navController.navigateUp() }
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
                    navController.navigate("addLock")
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
fun TransactionCard(
    wishlist: Wishlist,
    onDeleteConfirm: (Int) -> Unit,
    onWishlistSelect: (String) -> Unit
) {
    var isDeleteVisible by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(Color(0xFFFFFFFF)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 23.dp, start = 17.dp, end = 17.dp)
            .border(0.2.dp, Color.Black, shape = RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isDeleteVisible = !isDeleteVisible
                }
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
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Name: ${wishlist.name}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    //need want savings
                    //insight perlu category id
                    //monthly,


                    Text(
                        text = "${wishlist.price}",
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
                        text = "${wishlist.status}",
                        fontSize = 15.sp,
                    )


                    if (isDeleteVisible) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_restore_from_trash_24),
                            contentDescription = "Delete",
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    onDeleteConfirm(wishlist.id)
                                }
                        )

                        Image(
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = "Edit",
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
//                                    onExpenseSelect(expense.name)
                                }
                        )
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


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun LockPreview1() {
//    val navController = rememberNavController()
//    MovieAppsTheme {
//        LockView(navController = navController)
//    }
//}
//
