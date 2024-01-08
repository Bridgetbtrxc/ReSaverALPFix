package com.elflin.movieapps.Repository

import com.elflin.movieapps.data.EndPointAPI
import com.elflin.movieapps.model.AddExpenseRequest
import javax.inject.Inject
import retrofit2.Response
import retrofit2.http.Field


class MainRepository @Inject constructor(
    private val api: EndPointAPI
) {
//    suspend fun AddExpense(
//        expense_name: String,
//        user_amount: Int,
//        user_category: Int,
//        token: String
//    ): Response<AddExpenseRequest> {
//        val addExpenseRequest = AddExpenseRequest(
//            name = expense_name,
//            amount = user_amount,
//            category_id = user_category
//        )
//        return api.addExpense(token, addExpenseRequest)
//    }

    suspend fun AddExpense(
        token: String,
        expense_name: String,
        expense_amount: Int,
        expense_category: Int,
    ) = api.addExpense(token, expense_name, expense_amount, expense_category)

    suspend fun displayExpense(
        token: String,
        expense_month: String
    ) = api.displayExpense(token, expense_month)

    suspend fun displayExpenseWithoutMonth(
        token: String,
    ) = api.displayExpenseWithoutMonth(token)

    suspend fun BudgetChecker(
        token: String
    ) = api.BudgetChecker(token)

    suspend fun deleteExpense(
        token: String,
        expense_category: Int
    ) = api.deleteExpense(token, expense_category)



//    @Field("id") expense_id:Int,
//    @Field("name") expense_name:String,
//    @Field("amount") expense_amount:Int,
//    @Field("category_id") expense_category:Int,
//    @Field("date") expense_date:String,

    suspend fun updateExpense(
        token: String,
        expense_id: Int,
        expense_name:String,
        expense_amount:Int,
        expense_category:Int,
        expense_date:String

    ) = api.updateExpense(token, expense_id,expense_name,expense_amount,expense_category,expense_date)

    suspend fun setBudgetWants(
        token: String,
        budget_wants: Int
    ) = api.setBudgetWants(token, budget_wants)

    suspend fun setBudgetNeeds(
        token: String,
        budget_needs: Int
    ) = api.setBudgetNeeds(token, budget_needs)

    suspend fun setBudgetSavings(
        token: String,
        budget_savings: Int
    ) = api.setBudgetSavings(token, budget_savings)



    suspend fun getExpense(
        user_month: String, token: String
    ) = api.displayExpense(token, user_month)


    suspend fun getExpensesForMonth(month: String, token: String) = api.displayExpense(month, token)


//    fun deleteExpense(expenseId: Int, token: String) = api.delete
}