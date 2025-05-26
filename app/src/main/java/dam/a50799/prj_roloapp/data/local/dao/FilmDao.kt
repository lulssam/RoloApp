package dam.a50799.prj_roloapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dam.a50799.prj_roloapp.data.local.entities.Film

@Dao
interface FilmDao{
    @Query("SELECT * FROM films")
    fun getAllFilms(): LiveData<List<Film>>

    @Insert
    suspend fun insertFilm(film: Film)
}