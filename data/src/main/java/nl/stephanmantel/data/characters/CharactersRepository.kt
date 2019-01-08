package nl.stephanmantel.data.characters

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import nl.stephanmantel.data.common.DataWithNetworkState
import nl.stephanmantel.domain.Character
import nl.stephanmantel.network.StarWarsService
import nl.stephanmantel.network.rawdomain.character.CharacterMapper
import nl.stephanmantel.storage.CharacterDao

class CharactersRepository (
    private val starWarsService: StarWarsService,
    private val characterDao: CharacterDao,
    private val characterMapper: CharacterMapper
) {

    fun requestPeople(offset: Int): Observable<DataWithNetworkState<List<Character>>> {
        var isFetching = true
        val localCharacters = characterDao.getCharacters()
        val networkCharacters = loadMoreCharacters(offset)
            .onErrorResumeNext(localCharacters)
            .doOnEvent { _, _ ->
                isFetching = false
            }
        return localCharacters.toObservable()
            .concatWith(networkCharacters.toObservable())
            .map {
                DataWithNetworkState(isFetching, it)
            }
            .subscribeOn(Schedulers.io())
    }

    fun loadMoreCharacters(offset: Int): Single<List<Character>> {
        val page = (offset / 10) + 1
        val networkCharacters = starWarsService.getPeople(page)
            .map {
                characterMapper.apply(it.result)
            }
            .doOnSuccess {
                characterDao.storeCharacters(it)
            }

        return networkCharacters
            .subscribeOn(Schedulers.io())
    }

}