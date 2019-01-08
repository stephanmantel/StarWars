package nl.stephanmantel.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import nl.stephanmantel.domain.Character
import nl.stephanmantel.domain.Favourite
import nl.stephanmantel.domain.Film

@Database(
    entities = [
        Character::class,
        Favourite::class,
        Film::class
    ],
    version = 6
)
abstract class StarWarsRoomDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun favouritesDao(): FavouritesDao
    abstract fun filmDao(): FilmDao
}