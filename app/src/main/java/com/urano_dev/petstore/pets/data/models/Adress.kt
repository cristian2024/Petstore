package com.urano_dev.petstore.pets.data.models

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("address1")
    val address1: String?,
    @SerializedName("address2")
    val address2: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("postcode")
    val postalCode: String?,
    @SerializedName("country")
    val country: String?
)
