package nl.stephanmantel.data.characters

import org.koin.dsl.module.module

val DataModule = module {
    single {
        CharactersRepository(
            get(),
            get(),
            get()
        )
    }
}