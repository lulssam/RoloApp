package dam.a50799.prj_roloapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dam.a50799.prj_roloapp.data.local.entities.UserProfile

@Dao
interface UserProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: UserProfile)

    @Query("SELECT * FROM user_profile WHERE uid =:uid LIMIT 1")
    suspend fun getUserProfile(uid: String): UserProfile?

    @Delete
    suspend fun deleteUserProfile(userProfile: UserProfile)
}