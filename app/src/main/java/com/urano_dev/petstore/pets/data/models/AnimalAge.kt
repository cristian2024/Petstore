package com.urano_dev.petstore.pets.data.models

import com.google.gson.annotations.SerializedName

enum class AnimalAge {
    @SerializedName("Baby")
    BABY,

    @SerializedName("Young")
    YOUNG,

    @SerializedName("Adult")
    ADULT,

    @SerializedName("Senior")
    SENIOR,
}