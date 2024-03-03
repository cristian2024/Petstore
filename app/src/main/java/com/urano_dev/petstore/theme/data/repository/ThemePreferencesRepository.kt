package com.urano_dev.petstore.theme.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.urano_dev.petstore.common.data.AppDataStoreManager
import com.urano_dev.petstore.theme.domain.ThemeType
import javax.inject.Inject


class ThemePreferencesRepository @Inject constructor(context:Context) :
    AppDataStoreManager<String, ThemeType>(context, "theme") {

    override val _key: Preferences.Key<String>
        get() = stringPreferencesKey("theme_option")

    override fun transformValue(value: String?): ThemeType {
        return when (value?.lowercase()) {
            "dark" -> ThemeType.DARK
            "light" -> ThemeType.LIGHT
            else -> ThemeType.DEFAULT
        }
    }


}
