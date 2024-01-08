package com.elflin.movieapps.ui.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elflin.movieapps.R
import com.elflin.movieapps.Repository.AuthRepository
import com.elflin.movieapps.helper.Const
import com.elflin.movieapps.viewmodel.AuthViewModel
import com.elflin.movieapps.viewmodel.MainViewModel


@Composable
fun TopUpAddView(navController: NavController, authViewModel: AuthViewModel, mainViewModel: MainViewModel) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val token by authViewModel.token.observeAsState()

    var expenseName by rememberSaveable { mutableStateOf("") }
    var expenseAmount by rememberSaveable { mutableStateOf("") }
    var selectedCategory by rememberSaveable { mutableStateOf("") }
    var categoryId by remember { mutableStateOf(-1) }

    Column(modifier = Modifier.background(color = Color(0xFF5A45FE))) {
        TopBar(navController)
        CustomField(label = "Expense Name", value = expenseName){
            expenseName = it
        }
        CustomField2(label = "Amount", amount = expenseAmount,){
            expenseAmount = it
        }

        CustomDropdownMenu(mainViewModel, categories = listOf("Needs", "Wants", "Savings"), selectedCategory = selectedCategory, onCategorySelected = { category ->
            selectedCategory = category
            Log.e("sssaaa", selectedCategory)
            categoryId = mainViewModel.getCategoryId(category)
        })

        Text(
            text = "Continue",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(bottom = 1.dp)
                .fillMaxWidth()
                .background(Color(0xFFD2F801), RoundedCornerShape(16.dp))
                .padding(horizontal = 135.dp, vertical = 15.dp)
                .clickable {
                        if (categoryId != -1) {
                            mainViewModel.AddExpense(
                                "Bearer " + AuthRepository.ACCESS_TOKEN,
                                expenseName,
                                expenseAmount.toInt(),
                                categoryId
                            )
                            Log.e("testing", AuthRepository.ACCESS_TOKEN + categoryId)
                        }
                }
        )
    }
}


@Composable
fun TopBar(navController: NavController) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 40.dp)
    ){

        Image(

            painter = painterResource(id = R.drawable.chevron_left_line),
            contentDescription = "",
            modifier = Modifier
                .size(25.dp)
                .aspectRatio(1f)
                .fillMaxHeight()
                .clickable {
                    navController.popBackStack()
                }
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = "Add Transaction",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
        )

        Spacer(modifier = Modifier.width(5.dp))

        Image(

            painter = painterResource(id = R.drawable.question_circle_line),
            contentDescription = "",
            modifier = Modifier
                .size(25.dp)
                .aspectRatio(1f)
                .fillMaxHeight()
        )

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionNameDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    navController: NavController
) {
    var transactionName by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Input Transaction Name") },
        text = {
            OutlinedTextField(

                value = transactionName,
                onValueChange = {

                    transactionName = it
                },
                label = { Text("Transaction Name") }
            )
        },
        confirmButton = {
            TextButton(
                onClick = {

                    onConfirm(transactionName)
                    navController.popBackStack()

                }
            ) {
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





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextFieldWithImageAndDropdown(
    value: String,
    onValueChanged: (String) -> Unit,
    currencyOptions: List<Pair<String, Int>>,
    selectedCurrency: Pair<String, Int>,
    onCurrencySelected: (Pair<String, Int>) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)

            ) {
                Text(
                    text = selectedCurrency.first,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .weight(1f)
                )
                DropdownIcon(
                    currencyOptions = currencyOptions,
                    selectedCurrency = selectedCurrency,
                    onCurrencySelected = onCurrencySelected,
                    modifier = Modifier.size(25.dp).align(Alignment.CenterVertically)
                )

            }
        },
        keyboardOptions = keyboardOptions,
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Black
        )
    )
}

@Composable
fun DropdownIcon(
    currencyOptions: List<Pair<String, Int>>,
    selectedCurrency: Pair<String, Int>,
    onCurrencySelected: (Pair<String, Int>) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .clickable { expanded = !expanded }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                painter = painterResource(id = selectedCurrency.second),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
            Text(
                text = selectedCurrency.first,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 16.sp,
            )

        }

        if (expanded) {
            Box(
                modifier = Modifier
                    .background(color = Color.White)
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(16.dp)),
            ) {
                currencyOptions.forEach { currency ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onCurrencySelected(currency)
                                expanded = false
                            }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = currency.second),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                        Text(
                            text = currency.first,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                        )
                    }
                    Divider(color = Color.Gray, thickness = 1.dp)
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdownMenu(
    viewModel: MainViewModel,
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val onCategorySelectedState by rememberUpdatedState(onCategorySelected)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(16.dp))
            .padding(vertical = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = selectedCategory, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
        }

        if (expanded) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(16.dp))
            ) {
                Column {
                    categories.forEach { category ->
                        Text(
                            text = category,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    expanded = false
                                    onCategorySelectedState(category)
                                }
                                .padding(16.dp)
                        )
                        Divider(color = Color.Gray, thickness = 1.dp)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomField(
    label: String, value: String,  onValueChange: (String) -> Unit
) {

    Column(
        modifier = Modifier

    ) {
        TextField(
            value = value,
            onValueChange = {
                onValueChange(it) },
            label = { Text("Expense Name") },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                placeholderColor = Color.Gray,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(16.dp))
                .padding(vertical = 3.dp)
                .background(color = Color.Transparent, shape = RoundedCornerShape(4.dp))
                .padding(horizontal = 16.dp)
        )
        // You can add other UI elements here as needed
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomField2(
    label: String, amount: String, onValueChange: (String) -> Unit
) {


    Column(
        modifier = Modifier

    ) {
        TextField(
            value = amount,
            onValueChange = { onValueChange(it) },
            label = { Text("Expense Amount") },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                placeholderColor = Color.Gray,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(16.dp))
                .padding(vertical = 3.dp)
                .background(color = Color.Transparent, shape = RoundedCornerShape(4.dp))
                .padding(horizontal = 16.dp)
        )
        // You can add other UI elements here as needed
    }
}

//    @Composable
//fun Calculator(
//    context: Context, coroutineScope: CoroutineScope, navController: NavController,
//    mainViewModel: MainViewModel,
//    expenseName: String, expenseAmount: Int, selectedCategory:String, token:String) {
//
//    val categories = listOf("Needs", "Wants", "Savings")
//    var input_number by rememberSaveable { mutableStateOf("") }
//    var expanded by remember { mutableStateOf(false) }
//    var selectedCategory by remember { mutableStateOf(categories.first()) }
//    var showDialog by remember { mutableStateOf(false) }
//    var transactionName by remember { mutableStateOf("") }
//    Box(
//        modifier = Modifier
//            .fillMaxHeight()
//            .fillMaxWidth()
//            .background(
//                color = Color.White,
//                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
//            )
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(horizontal = 25.dp, vertical = 30.dp)
//                .fillMaxWidth()
//        ) {
//            Text(
//                text = "Expense Name",
//                fontSize = 16.sp,
//                modifier = Modifier
//                    .padding(vertical = 12.dp)
//            )
//
//
//
//
//            Text(
//                text = "Amount",
//                fontSize = 16.sp,
//            )
//
//            CustomTextFieldWithImageAndDropdown(
//                value = input_number,
//                onValueChanged = { input_number = it },
//                currencyOptions = currencyOptions,
//                selectedCurrency = selectedCurrency,
//                onCurrencySelected = { selectedCurrency = it },
//                keyboardOptions = KeyboardOptions.Default.copy(
//                    keyboardType = KeyboardType.Number,
//                    imeAction = ImeAction.Done
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp)
//            )
//
//
//            Text(
//                text = "Category",
//                fontSize = 16.sp,
//                modifier = Modifier
//                    .padding(vertical = 12.dp)
//            )
//
//            CustomDropdownMenu(
//                categories = categories,
//                selectedCategory = selectedCategory,
//                onCategorySelected = { selectedCategory = it },
//                modifier = Modifier.fillMaxWidth(),
//                viewModel = mainViewModel
//            )
//
//
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text(
//                text = "Continue",
//                fontSize = 15.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black,
//                modifier = Modifier
//                    .align(alignment = Alignment.CenterHorizontally)
//                    .padding(bottom = 1.dp)
//                    .fillMaxWidth()
//                    .background(Color(0xFFD2F801), RoundedCornerShape(16.dp))
//                    .padding(horizontal = 135.dp, vertical = 15.dp)
//                    .clickable {
//
//                        showDialog = false
//                        val categoryId = getCategoryID(categoryName)
//
//                        mainViewModel.AddExpense(expenseName, expenseAmount, categoryId, token)
//                    }
//            )
//
//            if (showDialog) {
//                TransactionNameDialog(
//                    onDismiss = { showDialog = false },
//                    onConfirm = { name ->
//                        // Lakukan sesuatu dengan nama transaksi yang diinput
//                        transactionName = name
//                        // Tutup dialog setelah konfirmasi
//                        showDialog = false
//                    },
//                    navController = navController
//                )
//            }
//        }
//    }
//}







