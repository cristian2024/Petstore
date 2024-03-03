package com.urano_dev.petstore.pets.data.models

import com.google.gson.annotations.SerializedName


//adoptable, adopted
enum class AnimalStatus {
    @SerializedName("adoptable")
    ADOPTABLE,

    @SerializedName("adopted")
    ADOPTED,
}