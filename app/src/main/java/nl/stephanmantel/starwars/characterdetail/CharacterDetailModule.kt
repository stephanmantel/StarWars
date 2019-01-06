package nl.stephanmantel.starwars.characterdetail

import nl.stephanmantel.domain.Character
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val CharacterDetailModule = module {

    viewModel { (character: Character) ->
        CharacterDetailViewModel(character)
    }

}