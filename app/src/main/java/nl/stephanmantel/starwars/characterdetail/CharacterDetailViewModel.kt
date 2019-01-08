package nl.stephanmantel.starwars.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nl.stephanmantel.domain.Character
import nl.stephanmantel.starwars.common.BaseViewmodel
import nl.stephanmantel.starwars.common.Resource

class CharacterDetailViewModel(
    character: Character
): BaseViewmodel() {

    private val characterMutableLiveData = MutableLiveData<Resource<Character>>().apply {
        value = Resource.success(character)
    }
    internal val characterLiveData: LiveData<Resource<Character>> get() = characterMutableLiveData

    internal fun like() {
    }

    internal fun dislike() {
    }

}