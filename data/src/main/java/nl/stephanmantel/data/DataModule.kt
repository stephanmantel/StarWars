package nl.stephanmantel.data

import nl.stephanmantel.data.characters.CharactersRepository
import nl.stephanmantel.data.favourites.FavouritesRepository
import nl.stephanmantel.data.films.FilmRepository
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

    single {
        FilmRepository(
            get(),
            get(),
            get()
        )
    }
}