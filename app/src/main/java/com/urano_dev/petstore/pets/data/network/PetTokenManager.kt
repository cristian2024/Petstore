package com.urano_dev.petstore.pets.data.network

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.urano_dev.petstore.common.data.AppDataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PetTokenManager @Inject constructor(context: Context) :
    AppDataStoreManager<String, String>(context, "pet_management") {
    override val _key: Preferences.Key<String>
        get() = stringPreferencesKey("pet_token")


    override suspend fun readValue(): String? {
        return super.readValue()
    }
}