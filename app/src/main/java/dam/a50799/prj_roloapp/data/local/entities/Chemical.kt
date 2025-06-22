package dam.a50799.prj_roloapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chemicals")
data class Chemical(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val type: String,
    val dilution: String,
    val timeInMinutes: String,
    val temperature: Int,
    val description: String,
    val imageUri: String?
)
