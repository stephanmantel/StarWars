package nl.stephanmantel.network

import com.google.gson.annotations.SerializedName

data class CharacterRaw (
    @SerializedName("name") val name: String
)