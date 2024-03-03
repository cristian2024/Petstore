package com.urano_dev.petstore.pets.data.models

import com.google.gson.annotations.SerializedName

enum class AnimalGender {

    @SerializedName("Male")
    MALE,
    @SerializedName("Female")
    FEMALE,
    @SerializedName("Unknown")
    UNKNOWN
}