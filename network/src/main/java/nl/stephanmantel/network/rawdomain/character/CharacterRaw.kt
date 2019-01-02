package nl.stephanmantel.network.rawdomain.character

import com.google.gson.annotations.SerializedName

data class CharacterRaw (
    @SerializedName("name") val name: String?
)