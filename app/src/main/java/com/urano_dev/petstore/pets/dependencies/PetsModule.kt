package com.urano_dev.petstore.pets.dependencies

import EnumDeserializator
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.gson.GsonBuilder
import com.urano_dev.petstore.pets.data.models.AnimalAge
import com.urano_dev.petstore.pets.data.models.AnimalGender
import com.urano_dev.petstore.pets.data.models.AnimalSize
import com.urano_dev.petstore.pets.data.models.AnimalStatus
import com.urano_dev.petstore.pets.data.network.PetAuthenticator
import com.urano_dev.petstore.pets.data.network.PetNetworkConstants
import com.urano_dev.petstore.pets.data.network.PetTokenManager
import com.urano_dev.petstore.pets.data.network.api.PetFinderApi
import com.urano_dev.petstore.pets.data.network.deserializer.AnimalAgeDeserializer
import com.urano_dev.petstore.pets.data.network.interceptor.PetInterceptor
import com.urano_dev.petstore.pets.data.repository.PetApiRepository
import com.urano_dev.petstore.pets.domain.repository.PetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PetsModule {


    @Provides
    @Singleton
    fun provideTokenManager( @ApplicationContext context: Context): PetTokenManager {
        return PetTokenManager(context)
    }

    @Provides
    @Singleton
    fun providePetsApi(petTokenManager: PetTokenManager): PetFinderApi {
        val client =
            OkHttpClient.Builder()
                .authenticator(
                    PetAuthenticator(petTokenManager)
                )
                .addInterceptor(
                    PetInterceptor(petTokenManager)
                ).build()

        val gson = GsonBuilder()
            .create()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(PetNetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

            .create(PetFinderApi::class.java)
    }

    @Provides
    @Singleton
    fun providePetsRepository(petApi: PetFinderApi): PetRepository {
        return PetApiRepository(petApi)
    }
}


