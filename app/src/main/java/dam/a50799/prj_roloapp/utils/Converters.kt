package dam.a50799.prj_roloapp.utils

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromIntList(value: List<Int>): String =
        value.joinToString(separator = ",")

    @TypeConverter
    fun toIntList(value: String): List<Int> =
        if (value.isEmpty()) emptyList()
        else value.split(",").map { it.toInt() }
}