package nl.stephanmantel.starwars.characterlist


import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val CharacterListModule = module {

    single {
        CharacterListRepository(get(), get())
    }

    viewModel {
        CharacterListViewModel(get())
    }

}