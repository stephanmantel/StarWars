package nl.stephanmantel.network.rawdomain.film

import com.google.gson.annotations.SerializedName

data class FilmRaw (
    @SerializedName("episode_id") val episodeId: Int?,
    @SerializedName("title") val title: String?
)