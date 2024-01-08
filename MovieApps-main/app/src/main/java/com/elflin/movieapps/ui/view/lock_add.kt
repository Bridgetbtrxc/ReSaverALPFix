package com.example.alp_resaver.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.elflin.movieapps.R
import com.elflin.movieapps.ui.theme.MovieAppsTheme
import kotlinx.coroutines.CoroutineScope
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Lock2(navController: NavController){

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .background(
                color =  Color(0xFF5A45FE)
            )
    ) {
        TopBar4(navController)
        Calculator3(context = context, coroutineScope = coroutineScope)
    }
}



@Composable
fun TopBar4(navController: NavController) {

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
                    navController.navigate("lockview_screen")
                }
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = "Add Lock",
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calculator3(context: Context, coroutineScope: CoroutineScope) {

    var input_number by rememberSaveable { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var isTextFieldClicked by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var selectedTime by remember { mutableStateOf(LocalTime.now()) }
    var selectedDateTime by remember { mutableStateOf(LocalDateTime.now()) }

    val currencyOptions = listOf(
        Pair("Rp", R.drawable.indonesiaaah)
    )

    var selectedCurrency by remember { mutableStateOf(currencyOptions.first()) }
    val categories = listOf("Leisure", "Figma", "Groceries")
    var selectedCategory by remember { mutableStateOf(categories.first()) }



    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 25.dp, vertical = 30.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Amount",
                fontSize = 16.sp,
            )

            CustomTextFieldWithImageAndDropdown3(
                value = input_number,
                onValueChanged = {
                    input_number = it
                    isTextFieldClicked = true
                },
                currencyOptions = currencyOptions,
                selectedCurrency = selectedCurrency,
                onCurrencySelected = { selectedCurrency = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Text(
                text = "Locks End",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(vertical = 12.dp)
            )

            DateTimePicker()


            Text(
                text = "Category",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(vertical = 12.dp)
            )

            CustomDropdownMenu3(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

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
            )
        }
    }
}




@Composable
fun CalculatorKey3(text: String, onClick: () -> Unit) {
    Box(

        modifier = Modifier
            .height(65.dp)
            .width(115.dp)
            .clickable { onClick() }
            .padding(8.dp)
            .background(Color(0xC4F3F3F3), shape = RoundedCornerShape(10.dp))
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextFieldWithImageAndDropdown3(
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
                DropdownIcon3(
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
fun DropdownIcon3(
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
fun CustomDropdownMenu3(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

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
                                    onCategorySelected(category)
                                    expanded = false
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
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateTimePicker() {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var selectedTime by remember { mutableStateOf(LocalTime.now()) }
    var isDateTimePickerVisible by remember { mutableStateOf(false) }

    val formattedDateTime = remember(selectedDate, selectedTime) {
        val dateFormatter = DateTimeFormatter.ofPattern("MMM, dd yyyy")
        val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
        "${selectedDate.format(dateFormatter)} | ${selectedTime.format(timeFormatter)}"
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = formattedDateTime,
            onValueChange = {},
            label = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp) // Set a fixed height for consistency
                .clickable {
                    isDateTimePickerVisible = true
                }
        )

        if (isDateTimePickerVisible) {

            AlertDialog(
                onDismissRequest = {
                    isDateTimePickerVisible = false
                },
                title = {
                    Text(text = "Select Date and Time")
                },
                confirmButton = {
                    Button(onClick = {
                        isDateTimePickerVisible = false
                    }) {
                        Text(text = "Done")
                    }
                },
                dismissButton = {
                    // Add a dismiss button if needed
                }
            )
            AndroidView(
                factory = { context ->
                    DatePicker(context).apply {
                        init(
                            selectedDate.year,
                            selectedDate.monthValue - 1,
                            selectedDate.dayOfMonth
                        ) { _, year, monthOfYear, dayOfMonth ->
                            selectedDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )


            Button(
                onClick = {
                    isDateTimePickerVisible = false
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Done")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LockPreview2() {
    val navController = rememberNavController()
    MovieAppsTheme {
        Lock2(navController = navController)
    }
}

