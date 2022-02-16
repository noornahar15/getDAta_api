package com.nahar.first_2022

import android.content.Context
import android.content.SharedPreferences

object Utility {

    fun customSharedPref(context: Context, name: String):SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

}