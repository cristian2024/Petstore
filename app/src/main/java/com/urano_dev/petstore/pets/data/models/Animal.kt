package com.urano_dev.petstore.pets.data.models

import com.google.gson.annotations.SerializedName
import com.urano_dev.petstore.pets.domain.PetPhotos
import java.util.Locale

data class Animal(
    @SerializedName("name") val name: String,
    @SerializedName("age") val age: AnimalAge,
    @SerializedName("gender") val gender: AnimalGender,
    @SerializedName("size") val size: AnimalSize,
    @SerializedName("status") val status: AnimalStatus = AnimalStatus.ADOPTABLE,
    @SerializedName("photos") val photos: List<PetPhotos>,
    @SerializedName("contact") val contact: Contact,
    @SerializedName("description") val description: String,
    @SerializedName("breeds") val breed: Breed,
)

fun Animal.getAddress():String{
    return contact.address.let {add->
        add?.city +", " +add?.country
    }

}

fun Animal.getCharacteristics():String{
    return "${age.name.lowercase().capitalize(Locale.ROOT)} | $breed"
}

