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
    val birthYear: String
): Parcelable {
    @Ignore
    @IgnoredOnParcel
    var isFavourite: Boolean = false

    fun birthYearAsFloat(): Float? {
        if (birthYear == "unknown") return null

        val regex = Regex("([0-9]+\\.?)+([A-Z]+)")
        val groups = regex.find(birthYear)?.groupValues
        return groups?.let {
            if (groups.size != 3) return null
            val year = groups[1].toFloat()
            val era = groups[2]

            val eraMultiplier = if (era == "BBY") -1 else 1
            year * eraMultiplier
        }
    }
}