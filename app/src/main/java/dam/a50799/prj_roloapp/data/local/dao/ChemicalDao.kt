package dam.a50799.prj_roloapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dam.a50799.prj_roloapp.data.local.entities.Chemical
import kotlinx.coroutines.flow.Flow

@Dao
interface ChemicalDao {

    @Query("SELECT * FROM chemicals")
    fun getAllChemicals(): Flow<List<Chemical>>

    @Query("SELECT * FROM chemicals WHERE type = :type")
    fun getChemicalsByType(type: String): Flow<List<Chemical>>

    @Query("SELECT * FROM chemicals WHERE id = :id")
    suspend fun getChemicalsById(id: Int): Chemical

    @Insert
    suspend fun insertChemical(chemical: Chemical)

    @Delete
    suspend fun deleteChemical(chemical: Chemical)

    @Update
    suspend fun updateChemical(chemical: Chemical)
}