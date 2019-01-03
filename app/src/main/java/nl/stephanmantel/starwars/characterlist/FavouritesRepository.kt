package nl.stephanmantel.starwars.characterlist

import io.reactivex.Single
import nl.stephanmantel.domain.Favourite
import nl.stephanmantel.storage.FavouritesDao

class FavouritesRepository (
    private val favouritesDao: FavouritesDao
) {

    fun getFavourites(): Single<List<Favourite>> {
        return favouritesDao.getFavourites()
    }

}