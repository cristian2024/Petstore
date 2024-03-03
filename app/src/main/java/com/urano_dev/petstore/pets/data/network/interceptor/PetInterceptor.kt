package com.urano_dev.petstore.pets.data.network.interceptor

import com.urano_dev.petstore.pets.data.network.PetTokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class PetInterceptor(val petTokenManager: PetTokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        return runBlocking {
            try {
                // Obtener el valor de manera asíncrona
                var token = ""
                token = petTokenManager.readValue()?:token



                // Construir la solicitud con el token obtenido
                val original = chain.request()
                val request = original.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()

                // Procesar la solicitud modificada
                val response = chain.proceed(request)
                val rawResponse =  response.peekBody(2048).string()
                print(rawResponse);
                // Devolver la respuesta
                return@runBlocking response
            } catch (e: Exception) {
                // Manejar cualquier excepción que ocurra durante la obtención del token
                e.printStackTrace()
                throw e
            }
        }

    }
}