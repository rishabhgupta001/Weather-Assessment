package com.example.weatherassessment.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

object Utils {
    /**
     * method used to hide the softKeyboard
     */
    fun hideKeyPad(activity: Activity) {
        try {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
        } catch (e: Exception) {
        }
    }


    //show Short Duration Toast
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}