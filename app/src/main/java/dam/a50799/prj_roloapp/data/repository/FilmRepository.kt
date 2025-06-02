package dam.a50799.prj_roloapp.data.repository

import androidx.lifecycle.LiveData
import dam.a50799.prj_roloapp.data.local.dao.FilmDao
import dam.a50799.prj_roloapp.data.local.entities.Film
import kotlinx.coroutines.flow.Flow

class FilmRepository(
    private val filmDao: FilmDao
) {
    val allFilms: Flow<List<Film>> = filmDao.getAllFilms()

    suspend fun insert(film: Film){
        filmDao.insertFilm(film)
    }
}