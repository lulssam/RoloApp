package dam.a50799.prj_roloapp.ui.theme.settings.account

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AccountViewModel : ViewModel() {
    private val _newEmail = MutableStateFlow("")
    val newEmail: StateFlow<String> = _newEmail

    private val _newPassword = MutableStateFlow("")
    val newPassword: StateFlow<String> = _newPassword

    fun updateEmail(email: String) {
        _newEmail.value = email
    }

    fun updatePassword(password: String) {
        _newPassword.value = password
    }
}
