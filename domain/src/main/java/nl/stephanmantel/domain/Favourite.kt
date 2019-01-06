package nl.stephanmantel.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

internal const val TABLE_NAME_FAVOURITES = "Favourites"

@Entity(tableName = TABLE_NAME_FAVOURITES)
data class Favourite (
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String
)