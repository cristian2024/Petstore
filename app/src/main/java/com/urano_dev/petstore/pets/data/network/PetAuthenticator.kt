package com.urano_dev.petstore.pets.data.network

import com.google.gson.GsonBuilder
import com.urano_dev.petstore.pets.data.network.api.PetFinderApi
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PetAuthenticator(
    private val petTokenManager: PetTokenManager,
) : Authenticator {


    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            refreshToken()
            val request = buildRequest(response.request.newBuilder())
            if (responseCount(response) >= 3) {
                null
            } else request
        }
    }

    private suspend fun refreshToken() {
        try {
            val gson = GsonBuilder()
                .create()
            val retrofit = Retrofit.Builder()
                .baseUrl(PetNetworkConstants.BASE_URL)
                .client(
                    OkHttpClient.Builder().addInterceptor(Interceptor()).addInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                    ).build()
                )
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(PetFinderApi::class.java)
//            PetFinderApi()
            val tokenInfo = retrofit.authenticate()
            petTokenManager.setValue(tokenInfo.token)


        } catch (error: Exception) {
            print(error)
        }


    }

    private fun responseCount(response: Response?): Int {
        var result = 1
        while (response?.priorResponse != null && result <= 3) {
            result++
        }
        return result
    }

    private fun buildRequest(requestBuilder: Request.Builder): Request {
        return runBlocking {
            var token = petTokenManager.readValue() ?: ""

            requestBuilder
                .header("Authorization", "Bearer $token")
                .build()
        }

    }
}


class Interceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val body = original.body
        return chain.proceed(original);


    }
}