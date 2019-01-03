package nl.stephanmantel.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import nl.stephanmantel.domain.Character

@Database(
    entities = [
        Character::class
    ],
    version = 2
)
abstract class StarWarsRoomDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}