package com.classy.gamesinfo.data.model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MySharedPreferences private constructor(context: Context) {
    interface KEYS {
        companion object {
            const val TOKEN = "TOKEN"
        }
    }

    private val preferences: SharedPreferences

    fun putString(key: String, value: String) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, def: String): String? {
        return preferences.getString(key, def)
    }

    fun putBoolean(key: String, value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String?, def: Boolean): Boolean {
        return preferences.getBoolean(key, def)
    }

//    fun putIgdbAccess(igdbAccess: IgdbAccess) {
//        val gson = Gson()
//        val accessJson: String = gson.toJson(igdbAccess)
//        preferences.edit().putString(KEYS.TOKEN, accessJson).apply()
//    }
//
//    fun getIgdbAccess(): IgdbAccess {
//        val gson = Gson()
//        var emptyTemp = gson.toJson(IgdbAccess())
//        var igdbAccess = gson.fromJson<IgdbAccess>(
//            preferences.getString(KEYS.TOKEN, emptyTemp),
//            object : TypeToken<IgdbAccess>(){}.type
//        )
//        if (igdbAccess == null) {
//            igdbAccess = IgdbAccess()
//        }
//        return igdbAccess
//    }

    companion object {
        lateinit var instance: MySharedPreferences
            private set

        fun initHelper(context: Context): MySharedPreferences {
            instance = MySharedPreferences(context)
            return instance
        }
    }

    init {
        preferences = context.applicationContext.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
    }
}