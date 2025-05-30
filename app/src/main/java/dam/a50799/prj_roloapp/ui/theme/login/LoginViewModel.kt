package dam.a50799.prj_roloapp.ui.theme.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dam.a50799.prj_roloapp.data.auth.SignInResults
import dam.a50799.prj_roloapp.data.auth.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _auth: FirebaseAuth = FirebaseAuth.getInstance()
    var email = mutableStateOf("")
    var password = mutableStateOf("")

    private val _isLoggedIn = MutableStateFlow(false)
    var isLoggedIn = _isLoggedIn.asStateFlow()

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResults) {
        val success = result.data != null
        _state.update {
            it.copy(
                isSignedSuccessfull = result.data != null,
                signInError = result.errorMessage
            )
        }

        _isLoggedIn.value = success
    }

    fun resetState() {
        _state.update { SignInState() }
    }

    fun login() {
        val emailValue = email.value.trim()
        val passwordValue = password.value

        if (emailValue.isEmpty() || passwordValue.isEmpty()){
            _state.value = SignInState(signInError = "Email and password cannot be empty!")
            return
        }
        viewModelScope.launch {
            _auth.signInWithEmailAndPassword(emailValue, passwordValue)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        _isLoggedIn.value = true
                        _state.value = SignInState(isSignedSuccessfull = true)
                    } else {
                        _isLoggedIn.value = false
                        _state.value = SignInState(signInError = task.exception?.localizedMessage?: "Login Failed")
                    }
                }
        }
    }
}