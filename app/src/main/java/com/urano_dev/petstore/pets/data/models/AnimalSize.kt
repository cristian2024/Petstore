package com.urano_dev.petstore.pets.data.models

import com.google.gson.annotations.SerializedName

enum class AnimalSize {
    @SerializedName("Small")
    SMALL,
    @SerializedName("Medium")
    MEDIUM,
    @SerializedName("Large")
    LARGE,
    @SerializedName("Xlarge")
    XLARGE
}