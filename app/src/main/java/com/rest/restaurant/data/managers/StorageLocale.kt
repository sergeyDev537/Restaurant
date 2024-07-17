package com.rest.restaurant.data.managers

import android.content.SharedPreferences

class StorageLocale(private val prefs: SharedPreferences) {

    object Keys {
        const val CountFavouritesKey = "CountFavouritesKey"
    }

    var countFavourites
        get() = prefs.getInt(Keys.CountFavouritesKey, 0)
        set(value) {
            prefs.edit().putInt(Keys.CountFavouritesKey, value).apply()
        }

}