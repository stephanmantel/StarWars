package nl.stephanmantel.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

internal const val TABLE_NAME_FILMS = "Films"

@Entity(tableName = TABLE_NAME_FILMS)
data class Film (
    @PrimaryKey
    @ColumnInfo(name = "episodeId")
    val episodeId: Int,

    @ColumnInfo(name = "title")
    val Title: String
)