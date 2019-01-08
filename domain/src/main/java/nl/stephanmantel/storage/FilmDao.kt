package nl.stephanmantel.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import nl.stephanmantel.domain.Film
import nl.stephanmantel.domain.TABLE_NAME_FILMS

@Dao
interface FilmDao {

    @Query("""
        SELECT * FROM $TABLE_NAME_FILMS
    """)
    fun getFilms(): Single<List<Film>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeFilm(film: Film): Long

    @Query("DELETE FROM $TABLE_NAME_FILMS")
    fun deleteAll()

}