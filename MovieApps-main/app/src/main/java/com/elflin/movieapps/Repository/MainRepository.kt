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

    suspend fun getSavingsBudgetInsight(
        token: String,
    ) = api.getSavingsBudgetInsight(token)
    suspend fun getNeedsBudgetInsight(
        token: String,
    ) = api.getNeedsBudgetInsight(token)
    suspend fun getWantsBudgetInsight(
        token: String,
    ) = api.getWantsBudgetInsight(token)
    suspend fun deleteWishlist(
        token: String,
        id:Int
    ) = api.deleteWishlist(token,id)
    suspend fun getWishlist(
        token: String,
    ) = api.getWishlist(token)
    suspend fun addWishlist(
        token: String,
        nama_barang: String,
        harga_barang: Int,
    ) = api.addWishlist(token, nama_barang,harga_barang)

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

    suspend fun setBudgetSekaligus(
        token: String,
        budget_needs: Int,
        budget_wants: Int,
        budget_savings: Int
    ) = api.setBudgetSekaligus(token,budget_needs,budget_savings,budget_wants)

    suspend fun getTotalExpense(
        token: String,
    ) = api.getTotalExpenses(token)

    suspend fun getTotalExpensePercentage(
        token: String,
    ) = api.getTotalExpensesPercentage(token)

    suspend fun getTotalExpensePercentage2(
        token: String,
    ) = api.getTotalExpensesPercentage2(token)

    suspend fun getTotalExpensePercentage3(
        token: String,
    ) = api.getTotalExpensesPercentage3(token)

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