package nl.stephanmantel.starwars.characterlist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import nl.stephanmantel.domain.Character
import nl.stephanmantel.starwars.common.Resource

internal class CharacterListViewModel (
) : ViewModel() {

    private val characterListMutableLiveData = MutableLiveData<Resource<List<Character>>>()
    internal val characterListLiveData: LiveData<Resource<List<Character>>> = characterListMutableLiveData

}