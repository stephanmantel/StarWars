package nl.stephanmantel.starwars.characterlist


import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val CharacterListModule = module {

    single {
        FavouritesRepository(get())
    }

    viewModel {
        CharacterListViewModel(get(), get())
    }

}