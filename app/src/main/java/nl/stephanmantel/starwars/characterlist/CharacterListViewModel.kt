package nl.stephanmantel.starwars.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import nl.stephanmantel.domain.Character
import nl.stephanmantel.starwars.common.BaseViewmodel
import nl.stephanmantel.starwars.common.Resource
import nl.stephanmantel.starwars.extensions.plusAssign

internal class CharacterListViewModel (
    private val repository: CharacterListRepository
) : BaseViewmodel() {

    private val characterListMutableLiveData = MutableLiveData<Resource<List<Character>>>()
    internal val characterListLiveData: LiveData<Resource<List<Character>>> = characterListMutableLiveData

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        characterListMutableLiveData.value = Resource.loading()
        compositeDisposable += repository.requestPeople()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                characterListMutableLiveData.value = if (it.isFetching) {
                    Resource.loading(it.data)
                } else {
                    Resource.success(it.data)
                }
            }, {
                characterListMutableLiveData.value = Resource.error(it)
            })
    }

    internal fun sortCharactersByName() {
        val characters = characterListLiveData.value?.data ?: return
        characterListMutableLiveData.value = Resource.loading(characters)
        val sorted = characters.sortedBy {
            it.name
        }
        characterListMutableLiveData.value = Resource.success(sorted)
    }

    internal fun sortCharactersByAge() {
        val characters = characterListLiveData.value?.data ?: return
        characterListMutableLiveData.value = Resource.loading(characters)
        val sorted = characters.sortedByDescending {
            it.name
        }
        characterListMutableLiveData.value = Resource.success(sorted)
    }

}