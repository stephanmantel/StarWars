package nl.stephanmantel.storage

import androidx.room.*
import io.reactivex.Single
import nl.stephanmantel.domain.Favourite
import nl.stephanmantel.domain.TABLE_NAME_FAVOURITES

@Dao
interface FavouritesDao {

    @Query("""
        SELECT *
        FROM $TABLE_NAME_FAVOURITES
    """)
    fun getFavourites(): Single<List<Favourite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavourite(favourite: Favourite): Long

    @Delete
    fun deleteFavourite(favourite: Favourite)

    @Query("DELETE FROM $TABLE_NAME_FAVOURITES")
    fun deleteAll()

}