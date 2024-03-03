package com.urano_dev.petstore.pets.data.network.deserializer

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.urano_dev.petstore.pets.data.models.AnimalAge
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class AnimalAgeDeserializer : JsonDeserializer<AnimalAge> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): AnimalAge {
        return AnimalAge.valueOf(json?.asString ?: "")
    }

    companion object {
        fun getConverter(): GsonConverterFactory {
            return GsonConverterFactory.create(
                GsonBuilder()
                    .registerTypeAdapter(AnimalAge::class.java, AnimalAgeDeserializer())
                    .create()
            )
        }
    }
}