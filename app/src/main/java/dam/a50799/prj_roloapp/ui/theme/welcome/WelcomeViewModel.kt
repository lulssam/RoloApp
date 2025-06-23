package dam.a50799.prj_roloapp.ui.theme.welcome

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WelcomeViewModel() : ViewModel(){
    companion object{
        private const val TAG = "WelcomeViewModel"
    }

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name
    private val _age = MutableStateFlow("")
    val age: StateFlow<String> = _age
    private val _favFilm = MutableStateFlow("")
    val favFilm: StateFlow<String> = _favFilm

    fun updateName(newName: String){
        Log.d(TAG, "Updating name from '${_name.value}' to '$newName")
        _name.value = newName
        Log.d(TAG, "Name after update: '${_name.value}")
    }

    fun updateAge(newAge: String){
        Log.d(TAG, "Updating age from '${_age.value}' to '$newAge")
        _age.value = newAge
        Log.d(TAG, "Age after update: '${_age.value}")
    }

    fun updateFilm(newFilm: String){
        Log.d(TAG, "Updating film from '${_favFilm.value}' to '$newFilm")
        _favFilm.value = newFilm
        Log.d(TAG, "Fav Film after update: '${_favFilm.value}")
    }

    fun getProfileData(): Triple<String, String, String>{
        return Triple(_name.value, _age.value, _favFilm.value)
    }
    init {
        Log.d(TAG, "ViewModel criado: ${this.hashCode()}")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel  ${this.hashCode()}")
    }

}