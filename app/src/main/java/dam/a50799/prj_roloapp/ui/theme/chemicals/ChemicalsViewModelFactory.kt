package dam.a50799.prj_roloapp.ui.theme.chemicals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dam.a50799.prj_roloapp.data.local.dao.ChemicalDao

class ChemicalsViewModelFactory(
    private val chemicalDao: ChemicalDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChemicalsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChemicalsViewModel(chemicalDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}