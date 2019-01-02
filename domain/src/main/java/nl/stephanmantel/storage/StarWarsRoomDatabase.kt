package nl.stephanmantel.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import nl.stephanmantel.domain.Character

@Database(
    entities = [
        Character::class
    ],
    version = 1
)
abstract class StarWarsRoomDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}