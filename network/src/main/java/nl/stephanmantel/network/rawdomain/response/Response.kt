package nl.stephanmantel.network.rawdomain.response

import com.google.gson.annotations.SerializedName

data class Response<ResultType> (
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val result: ResultType
)