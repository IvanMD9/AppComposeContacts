package com.example.appcomposecontacts.data.data_source

import android.content.Context
import android.content.SharedPreferences
import com.example.appcomposecontacts.R

class SessionManager(context: Context?) {
    private val prefs: SharedPreferences? =
        context?.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun savePhone(phone : String) {
        val editor = prefs?.edit()
        editor?.putString(PHONE, phone)
        editor?.apply()
    }

    fun getPhone() : String {
        val numPhone = prefs?.getString(PHONE, null)
        return numPhone.toString()
    }

    companion object {
        const val PHONE = "phone"
    }
}