package com.decagonhq.clads.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class AppSharedPreference @Inject constructor(private val sharedPref: SharedPreferences) {

    /**
     * Saves data to the shared preference, SHARED_PREF; this function takes two parameters,
     * keyName: This is key of the data you want to retrieve from the shared preference, and
     * sharedPrefName: This is the variable that you want to save to the shared preference, this
     * can be either String, Boolean or Int
     * */
    fun saveDataToTheSharedPreference(keyName: String, sharedPrefValue: Any) {
        val sharedPrefEditor = sharedPref.edit()

        when (sharedPrefValue) {
            is Int -> {
                sharedPrefEditor.putInt(keyName, sharedPrefValue)
            }
            is String -> {
                sharedPrefEditor.putString(keyName, sharedPrefValue)
            }
            is Boolean -> {
                sharedPrefEditor.putBoolean(keyName, sharedPrefValue)
            }
        }

        sharedPrefEditor.apply()
    }

    /**
     * Gets data from the shared preference, SHARED_PREF; this function takes two parameters,
     * keyName: This is key of the data you want to retrieve from the shared preference, and
     * expectedType: This is the data type of the data that you want to retrieve from the shared preference, this
     * can be either String, Boolean or Int.
     * FOR EXAMPLE, If you are expecting a data of type:
     * 1. String: Just passing any string and surround it with quotation eg "string"
     * 1. Integer: Just passing any number eg 0, 1, 2 etc.
     * 1. Boolean: Just passing any boolean i.e either true or false
     * */
    fun getDataFromSharedPreference(keyName: String, expectedType: Any): Any {

        return when (expectedType) {
            is Int -> {
                sharedPref.getInt(keyName, 0)
            }
            is String -> {
                sharedPref.getString(keyName, "")!!
            }
            is Boolean -> {
                sharedPref.getBoolean(keyName, false)
            }
            else -> {
                Unit
            }
        }
    }

    /**
     * Removes data from the shared preference, SHARED_PREF; this function takes one argument,
     * keyName: This is key of the data you want to delete from the shared preference
     * */
    fun removeDataFromSharedPreference(keyName: String) {
        val sharedPrefEditor = sharedPref.edit()

        sharedPrefEditor.remove(keyName)
        sharedPrefEditor.apply()
    }

    /**
     * Deletes the entire shared pref, SHARED_REF.
     * */
    fun clearSharedPref() {
        val sharedPrefEditor = sharedPref.edit()
        sharedPrefEditor.clear()
        sharedPrefEditor.apply()
    }
}
