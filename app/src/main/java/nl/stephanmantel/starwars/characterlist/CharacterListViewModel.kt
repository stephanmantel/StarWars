package nl.stephanmantel.starwars.characterlist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
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
                characterListMutableLiveData.value = Resource.success(it)
            }, {
                characterListMutableLiveData.value = Resource.error(it)
            })
    }

}