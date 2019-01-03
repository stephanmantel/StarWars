package nl.stephanmantel.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import nl.stephanmantel.domain.Character
import nl.stephanmantel.domain.Favourite

@Database(
    entities = [
        Character::class,
        Favourite::class
    ],
    version = 5
)
abstract class StarWarsRoomDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun favouritesDao(): FavouritesDao
}