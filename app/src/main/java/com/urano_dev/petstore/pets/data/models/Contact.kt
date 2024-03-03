package com.urano_dev.petstore.pets.data.models

import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("email")
    val email: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("address")
    val address: Address?
)

