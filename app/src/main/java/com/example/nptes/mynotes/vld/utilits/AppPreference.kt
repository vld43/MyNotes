package com.example.nptes.mynotes.vld.utilits

import android.content.Context
import android.content.SharedPreferences

object AppPreference {

    private const val INIT_USER = "initUser"
    private const val TYPE_DB = "typeDB"
    private const val NAME_PREF = "preference"

    private lateinit var preference: SharedPreferences

    fun getPreference(context: Context): SharedPreferences {
        preference = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
        return preference
    }

    fun setInitUser(init: Boolean) {
        preference
            .edit()
            .putBoolean(INIT_USER, init)
            .apply()
    }

    fun setTypeDB(type: String) {
        preference.edit()
            .putString(TYPE_DB, type)
            .apply()
    }

    fun getInitUser(): Boolean {
        return preference.getBoolean(INIT_USER, false)
    }

    fun getTypeDB(): String {
        return preference.getString(TYPE_DB, TYPE_ROOM).toString()
    }
}