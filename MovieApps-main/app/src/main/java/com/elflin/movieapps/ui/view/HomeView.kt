package com.elflin.movieapps.ui.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elflin.movieapps.R
import com.elflin.movieapps.model.Expense
import com.elflin.movieapps.viewmodel.AuthViewModel
import com.elflin.movieapps.viewmodel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController,
    authViewModel: AuthViewModel,
    mainViewModel: MainViewModel
) {

    val totalSpendingState = mainViewModel.totalSpending.observeAsState("")

    val budgetChecker by mainViewModel.budgetChecker.observeAsState()
    val expenses by mainViewModel.expenses.observeAsState(initial = emptyList())
    var activeDeleteId by remember { mutableStateOf<Int?>(null) }
    var showDeleteConfirmationDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {

        mainViewModel.loadExpenses()
        mainViewModel.setToken()
    }


    // Function to handle delete confirmation
    val confirmDelete = { id: Int ->
        activeDeleteId = id
        showDeleteConfirmationDialog = true
        Log.d("HomeView", "Confirm delete for ID: $id")
    }

    ConfirmDeleteDialog(
        showDialog = showDeleteConfirmationDialog,
        onConfirm = {
            activeDeleteId?.let { mainViewModel.deleteExpense(it) }
            showDeleteConfirmationDialog = false
        },
        onDismiss = {
            showDeleteConfirmationDialog = false
        }
    )

    Scaffold(
        bottomBar = { NavBar(navController) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Menu1(navController, authViewModel,totalSpendingState)
                }

                items(expenses) { expense ->
                    TransactionCard(
                        expense = expense,
                        onDeleteConfirm = { id ->
                            confirmDelete(id) // Assuming confirmDelete is a function that handles deletion
                        },
                        onExpenseSelect = { expenseName ->
                            navController.navigate("topupeditview/${expense.id}/${expense.name}")
                            // Navigate with expense name as argument
                        }

                    )
                }
            }
        }
    }

}

@Composable
fun Menu1(
    navController: NavController, authViewModel: AuthViewModel,
    totalSpendingState: State<String>
) {
    val userName by authViewModel.userName.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(top = 10.dp, start = 7.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_circle_svgrepo_com),
                contentDescription = "Image Compose",
                contentScale = ContentScale.Crop,

                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = RoundedCornerShape(100.dp))
            )

            Spacer(modifier = Modifier.width(15.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = "Hi ! ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp) // Reduced bottom padding
                )
                Text(
                    text = "Good Morning!",
                    fontSize = 14.sp,
                )
            }

            Image(
                painter = painterResource(id = R.drawable.icon_2),
                contentDescription = "Image Compose",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(50.dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 35.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .padding(start = 7.dp)
            ) {
                Text(
                    text = "Total Spending This Month",
                    fontSize = 14.sp
                )
                Image(
                    painter = painterResource(id = R.drawable.eye_line),
                    contentDescription = "Image Compose",
                    modifier = Modifier
                        .size(25.dp)
                )

                Text(
                    text = "Modify Budget",
                    color = Color.Blue,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable { navController.navigate("setBudget") }
                )
            }
            Text(
                text = "Rp "+ totalSpendingState.value,
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 4.dp, top = 5.dp, start = 7.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 30.dp)
            ) {

                Text(
                    text = "Transaction",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .weight(1f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp)
            ) {
                ClickableRow(navController)

                Spacer(modifier = Modifier.width(8.dp))

//                ClickableRow1(onDeleteClicked = {})
                Spacer(modifier = Modifier.width(8.dp))

//                ClickableRow2(navController)
            }
        }
    }

}

@Composable
fun DeleteConfirmationDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
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


//fun TransactionList(
//    transaction: List<transaction_Model>,
//    onTransactionRemove: (transaction_Model) -> Unit
//) {
//    Column(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        transaction.forEach { transaction ->
//            TransactionCard(transaction, onTransactionRemove)
//        }
//    }
//}

@Composable
fun TransactionCard(
    expense: Expense,
    onDeleteConfirm: (Int) -> Unit,
    onExpenseSelect: (String) -> Unit
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
                        text = "Name: ${expense.name}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    //need want savings
                    //insight perlu category id
                    //monthly,


                    Text(
                        text = "${expense.amount}",
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
                        text = "${expense.date}",
                        fontSize = 15.sp,
                    )

                    Text(
                        text = "${expense.categoryId}",
                        fontSize = 15.sp
                    )

                    if (isDeleteVisible) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_restore_from_trash_24),
                            contentDescription = "Delete",
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    onDeleteConfirm(expense.id)
                                }
                        )

                        Image(
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = "Edit",
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    onExpenseSelect(expense.name)
                                }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ConfirmDeleteDialog(
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
fun NavBar(navController: NavController) {

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
        ) {

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
        ) {

            Image(

                painter = painterResource(id = R.drawable.chart_svgrepo_com),
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
        ) {

            Image(

                painter = painterResource(id = R.drawable.baseline_lock_23),
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
        ) {

            Image(

                painter = painterResource(id = R.drawable.baseline_person_24),
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



@Composable
fun ClickableRow(navController: NavController) {
    var isClicked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .background(
                color = if (isClicked) Color.Gray else Color(0xFFD2F801),
                shape = RoundedCornerShape(30.dp)
            )
            .padding(vertical = 8.dp, horizontal = 10.dp)
            .clickable {
                isClicked = !isClicked

                if (isClicked) {

                    navController.navigate("topupadd_screen")
                }
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.plus),
            contentDescription = "Image Compose",
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(15.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = "Add",
            fontWeight = FontWeight.SemiBold,
            color = if (isClicked) Color.White else Color.Black,
            fontSize = 11.sp,
            modifier = Modifier
                .clickable {
                    navController.navigate("topupadd_screen")
                }
                .padding(horizontal = 6.dp)
                .fillMaxHeight()

        )
    }
}

@Composable
fun ClickableRow1(

    onDeleteClicked: () -> Unit
) {
    var isClicked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .background(
                color = if (isClicked) Color.Gray else Color(0xFFD2F801),
                shape = RoundedCornerShape(30.dp)
            )
            .padding(vertical = 8.dp, horizontal = 10.dp)
            .clickable {
                isClicked = !isClicked
                onDeleteClicked()
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.minus),
            contentDescription = "Image Compose",
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(15.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = "Delete",
            fontWeight = FontWeight.SemiBold,
            color = if (isClicked) Color.White else Color.Black,
            fontSize = 11.sp,
            modifier = Modifier
                .padding(horizontal = 6.dp)


        )
    }
}

@Composable
fun ClickableRow2(navController: NavController) {
    var isClicked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .background(
                color = if (isClicked) Color.Gray else Color(0xFFD2F801),
                shape = RoundedCornerShape(30.dp)
            )
            .padding(vertical = 8.dp, horizontal = 10.dp)
            .clickable {
                isClicked = !isClicked

                if (isClicked) {

                    navController.navigate("TopUpEditView")
                }
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.edit),
            contentDescription = "Image Compose",
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(15.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = "Edit",
            fontWeight = FontWeight.SemiBold,
            color = if (isClicked) Color.White else Color.Black,
            fontSize = 11.sp,
            modifier = Modifier
                .padding(horizontal = 6.dp)
        )
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun HomeViewPreview() {
//    val navController = rememberNavController()
//    MovieAppsTheme {
//        HomeView(navController = navController)
//    }
//}

