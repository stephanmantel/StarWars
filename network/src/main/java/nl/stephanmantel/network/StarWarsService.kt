package nl.stephanmantel.network

import io.reactivex.Single
import nl.stephanmantel.network.rawdomain.character.CharacterRaw
import nl.stephanmantel.network.rawdomain.film.FilmRaw
import nl.stephanmantel.network.rawdomain.response.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface StarWarsService {

    @GET("people")
    fun getPeople(
        @Query("page") page: Int? = 1
    ): Single<Response<List<CharacterRaw>>>

    @GET
    fun getFilm(
        @Url url: String
    ): Single<FilmRaw>

}