package nl.stephanmantel.starwars.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import nl.stephanmantel.domain.Character
import nl.stephanmantel.domain.Favourite
import nl.stephanmantel.starwars.common.BaseViewmodel
import nl.stephanmantel.starwars.common.Resource
import nl.stephanmantel.starwars.extensions.plusAssign

internal class CharacterListViewModel (
    private val repository: CharacterListRepository,
    private val favouritesRepository: FavouritesRepository
) : BaseViewmodel() {

    private val characterListMutableLiveData = MutableLiveData<Resource<List<Character>>>()
    internal val characterListLiveData: LiveData<Resource<List<Character>>> = characterListMutableLiveData

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        characterListMutableLiveData.value = Resource.loading()
        compositeDisposable += repository.requestPeople()
            .flatMapSingle { characterData ->
                favouritesRepository.getFavourites().doOnSuccess { favourites ->
                    setCharactersFavouriteStates(favourites, characterData.data)
                }
                Single.just(characterData)
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

    internal fun setCharacterFavourite(character: Character, isFavourite: Boolean) {
        favouritesRepository.setFavourite(character, isFavourite)
    }

    private fun setCharactersFavouriteStates(
        favourites: List<Favourite>,
        characters: List<Character>
    ) {
        characters.forEach { character ->
            val isFavourite = favourites.any {
                it.name == character.name
            }
            character.isFavourite = isFavourite
        }
    }

    internal fun sortCharacters(sorter: (Character) -> Comparable<*>?) {
        val characters = characterListLiveData.value?.data ?: return
        characterListMutableLiveData.value = Resource.loading(characters)
        val sortedList = characters.sortedWith(compareBy(sorter))
        characterListMutableLiveData.value = Resource.success(sortedList)
    }

}