package com.elflin.movieapps.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elflin.movieapps.Repository.AuthRepository
import com.elflin.movieapps.Repository.MainRepository
import com.elflin.movieapps.data.DataStoreManager
import com.elflin.movieapps.model.Expense
import com.elflin.movieapps.model.ExpensesResponse
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.TimeZone
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val dataStore: DataStoreManager
) : ViewModel() {
//    init {
//        // Call this method to check the budget when ViewModel is created
//        checkBudget()
//    }

    var calendar = Calendar.getInstance(TimeZone.getDefault())

    private val _budgetChecker = MutableLiveData<Int>()
    val budgetChecker: LiveData<Int> = _budgetChecker


    private val _selectedCategoryId = MutableLiveData<Int>()
    val selectedCategoryId: LiveData<Int> = _selectedCategoryId
    private val _token = MutableLiveData<String>()
    val token: LiveData<String> = _token

    private val _expenses = MutableLiveData<List<Expense>>()
    val expenses: LiveData<List<Expense>> = _expenses


    private val _categoryId = MutableLiveData<Int>()
    val categoryId: LiveData<Int> = _categoryId

    init {
        viewModelScope.launch {
            dataStore.getToken.collect { token ->
                if (token != null) {

                    AuthRepository.ACCESS_TOKEN = token
                    loadExpenses()

                }
            }
        }
    }

    fun setSelectedCategory(categoryId: Int) {
        _selectedCategoryId.value = categoryId
    }

    fun getCurrentYearAndMonth(): String {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH] + 1 // Adding 1 because Calendar.MONTH is zero-based
        return "$year-$month"
    }

//    private val _expenses = MutableLiveData<List<Expense>>()
//    val expenses: LiveData<List<Expense>> = _expenses

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String> = _responseMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

//    fun getExpensesForMonth(month: String, token: String) {
//        _isLoading.value = true
//        viewModelScope.launch {
//            try {
//                val response = mainRepository.getExpensesForMonth(month, token)
//                if (response.isSuccessful && response.body() != null) {
//                    _expenses.value = listOf(response.body())
//                } else {
//                    _responseMessage.value = "Failed to fetch expenses"
//                }
//            } catch (e: Exception) {
//                _responseMessage.value = "Error: ${e.message}"
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }

    // Inside MainViewModel
    fun getCategoryId(categoryName: String): Int {
        return when (categoryName) {
            "Needs" -> 1
            "Wants" -> 2
            "Savings" -> 3
            else -> -1 // Invalid category
        }
    }

    fun AddExpense(token: String, name: String, amount: Int, categoryId: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = mainRepository.AddExpense(token, name, amount, categoryId)
                Log.e("AddExpense1", response.toString())
                // Handle the response here
            } catch (e: Exception) {

                _responseMessage.value = "Error: ${e.message}"

                Log.e("AddExpense2", "aaa")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun AddBudget(token: String, name: String, amount: Int, categoryId: Int) {
        _isLoading.value = true
        viewModelScope.launch {

            try {
                val response = mainRepository.AddExpense(token, name, amount, categoryId)
                Log.e("AddExpense1", response.toString())
                // Handle the response here
            } catch (e: Exception) {

                _responseMessage.value = "Error: ${e.message}"

                Log.e("AddExpense2", "aaa")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteExpense(id: Int) {
        _isLoading.value = true
        val token = "Bearer " + AuthRepository.ACCESS_TOKEN

        viewModelScope.launch {
            try {
                Log.d("deleteExpense", "Trying to delete expense $id")
                val response = mainRepository.deleteExpense(token, id)
                Log.d("deleteExpense", "Respone from delete expense ${response.body()}")
                Log.e("deleteExpense", "Response: $response")
                if (response.isSuccessful) {
                    loadExpenses()
                    _responseMessage.value = "Expense deleted successfully"
                } else {
                    _responseMessage.value =
                        "Failed to delete expense: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                _responseMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateExpense(token: String, expenseId: Int, expenseName: String, expenseAmount: Int, expenseCategory: Int, expenseDate: String) {
        _isLoading.value = true
        val token1 = "Bearer " + AuthRepository.ACCESS_TOKEN
        viewModelScope.launch {
            try {

                val response = mainRepository.updateExpense(token1, expenseId, expenseName, expenseAmount, expenseCategory, expenseDate)
                loadExpenses()
                if (response.isSuccessful && response.body() != null) {
                    _responseMessage.value = "Expense updated successfully"
                    // Optionally, refresh the expenses list or handle the updated data

                } else {
                    _responseMessage.value = "Failed to update expense"
                }
            } catch (e: Exception) {
                _responseMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }}



    fun checkBudget() {
        val token = "Bearer " + AuthRepository.ACCESS_TOKEN
        viewModelScope.launch {


            try {

                val response = mainRepository.BudgetChecker(token)
                Log.e(
                    "Check budget response",
                    response.toString()
                )
                if (response.isSuccessful) {
                    // Assuming the API returns an integer indicating the budget status
                    _budgetChecker.value = 0

                } else {
                    Log.e(
                        "Check budget",
                        "Failed to check budget: ${response.errorBody()?.string()}"
                    )
                    _budgetChecker.value = 1 // Assuming 0 means no budget
                }
            } catch (e: Exception) {
                Log.e("Check budget", "Error checking budget: ${e.localizedMessage}")
                _budgetChecker.value = 0
            }
        }


    }

    fun setBudgetSavings(budgetSavings: Int) {
        val token = "Bearer " + AuthRepository.ACCESS_TOKEN
        _isLoading.value = true
        Log.e("setBudgetSavings", token)
        viewModelScope.launch {
            try {

                val response = mainRepository.setBudgetSavings(token, budgetSavings)
                Log.d("setBudgetSavings", "Response: $response")
                // Handle the response here
            } catch (e: Exception) {
                _responseMessage.value = "Error: ${e.message}"
                Log.e("setBudgetSavings", "Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }





    fun monthpemberes(month: String): String {
        val monthInt = month.toIntOrNull() ?: return "Invalid Month" // Handle invalid input
        return if (monthInt < 10) "0$monthInt" else monthInt.toString()
    }



    fun formatDate(year: String, month: String): String {
            val monthPadded = if (month.length == 1) "0$month" else month
            return "$year-$monthPadded"
        }


        fun loadExpenses() {
        val token1 = "Bearer " + AuthRepository.ACCESS_TOKEN
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        val year = calendar.get(Calendar.YEAR)
        val month =
            calendar.get(Calendar.MONTH) + 1 // Adding 1 because Calendar.MONTH is zero-based
        val monthPadded: String = when {
            month < 10 -> "0$month"
            else -> "$month"
        }
        val formattedMonth = "$year-$monthPadded"
        Log.d("MainViewModel", formattedMonth)
        _isLoading.value = true
        viewModelScope.launch {
            Log.d("MainViewModel", "Attempting to load expenses")
            try {
                val response = mainRepository.displayExpenseWithoutMonth(token1)
                Log.d("MainViewModel", "Response received: $response")
                if (response.isSuccessful) {
                    Log.d("MainViewModel", "Expenses loaded successfully: ${response.body()}")

                    val expensesResponse: ExpensesResponse =
                        Gson().fromJson(response.body()?.toString(), ExpensesResponse::class.java)
                    val individualExpenses = mutableListOf<Expense>()
                    expensesResponse.expenses.forEach { expense ->
                        individualExpenses.add(expense)
                    }
                    _expenses.postValue(individualExpenses) // Update LiveData with the new list

                    Log.d("MainViewModel", _expenses.toString())

                } else {
                    _responseMessage.value =
                        "Failed to load expenses: ${response.errorBody()?.string()}"
                    Log.e(
                        "MainViewModel",
                        "Failed to load expenses: Response Code: ${response.code()}, Error Body: ${
                            response.errorBody()?.string()
                        }"
                    )
                }
            } catch (e: Exception) {
                _responseMessage.value = "Error loading expenses: ${e.localizedMessage}"
                Log.e("MainViewModel", "Error loading expenses: ${e.localizedMessage}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun setBudgetWants(budgetWants: Int,token:String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val token = "Bearer " + AuthRepository.ACCESS_TOKEN
                val response = mainRepository.setBudgetWants(token, budgetWants)
                Log.d("setBudgetWants", "Response: $response")
                // Handle the response here
            } catch (e: Exception) {
                _responseMessage.value = "Error: ${e.message}"
                Log.e("setBudgetWants", "Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun setBudgetNeeds(budgetNeeds: Int,token:String) {
        val token = "Bearer " + "2|9UesZDCWnLyXAvPvuE51kp4acjK0Hd82dMpow2YK9fe27eee"
        _isLoading.value = true
        viewModelScope.launch {
            try {

                val response = mainRepository.setBudgetWants("Bearer " + AuthRepository.ACCESS_TOKEN, budgetNeeds)
                Log.d("setBudgetNeeds", "Response: $response")
                // Handle the response here
            } catch (e: Exception) {
                _responseMessage.value = "Error: ${e.message}"
                Log.e("setBudgetNeeds", "Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

        }


//    fun AddExpense(name: String, amount: String, categoryId: Int, token: String) {
//
//        _isLoading.value = true
//        viewModelScope.launch {
//            val categoryId = getCategoryID(categoryName)
//            try {
//                val response = mainRepository.AddExpense(name,token,categoryId,amount,categoryId)
//                if (response.isSuccessful && response.body() != null) {
//
//                } else {
//                    _responseMessage.value = "Failed to add expense"
//                }
//            } catch (e: Exception) {
//                _responseMessage.value = "Error: ${e.message}"
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }

//    fun updateExpense(expenseId: Int, name: String, amount: Double, categoryId: Int, date: String, token: String) {
//        _isLoading.value = true
//        viewModelScope.launch {
//            try {
//                val response = mainRepository.updateExpense(expenseId, name, amount, categoryId, date, token)
//                if (response.isSuccessful && response.body() != null) {
//                    _responseMessage.value = response.body()?.message ?: "Expense updated successfully"
//                } else {
//                    _responseMessage.value = "Failed to update expense"
//                }
//            } catch (e: Exception) {
//                _responseMessage.value = "Error: ${e.message}"
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }

//    fun deleteExpense(expenseId: Int, token: String) {
//        _isLoading.value = true
//        viewModelScope.launch {
//            try {
//                val response = mainRepository.deleteExpense(expenseId, token)
//                if (response.isSuccessful && response.body() != null) {
//                    _responseMessage.value = response.body()?.message ?: "Expense deleted successfully"
//                } else {
//                    _responseMessage.value = "Failed to delete expense"
//                }
//            } catch (e: Exception) {
//                _responseMessage.value = "Error: ${e.message}"
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }

    // Additional methods can be implemented for other functionalities like
    // getting total expenses, budget percentages, etc., based on your API structure.



//@HiltViewModel
//class MainViewModel @Inject constructor(
//    private val repo: MainRepository,
//    private val dataStore : DataStoreManager
//
//): ViewModel(){
//
//    private val _expenseName = MutableLiveData<List<Expense>>()
//    val expenseName: MutableLiveData<List<Expense>> get() = _expenseName
//
//
////    user_amount:String,
////    @Field("category") user_category:String,
////    @Header("Authorization") token: String,
//// LiveData to hold the response message
//private val _responseMessage = MutableLiveData<String>()
//    val responseMessage: LiveData<String> = _responseMessage
//
//    // LiveData to handle loading state
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    private fun getCategoryID(categoryName: String): Int {
//        return when (categoryName) {
//            "Needs" -> 1
//            "Wants" -> 2
//            "Savings" -> 3
//            else -> -1 // Invalid category
//        }
//    }
//
//    // Function to add an expense
//    fun addExpense(expenseName: String, categoryName: String, amount: String, token: String) {
//        val categoryID = getCategoryID(categoryName)
//        if (categoryID == -1) {
//            _responseMessage.value = "Invalid category selected"
//            return
//        }
//
//        _isLoading.value = true
//        viewModelScope.launch {
//            try {
//                val response = repo.AddExpense(expenseName, categoryID.toString(), amount, token)
//                if (response.isSuccessful && response.body() != null) {
//                    // Assuming the response body contains a message field
//                    _responseMessage.value = response.body()?.get("message")?.asString ?: "Expense added successfully"
//                } else {
//                    _responseMessage.value = "Failed to add expense"
//                }
//            } catch (e: Exception) {
//                _responseMessage.value = "Error: ${e.message}"
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }
//
////    fun addExpense(expense_name:String,user_category: String, user_amount: String, token: String)
////            = viewModelScope.launch{
////        repo.AddExpense(expense_name,user_category, user_amount, token).let{
////                response ->
////            if(response.isSuccessful){
////                if(response.body()?.get("message")!!.asString=="Expense added successfully"){
////                    _expenseName.value = response.body()?.get("message")!!.asString
////
////                }
////            } else {
////
////            }
////        }
////}
//
//
//
