package com.urano_dev.petstore.pets.data.models

import com.google.gson.annotations.SerializedName

//
//"primary": "Domestic Short Hair",
//"secondary": null,
//"mixed": false,
//"unknown": false
data class Breed(
    @SerializedName("primary")
    val primary:String?,
    @SerializedName("secondary")
    val secondary:String?,
    @SerializedName("mixed")
    val isMixed:Boolean= false,
    @SerializedName("unknown")
    val isUnknown:Boolean = false
){
    override fun toString(): String {
        return when{
            isMixed->{
                return "$primary, $secondary"
            }

            isUnknown ->"Unknown breed"
            else-> primary?:""
        }

    }
}

