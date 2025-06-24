package dam.a50799.prj_roloapp.ui.theme.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dam.a50799.prj_roloapp.R
import dam.a50799.prj_roloapp.data.local.entities.UserProfile
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.laranja
import dam.a50799.prj_roloapp.utils.dropShadow

@Composable
fun ProfileScreenContent(
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onEditClick: () -> Unit,
    onSaveClick: () -> Unit,
    navController: NavController?,
    userProfile: UserProfile?,
    isEditing: Boolean = false,
    editedName: String = "",
    editedAge: String = "",
    editedFavFilm: String = "",
    onNameChange: (String) -> Unit = {},
    onAgeChange: (String) -> Unit = {},
    onFavFilmChange: (String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) {
        // Botões de topo (voltar e configurações)
        IconButton(
            onClick = onSettingsClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 24.dp)
                .size(40.dp)
        ) {
            Icon(
                modifier = Modifier.size(34.dp),
                painter = painterResource(id = R.drawable.settings),
                contentDescription = "Settings Icon",
                tint = Color.Black
            )
        }

        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 24.dp)
                .size(40.dp)
        ) {
            Icon(
                modifier = Modifier.size(34.dp),
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "Back Icon",
                tint = Color.Black
            )
        }

        // Área de perfil
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Ícone de perfil
            Icon(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.default_icon),
                contentDescription = "Profile Icon",
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Nome (editável)
            if (isEditing) {
                OutlinedTextField(
                    value = editedName,
                    onValueChange = onNameChange,
                    label = { Text("Name") },
                    modifier = Modifier.padding(horizontal = 32.dp),
                    textStyle = TextStyle(
                        fontSize = 36.sp,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                )
            } else {
                Text(
                    text = userProfile?.name ?: "User",
                    fontSize = 36.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (isEditing) {
                OutlinedTextField(
                    value = editedAge,
                    onValueChange = onAgeChange,
                    label = { Text("Age") },
                    modifier = Modifier.padding(horizontal = 32.dp),
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 1.sp
                    )
                )
            } else {
                Text(
                    text = "Age: ${userProfile?.age ?: "Not set"}",
                    fontSize = 20.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Filme favorito (editável)
            if (isEditing) {
                OutlinedTextField(
                    value = editedFavFilm,
                    onValueChange = onFavFilmChange,
                    label = { Text("Favorite Film") },
                    modifier = Modifier.padding(horizontal = 32.dp),
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Light
                    )
                )
            } else {
                Text(
                    text = "Favorite Film: ${userProfile?.favFilm ?: "Not set"}",
                    fontSize = 14.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Light
                )
            }
        }

        Button(
            modifier = Modifier
                .padding(bottom = 40.dp)
                .width(330.dp)
                .height(70.dp)
                .align(Alignment.BottomCenter)
                .dropShadow(),
            onClick = {
                if (isEditing) onSaveClick() else onEditClick()
            },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = laranja,
                contentColor = Color.Black
            ),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Text(
                if (isEditing) "Save Changes" else "Edit Profile",
                fontSize = 24.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ProfileScreen(
    navController: NavController
) {
    val user = remember { FirebaseAuth.getInstance().currentUser }
    var userProfile by remember { mutableStateOf<UserProfile?>(null) }
    var isEditing by remember { mutableStateOf(false) }
    var editedName by remember { mutableStateOf("") }
    var editedAge by remember { mutableStateOf("") }
    var editedFavFilm by remember { mutableStateOf("") }

    // Carregar dados do perfil
    LaunchedEffect(user) {
        if (user != null) {
            val db = FirebaseFirestore.getInstance()
            db.collection("users").document(user.uid).get()
                .addOnSuccessListener { document ->
                    userProfile = UserProfile(
                        uid = user.uid,
                        name = document.getString("name") ?: "",
                        age = document.getString("age") ?: "",
                        favFilm = document.getString("favFilm") ?: ""
                    )
                    // Inicializar campos editáveis
                    editedName = userProfile?.name ?: ""
                    editedAge = userProfile?.age ?: ""
                    editedFavFilm = userProfile?.favFilm ?: ""
                }
        }
    }

    ProfileScreenContent(
        onBackClick = { navController.popBackStack() },
        onSettingsClick = { navController.navigate("settings") },
        onEditClick = { isEditing = true },
        onSaveClick = {
            // Salvar alterações no Firebase
            if (user != null) {
                val db = FirebaseFirestore.getInstance()
                db.collection("users").document(user.uid).update(
                    mapOf(
                        "name" to editedName,
                        "age" to editedAge,
                        "favFilm" to editedFavFilm
                    )
                ).addOnSuccessListener {
                    userProfile = userProfile?.copy(
                        name = editedName,
                        age = editedAge,
                        favFilm = editedFavFilm
                    )
                    isEditing = false
                }
            }
        },
        navController = navController,
        userProfile = userProfile,
        isEditing = isEditing,
        editedName = editedName,
        editedAge = editedAge,
        editedFavFilm = editedFavFilm,
        onNameChange = { editedName = it },
        onAgeChange = { editedAge = it },
        onFavFilmChange = { editedFavFilm = it }
    )
}
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreenContent(
        onBackClick = {},
        onSettingsClick = {},
        onEditClick = {},
        onSaveClick = {},
        navController = null,
        userProfile = null
    )
}