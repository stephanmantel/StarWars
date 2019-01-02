package nl.stephanmantel.network

import io.reactivex.Single
import nl.stephanmantel.network.rawdomain.character.CharacterRaw
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsService {

    @GET("people/{page}")
    fun getPeople(
        @Path("page") page: Int = 1
    ): Single<List<CharacterRaw>>

}