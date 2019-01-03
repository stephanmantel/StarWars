package nl.stephanmantel.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

internal const val TABLE_NAME_CHARACTERS = "Characters"

@Entity(tableName = TABLE_NAME_CHARACTERS)
data class Character (
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "birthYear")
    val birthYear: Float?
)