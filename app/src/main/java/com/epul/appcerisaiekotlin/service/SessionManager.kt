package com.epul.appcerisaiekotlin.service

import android.content.Context
import android.content.SharedPreferences
import com.epul.appcerisaiekotlin.R


class SessionManager(context: Context) {
    private val prefs: SharedPreferences
    val USER_TOKEN = "user_token"

    fun saveAdmin(token: String?) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    init {
        prefs = context.getSharedPreferences(
            context.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }
}