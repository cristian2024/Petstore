package com.urano_dev.petstore.pets.data.network.deserializer

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.urano_dev.petstore.pets.data.models.AnimalGender
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class AnimalGenderDeserializer : JsonDeserializer<AnimalGender> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): AnimalGender {
        return AnimalGender.valueOf(json?.asString ?: "")
    }

    companion object {
        fun getConverter(): GsonConverterFactory {
            return GsonConverterFactory.create(
                GsonBuilder()
                    .registerTypeAdapter(AnimalGender::class.java, AnimalGenderDeserializer())
                    .create()
            )
        }
    }
}