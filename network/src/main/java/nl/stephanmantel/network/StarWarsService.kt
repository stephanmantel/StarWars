package nl.stephanmantel.network

import io.reactivex.Single
import nl.stephanmantel.network.rawdomain.character.CharacterRaw
import nl.stephanmantel.network.rawdomain.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsService {

    @GET("people")
    fun getPeople(
        @Query("page") page: Int? = 1
    ): Single<Response<List<CharacterRaw>>>

}