package nl.stephanmantel.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

internal const val TABLE_NAME_CHARACTERS = "Characters"

@Parcelize
@Entity(tableName = TABLE_NAME_CHARACTERS)
data class Character (
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "birthYear")
    val birthYear: Float?
): Parcelable {
    @Ignore
    @IgnoredOnParcel
    var isFavourite: Boolean = false
}