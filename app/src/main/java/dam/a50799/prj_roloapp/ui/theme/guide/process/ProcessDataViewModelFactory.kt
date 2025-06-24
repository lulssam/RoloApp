package dam.a50799.prj_roloapp.ui.theme.guide.process

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dam.a50799.prj_roloapp.data.local.entities.Chemical
import dam.a50799.prj_roloapp.data.local.entities.Film

class ProcessDataViewModelFactory(
    private val films: List<Film>,
    private val chemicals: List<Chemical>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProcessDataViewModel::class.java)) {
            return ProcessDataViewModel(films, chemicals) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}