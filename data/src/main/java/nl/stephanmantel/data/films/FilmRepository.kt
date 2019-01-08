package nl.stephanmantel.data.films

import io.reactivex.Single
import nl.stephanmantel.domain.Film
import nl.stephanmantel.network.StarWarsService
import nl.stephanmantel.network.rawdomain.film.FilmMapper
import nl.stephanmantel.storage.FilmDao

class FilmRepository(
    private val starWarsService: StarWarsService,
    private val filmDao: FilmDao,
    private val filmMapper: FilmMapper
) {

    fun getFilm(url: String): Single<Film> {
        return starWarsService.getFilm(url)
            .map {
                filmMapper.apply(it)
            }
            .doOnSuccess {
                filmDao.storeFilm(it)
            }
    }

}