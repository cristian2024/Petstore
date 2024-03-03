import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.urano_dev.petstore.pets.data.models.AnimalGender
import com.urano_dev.petstore.pets.data.models.AnimalSize
import com.urano_dev.petstore.pets.data.network.deserializer.AnimalGenderDeserializer
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


class EnumDeserializator<T : Enum<T>>: JsonDeserializer<T> {



    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): T {

        if (json == null || json.isJsonNull) {
            throw IllegalArgumentException("JSON cannot be null or empty")
        }
        val enumClass = typeOfT?.let { type ->
            if (type is Class<*> && type.isEnum) {
                @Suppress("UNCHECKED_CAST")
                type as Class<T>
            } else {
                throw IllegalArgumentException("Type is not an enum class")
            }
        } ?: throw IllegalArgumentException("Type cannot be null")
        return java.lang.Enum.valueOf(enumClass, json.asString)
    }

    fun getConverter(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(object : TypeToken<T>() {}.type, this)
        return GsonConverterFactory.create(gsonBuilder.create())
    }

}



class AnimalSizeDeserializer : JsonDeserializer<AnimalSize> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): AnimalSize {
        EnumDeserializator<AnimalSize>().getConverter()
        return AnimalSize.valueOf(json?.asString ?: "")
    }


}