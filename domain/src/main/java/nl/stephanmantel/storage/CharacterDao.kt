package nl.stephanmantel.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import nl.stephanmantel.domain.Character
import nl.stephanmantel.domain.TABLE_NAME_CHARACTERS

@Dao
interface CharacterDao {

    @Query("""
        SELECT * FROM $TABLE_NAME_CHARACTERS
    """)
    fun getCharacters(): Single<List<Character>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeCharacters(characters: List<Character>): List<Long>

    @Query("DELETE FROM $TABLE_NAME_CHARACTERS")
    fun deleteAll()

}