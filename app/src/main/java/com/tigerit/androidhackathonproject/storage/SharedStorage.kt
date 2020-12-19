package com.tigerit.androidhackathonproject.storage

import android.content.Context
import android.content.SharedPreferences
import com.tigerit.androidhackathonproject.R

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedStorage @Inject constructor(private val context: Context) {
    private val pref: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun saveAuthToken(token : String?){
        pref.edit().putString("token", token).apply()
    }

    fun getAuthToken() = pref.getString("token",null)

    fun clear() {
        pref.edit().clear().apply()
    }
}
