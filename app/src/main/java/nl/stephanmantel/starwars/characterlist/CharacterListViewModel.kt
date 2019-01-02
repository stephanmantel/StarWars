package nl.stephanmantel.starwars.characterlist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import nl.stephanmantel.domain.Character

internal class CharacterListViewModel (
) : ViewModel() {

    private val characterListMutableLiveData = MutableLiveData<List<Character>>()
    internal val characterListLiveData: LiveData<List<Character>> = characterListMutableLiveData

}