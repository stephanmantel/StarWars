package nl.stephanmantel.starwars.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import nl.stephanmantel.data.characters.CharactersRepository
import nl.stephanmantel.data.common.DataWithNetworkState
import nl.stephanmantel.data.favourites.FavouritesRepository
import nl.stephanmantel.domain.Character
import nl.stephanmantel.domain.Favourite
import nl.stephanmantel.starwars.common.BaseViewmodel
import nl.stephanmantel.starwars.common.Resource
import nl.stephanmantel.starwars.common.Status
import nl.stephanmantel.starwars.extensions.plusAssign

internal class CharacterListViewModel (
    private val repository: CharactersRepository,
    private val favouritesRepository: FavouritesRepository
) : BaseViewmodel() {

    private val characterListMutableLiveData = MutableLiveData<Resource<List<Character>>>()
    internal val characterListLiveData: LiveData<Resource<List<Character>>> get() = characterListMutableLiveData
    private var endOfDataSetReached = false

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        characterListMutableLiveData.value = Resource.loading()
        compositeDisposable += repository.requestPeople(0)
            .flatMapSingle { characterData ->
                favouritesRepository.getFavourites()
                    .map { favourites ->
                        val favList = setCharactersFavouriteStates(favourites, characterData.data)
                        DataWithNetworkState(characterData.isFetching, favList)
                    }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val characters = it.data.sortedWith(compareBy(byName))
                characterListMutableLiveData.value = if (it.isFetching) {
                    Resource.loading(characters)
                } else {
                    Resource.success(characters)
                }
            }, {
                characterListMutableLiveData.value = Resource.error(it)
            })
    }

    internal fun fetchMoreCharacters() {
        if (characterListLiveData.value?.status == Status.LOADING || endOfDataSetReached) {
            return
        }
        val existingCharacters = characterListLiveData.value?.data ?: emptyList()
        characterListMutableLiveData.value = Resource.loading(existingCharacters)
        val offset = existingCharacters.size
        compositeDisposable += repository.loadMoreCharacters(offset)
            .onErrorReturn {
                endOfDataSetReached = true
                emptyList()
            }
            .flatMap { characters ->
                favouritesRepository.getFavourites()
                    .map { favourites ->
                        setCharactersFavouriteStates(favourites, characters)
                    }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val characters = it.sortedWith(compareBy(byName))
                characterListMutableLiveData.value = Resource.success(existingCharacters + characters)
            }, {
                characterListMutableLiveData.value = Resource.error(it)
            })

    }

    internal fun setCharacterFavourite(character: Character, isFavourite: Boolean) {
        favouritesRepository.setFavourite(character, isFavourite)
    }

    private fun setCharactersFavouriteStates(
        favourites: List<Favourite>,
        characters: List<Character>
    ): List<Character> {
        characters.forEach { character ->
            val isFavourite = favourites.any {
                it.name == character.name
            }
            character.isFavourite = isFavourite
        }
        return characters
    }

    internal fun sortCharacters(sorter: (Character) -> Comparable<*>?) {
        val characters = characterListLiveData.value?.data ?: return
        characterListMutableLiveData.value = Resource.loading(characters)
        val sortedList = characters.sortedWith(compareBy(sorter))
        characterListMutableLiveData.value = Resource.success(sortedList)
    }

}