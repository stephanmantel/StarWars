package nl.stephanmantel.data

import nl.stephanmantel.data.characters.CharactersRepository
import nl.stephanmantel.data.favourites.FavouritesRepository
import org.koin.dsl.module.module

val DataModule = module {
    single {
        CharactersRepository(
            get(),
            get(),
            get()
        )
    }

    single {
        FavouritesRepository(
            get()
        )
    }
}