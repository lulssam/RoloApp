package dam.a50799.prj_roloapp.ui.theme.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var verifyPassword = mutableStateOf("")
    var arePasswordsEqual = mutableStateOf(true)

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun registerUser(
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        if (password.value != verifyPassword.value){
            arePasswordsEqual.value = false
            onFailure("Passwords do not match")
            return
        }

        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email.value, password.value)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        onSuccess()
                    } else {
                        onFailure(task.exception?.message?: "Registration failed")
                    }
                }
        }
    }

}