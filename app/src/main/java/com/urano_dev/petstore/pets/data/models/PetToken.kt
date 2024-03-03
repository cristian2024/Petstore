package com.urano_dev.petstore.pets.data.models

import com.google.gson.annotations.SerializedName

data class PetToken (
    @SerializedName("token_type") val tokenType: String= "",

    @SerializedName("expires_in") val expireTime: Int= 0,
    @SerializedName("access_token") val token: String= "",
)
