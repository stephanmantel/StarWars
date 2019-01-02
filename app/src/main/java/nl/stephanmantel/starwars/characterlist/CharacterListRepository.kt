package nl.stephanmantel.starwars.characterlist

import io.reactivex.schedulers.Schedulers
import nl.stephanmantel.network.StarWarsService

class CharacterListRepository (
    private val starWarsService: StarWarsService
) {

    fun requestPeople(page: Int) {
        starWarsService.getPeople(page)
            .subscribeOn(Schedulers.io())

    }

}