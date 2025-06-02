package dam.a50799.prj_roloapp.ui.theme.films

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dam.a50799.prj_roloapp.data.local.dao.FilmDao

class FilmViewModelFactory(
    private val filmDao: FilmDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilmViewModel::class.java)){
            @Suppress ("UNCHECKED_CAST")
            return FilmViewModel(filmDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}