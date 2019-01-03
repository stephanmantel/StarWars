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

    internal fun sortCharacters(sorter: (Character) -> Comparable<*>?) {
        val characters = characterListLiveData.value?.data ?: return
        characterListMutableLiveData.value = Resource.loading(characters)
        val sortedList = characters.sortedWith(compareBy(sorter))
        characterListMutableLiveData.value = Resource.success(sortedList)
    }

}