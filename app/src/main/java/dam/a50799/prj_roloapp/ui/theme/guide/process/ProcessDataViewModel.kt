package dam.a50799.prj_roloapp.ui.theme.guide.process

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dam.a50799.prj_roloapp.data.local.entities.Chemical
import dam.a50799.prj_roloapp.data.local.entities.Film

class ProcessDataViewModel(
    initialFilms: List<Film>,
    initialChemicals: List<Chemical>
) : ViewModel() {
    val films = initialFilms
    val chemicals = initialChemicals

    val selectedFilm = mutableStateOf<Film?>(null)
    val selectedDeveloper = mutableStateOf<Chemical?>(null)
    val selectedFixer = mutableStateOf<Chemical?>(null)

    val developers = chemicals.filter { it.type == "DEVELOPER" }
    val fixers = chemicals.filter { it.type == "FIXER" }

    fun selectFilm(film: Film) {
        selectedFilm.value = film
        Log.d("ProcessDebug", "Selected film: ${film.name}")
    }

    fun selectDeveloper(developer: Chemical) {
        selectedDeveloper.value = developer
        Log.d("ProcessDebug", "Selected developer: ${developer.name}")
    }

    fun selectFixer(fixer: Chemical) {
        selectedFixer.value = fixer
        Log.d("ProcessDebug", "Selected fixer: ${fixer.name}")
    }
}

