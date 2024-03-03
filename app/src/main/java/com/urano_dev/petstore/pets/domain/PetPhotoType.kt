package com.urano_dev.petstore.pets.domain

import com.google.gson.annotations.SerializedName


data class PetPhotos(
    @SerializedName("small")
    val small: String,

    @SerializedName("medium")
    val medium: String,

    @SerializedName("large")
    val large: String,

    @SerializedName("full")
    val full: String
)

