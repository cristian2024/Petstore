package com.urano_dev.petstore.common.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.urano_dev.petstore.common.domain.AppDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single


abstract class AppDataStoreManager<TYPE, OUT> (val context: Context,  preferencesKey:String) :

    AppDataStore<TYPE, OUT> {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(preferencesKey )
    override suspend fun setValue(value: TYPE) {
        context.dataStore.edit { preferences ->
            preferences[_key] = value
        }

    }

    val flow: Flow<OUT?> =
        context. dataStore.data.map {
            val data =it[_key]
            if(data!=null){
                transformValue(it[_key] )
            }else null

        }

    override suspend fun readValue(): OUT? {
        return transformValue(context.dataStore.data.firstOrNull()?.get(_key))


    }

    override suspend fun readSingleValue(): OUT? {
        context.dataStore.data.map { preferences ->
            print(preferences)
        }
        val data = context.dataStore.data.single()
        return transformValue(context.dataStore.data.single()[_key])
    }

    override fun transformValue(value: TYPE?): OUT? {
        if (value != null)
            return value as OUT
        return null
    }


    protected abstract val _key: Preferences.Key<TYPE>


}