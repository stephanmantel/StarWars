package nl.stephanmantel.starwars.characterlist

import io.reactivex.Single
import nl.stephanmantel.domain.Character
import nl.stephanmantel.domain.Favourite
import nl.stephanmantel.storage.FavouritesDao

class FavouritesRepository (
    private val favouritesDao: FavouritesDao
) {

    fun getFavourites(): Single<List<Favourite>> {
        return favouritesDao.getFavourites()
    }

    fun setFavourite(character: Character, isFavourite: Boolean) {
        Thread {
            if (isFavourite) {
                val favourite = Favourite(character.name)
                favouritesDao.addFavourite(favourite)
            } else {
                favouritesDao.delete(character.name)
            }
        }.start()
    }

}