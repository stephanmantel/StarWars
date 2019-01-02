package nl.stephanmantel.starwars.characterlist

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import nl.stephanmantel.domain.Character
import nl.stephanmantel.network.StarWarsService
import nl.stephanmantel.network.rawdomain.character.CharacterMapper
import nl.stephanmantel.storage.CharacterDao

class CharacterListRepository (
    private val starWarsService: StarWarsService,
    private val characterDao: CharacterDao,
    private val characterMapper: CharacterMapper
) {

    fun requestPeople(): Observable<List<Character>> {
        val localCharacters = characterDao.getCharacters()
        val networkCharacters = starWarsService.getPeople()
            .map {
                characterMapper.apply(it.result)
            }
            .doOnSuccess {
                characterDao.storeCharacters(it)
            }
        return localCharacters.toObservable()
            .concatWith(networkCharacters.toObservable())
            .subscribeOn(Schedulers.io())
    }

}