package com.elflin.movieapps.data

import com.elflin.movieapps.model.AddExpenseRequest
import com.elflin.movieapps.model.Expense
import com.elflin.movieapps.model.ExpenseUpdateRequest
import com.elflin.movieapps.model.ExpensesResponse
import com.elflin.movieapps.model.GeneralResponse
import com.elflin.movieapps.model.LoginInfo
import com.elflin.movieapps.model.UserResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface EndPointAPI {



    @DELETE("deleteWishlist")
    suspend fun deleteWishlist(
        @Header("Authorization") token: String,
        @Query("id") wishlist_id:Int
    ): Response<JsonObject>
    @GET("getWishlist")
    suspend fun getWishlist(
        @Header("Authorization") token: String,
    ): Response<JsonObject>
    @FormUrlEncoded
    @POST("addWishlist")
    suspend fun addWishlist(
        @Header("Authorization") token: String,
        @Field("name") user_username:String,
        @Field("price") user_email:Int
    ): Response<JsonObject>

    // Login and receive a token
    @FormUrlEncoded
    @POST("createuser")
    suspend fun register(
        @Field("name") user_username:String,
        @Field("email") user_email:String,
        @Field("password") user_password:String
    ): Response<JsonObject>

    @FormUrlEncoded
    @POST("setBudgetSekaligus")
    suspend fun setBudgetSekaligus(
        @Header("Authorization") token: String,
        @Field("budget_wants") user_username: Int,
        @Field("budget_needs") user_email: Int,
        @Field("budget_savings") user_password: Int
    ): Response<JsonObject>



    @GET("getSavingsBudgetInsight")
    suspend fun getSavingsBudgetInsight(
        @Header("Authorization") token: String
    ): Response<JsonObject>

    @GET("getNeedsBudgetInsight")
    suspend fun getNeedsBudgetInsight(
        @Header("Authorization") token: String
    ): Response<JsonObject>

    @GET("getWantsBudgetInsight")
    suspend fun getWantsBudgetInsight(
        @Header("Authorization") token: String
    ): Response<JsonObject>
    @GET("getTotalExpenses")
    suspend fun getTotalExpenses(
        @Header("Authorization") token: String
    ): Response<JsonObject>

    @GET("getTotalExpensesPercentage1")
    suspend fun getTotalExpensesPercentage(
        @Header("Authorization") token: String
    ): Response<JsonObject>

    @GET("getTotalExpensesPercentage2")
    suspend fun getTotalExpensesPercentage2(
        @Header("Authorization") token: String
    ): Response<JsonObject>

    @GET("getTotalExpensesPercentage3")
    suspend fun getTotalExpensesPercentage3(
        @Header("Authorization") token: String
    ): Response<JsonObject>





    //    @FormUrlEncoded
//    @POST("addExpense")
//    suspend fun addExpense(
//
//        @Field("name") user_expenseName:String,
//        @Field("amount") user_amount: String,
//        @Field("category_id") user_category: Int,
//        @Header("Authorization") token: String,
//    ): Response<JsonObject>
//    @FormUrlEncoded
//    @POST("login")
//    suspend fun login(
//                      @Field("email") user_email:String,
//                      @Field("password") user_password:String
//    ): Response<LoginRequest>
@FormUrlEncoded
@POST("login")
suspend fun login(@Field("email") user_email:String,
                  @Field("password") user_password:String): Response<String>

    @FormUrlEncoded
    @POST("update")
    suspend fun updateExpense(
        @Header("Authorization") token: String,
        @Body expenseUpdateRequest: ExpenseUpdateRequest
    ): Response<String>

//    suspend fun addExpense(
//
//        expense_name: String,
//        expense_amount:Int,
//        expense_category:Int
//        token:String)
//            =api.addExpense()

    @FormUrlEncoded
    @POST("addExpense")
    suspend fun addExpense(
        @Header("Authorization") token: String,
        @Field("name") expense_name:String,
        @Field("amount") expense_amount:Int,
        @Field("category_id") expense_category:Int
    ): Response<String>

    @FormUrlEncoded
    @POST("displayExpense")
    suspend fun displayExpense(
        @Header("Authorization") token: String,
        @Field("month") expense_month:String
    ): Response<JsonObject>
    
    @GET("displayExpenseWithoutDate")
    suspend fun displayExpenseWithoutMonth(
        @Header("Authorization") token: String
    ): Response<JsonObject>



    @DELETE("deleteExpense")
    suspend fun deleteExpense(
        @Header("Authorization") token: String,
        @Query("id") expense_id:Int
    ): Response<JsonObject>

    @FormUrlEncoded
    @POST("updateExpense")
    suspend fun updateExpense(
        @Header("Authorization") token: String,
        @Field("id") expense_id:Int,
        @Field("name") expense_name:String,
        @Field("amount") expense_amount:Int,
        @Field("category_id") expense_category:Int,
        @Field("date") expense_date:String,
    ): Response<JsonObject>

    @FormUrlEncoded
    @POST("setBudgetWants")
    suspend fun setBudgetWants(
        @Header("Authorization") token: String,
        @Field("budget_wants") budget_wants:Int,
    ): Response<JsonObject>

    @FormUrlEncoded
    @POST("setBudgetNeeds")
    suspend fun setBudgetNeeds(
        @Header("Authorization") token: String,
        @Field("budget_needs") budget_needs:Int,
    ): Response<JsonObject>


    @GET("BudgetChecker")
    suspend fun BudgetChecker(
        @Header("Authorization") token: String
    ): Response<JsonObject>

    @FormUrlEncoded
    @POST("setBudgetSavings")
    suspend fun setBudgetSavings(
        @Header("Authorization") token: String,
        @Field("budget_savings") budget_savings:Int,
    ): Response<JsonObject>

    @GET("logininfo")
    suspend fun getLoginInfo(@Header("Authorization") token: String): Response<LoginInfo>

//    // Create a new user
//    @FormUrlEncoded
//    @POST("createuser")
//    suspend fun createUser(@Body newUser: CreateUserRequest): Response<UserResponse>

    // Delete a user
    @FormUrlEncoded
    @DELETE("deleteuser")
    suspend fun deleteUser(@Header("Authorization") token: String, @Query("email") email: String): Response<UserResponse>

    // Update a user's details

    @PATCH("updateuser")
    suspend fun updateUser(@Header("Authorization") token: String, @Field("user_username") user_username:String,
                           @Field("user_email") user_email:String,
                           @Field("user_password") user_password:String): Response<UserResponse>



    // Logout the user
    @GET("logout")
    suspend fun logout(@Header("Authorization") token: String): Response<GeneralResponse>
}