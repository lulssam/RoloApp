package dam.a50799.prj_roloapp.ui.theme.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
        if (password.value != verifyPassword.value) {
            arePasswordsEqual.value = false
            onFailure("Passwords do not match")
            return
        }

        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email.value, password.value)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onSuccess()
                    } else {
                        onFailure(task.exception?.message ?: "Registration failed")
                    }
                }
        }
    }

    fun checkIfUserProfileExists(onResult: (Boolean) -> Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            onResult(false)
            return
        }

        val db = FirebaseFirestore.getInstance()
        val userDocRef = db.collection("users").document(user.uid)

        userDocRef.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null && document.exists()) {
                        // perfil existe
                        onResult(true)
                    } else {
                        // perfil não existe
                        onResult(false)
                    }
                } else {
                    // erro ao buscar informações
                    onResult(false)
                }
            }
            .addOnFailureListener {
                // erro
                onResult(false)
            }
    }

    fun saveUserProfile(name: String, onComplete: (Boolean) -> Unit) {
        val user = FirebaseAuth.getInstance().currentUser ?: run {
            onComplete(false)
            return
        }

        val db = FirebaseFirestore.getInstance()

        val userData = hashMapOf(
            "name" to name,
            "email" to user.email
        )

        db.collection("users").document(user.uid)
            .set(userData)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

}