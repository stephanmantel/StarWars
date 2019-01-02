package nl.stephanmantel.starwars.characterlist

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import nl.stephanmantel.domain.Character
import nl.stephanmantel.network.StarWarsService
import nl.stephanmantel.network.rawdomain.character.CharacterMapper

class CharacterListRepository (
    private val starWarsService: StarWarsService,
    private val characterMapper: CharacterMapper
) {

    fun requestPeople(): Single<List<Character>> {
        return starWarsService.getPeople()
            .subscribeOn(Schedulers.io())
            .map {
                characterMapper.apply(it.result)
            }

    }

}