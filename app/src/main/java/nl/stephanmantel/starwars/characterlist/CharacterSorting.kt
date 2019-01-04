package nl.stephanmantel.starwars.characterlist

import nl.stephanmantel.domain.Character

internal val byName: (Character) -> Comparable<*>? = {
    it.name
}

internal val byAge: (Character) -> Comparable<*>? = {
    it.birthYear
}

internal val byFavouriteState: (Character) -> Comparable<*>? = {
    !it.isFavourite
}