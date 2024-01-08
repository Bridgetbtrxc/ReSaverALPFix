package com.elflin.movieapps.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.elflin.movieapps.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class transaction_Model(

    val transaction_name: String,
    val money: Double? = 0.0,
    val date: LocalDateTime,
    val hour: LocalDateTime,
    val category: String,
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formattedDate(): String {
        val today = LocalDateTime.now().toLocalDate()
        val transactionDate = date.toLocalDate()

        return if (today == transactionDate) {
            "Today"
        } else {
            transactionDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formattedHour(): String {
        return hour.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

//    val categoryImageResource: Int
//        get() {
//            return when (category) {
//                "Leisure" -> R.drawable.ic_plus
//                "Work" -> R.drawable.ic_minus
//                "Groceries" -> R.drawable.ic_minus,
//                "Money In" -> R.drawable.ic_minus
//                else -> {}
//            }
//        }
}