package nl.stephanmantel.starwars.characterlist

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val CharacterListModule = module {

    single {
        CharacterListRepository()
    }

    viewModel {
        CharacterListViewModel(get())
    }

}