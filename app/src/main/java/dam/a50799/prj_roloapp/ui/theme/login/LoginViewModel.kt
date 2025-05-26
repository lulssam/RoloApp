package dam.a50799.prj_roloapp.ui.theme.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var isLoggedIn = mutableStateOf(false)

    fun login(){
        // teste inicial
        if (email.value == "teste@exemplo.com" && password.value == "1234") isLoggedIn.value = true
        else isLoggedIn.value = false
    }
}