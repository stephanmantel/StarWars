package nl.stephanmantel.starwars.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nl.stephanmantel.domain.Character
import nl.stephanmantel.starwars.common.BaseViewmodel
import nl.stephanmantel.starwars.common.Resource
import java.util.*

class CharacterDetailViewModel(
    character: Character
): BaseViewmodel() {

    private val characterMutableLiveData = MutableLiveData<Resource<Character>>().apply {
        value = Resource.success(character)
    }
    internal val characterLiveData: LiveData<Resource<Character>> get() = characterMutableLiveData

    private val movies = Stack<String>()
    private val moviesMutableLiveData = MutableLiveData<Resource<List<String>>>()
    internal val moviesLiveData: LiveData<Resource<List<String>>> get() = moviesMutableLiveData

    init {
        movies.addAll(
            listOf(
                "A new Mantel",
                "The Mantel strikes back",
                "The return of Mantel",
                "The phantom Mantel",
                "Attack of the Mantel",
                "Revenge of the Mantel",
                "The Mantel Awakens",
                "The Last Mantel",
                "The Android you are looking for"
            )
        )
        moviesMutableLiveData.value = Resource.success(movies)
    }

    internal fun like() {
        popMovies()
    }

    internal fun dislike() {
        popMovies()
    }

    private fun popMovies() {
        movies.pop()
        moviesMutableLiveData.value = Resource.success(movies.toList())
    }

}