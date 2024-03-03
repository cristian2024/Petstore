package com.urano_dev.petstore.pets.data.network.api

import com.urano_dev.petstore.pets.data.models.PetToken
import com.urano_dev.petstore.pets.data.network.PetNetworkConstants
import com.urano_dev.petstore.pets.data.network.PetPagination
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

typealias C = PetNetworkConstants

interface PetFinderApi {

    @GET(C.ANIMAL_BASE_ENDPOINT)
    suspend fun getAnimals(
        @Query("page") page: Int,
    ): PetPagination


    @POST(C.AUTH_ENDPOINT)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun authenticate(
        @Field(C.CLIENT_ID_FIELD) clientId: String = C.CLIENT_ID,
        @Field(C.CLIENT_SECRET_FIELD) clientSecret: String = C.CLIENT_SECRET,
        @Field("grant_type") grantType: String = "client_credentials",
    ): PetToken


}