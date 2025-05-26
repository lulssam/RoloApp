package dam.a50799.prj_roloapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class Film(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val iso: Int,
    val developer: String
)