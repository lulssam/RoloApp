package dam.a50799.prj_roloapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey val uid: String,
    val name: String,
    val age: String,
    val favFilm: String
)