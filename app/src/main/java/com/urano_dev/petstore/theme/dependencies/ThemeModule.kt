package com.urano_dev.petstore.theme.dependencies

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.urano_dev.petstore.theme.data.repository.ThemePreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ThemeModule {


    @Provides
    @Singleton
    fun provideThemeRepository(@ApplicationContext context: Context): ThemePreferencesRepository {
        return ThemePreferencesRepository(context)
    }
}