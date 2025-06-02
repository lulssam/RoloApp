package dam.a50799.prj_roloapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import dam.a50799.prj_roloapp.data.local.entities.Film
import kotlinx.coroutines.flow.Flow

@Dao // data access object
/**
 * Interface que trata de todos os filmes*/
interface FilmDao{
    @Query("SELECT * FROM films")
    fun getAllFilms(): Flow<List<Film>>

    @Query("SELECT * FROM films WHERE id = :id")
    suspend fun getFilmById(id: Int): Film

    @Upsert //update e insert juntos para evitar sobreposições
    /**
     * Função suspensa para correr em courotine*/
    suspend fun insertFilm(film: Film)

    @Delete
    suspend fun deleteFilm(film: Film)
}