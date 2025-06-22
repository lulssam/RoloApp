package dam.a50799.prj_roloapp.ui.theme.chemicals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dam.a50799.prj_roloapp.data.local.dao.ChemicalDao
import dam.a50799.prj_roloapp.data.local.entities.Chemical
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChemicalsViewModel(
    private val chemicalDao: ChemicalDao
) : ViewModel() {
    val chemicals: StateFlow<List<Chemical>> = chemicalDao.getAllChemicals()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        viewModelScope.launch {
            chemicalDao.getAllChemicals().collect { chemicals ->
                println("Número de químicos na base de dados: ${chemicals.size}")
            }
        }
    }
}