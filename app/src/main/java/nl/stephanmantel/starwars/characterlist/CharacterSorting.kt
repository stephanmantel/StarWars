package nl.stephanmantel.starwars.characterlist

import nl.stephanmantel.domain.Character

internal val byName: (Character) -> Comparable<*>? = {
    it.name
}

internal val byAge: (Character) -> Comparable<*>? = {
    it.birthYearAsFloat()
}

internal val byFavouriteState: (Character) -> Comparable<*>? = {
    !it.isFavourite
}