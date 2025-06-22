package dam.a50799.prj_roloapp.ui.theme.films

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dam.a50799.prj_roloapp.data.local.dao.FilmDao
import dam.a50799.prj_roloapp.data.local.entities.Film
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FilmViewModel(
    private val filmDao: FilmDao
) : ViewModel() {
    //private val _films = MutableStateFlow<List<Film>>(emptyList())
    val films: StateFlow<List<Film>> = filmDao.getAllFilms()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        viewModelScope.launch {
            filmDao.getAllFilms().collect { films ->
                println("Número de filmes na base de dados: ${films.size}")
            }
        }
    }
}
