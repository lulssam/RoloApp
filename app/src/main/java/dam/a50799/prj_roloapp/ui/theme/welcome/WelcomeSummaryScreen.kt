package dam.a50799.prj_roloapp.ui.theme.welcome

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dam.a50799.prj_roloapp.data.local.dao.UserProfileDao
import dam.a50799.prj_roloapp.data.local.entities.UserProfile
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.amareloTorrado
import dam.a50799.prj_roloapp.ui.theme.register.RegisterViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun WelcomeSummaryScreen(
    navController: NavController,
    viewModel: WelcomeViewModel = viewModel(),
    registerViewModel: RegisterViewModel = viewModel(),
) {

    val context = LocalContext.current
    val name by viewModel.name.collectAsState()
    val age by viewModel.age.collectAsState()
    val favoriteFilm by viewModel.favFilm.collectAsState()

    Log.d("SUMMARY", "Name: $name, Age: $age, Fav Film: $favoriteFilm")

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Summary", fontSize = 32.sp, fontWeight = FontWeight.Bold, fontFamily = Roboto)
        Spacer(Modifier.height(24.dp))
        Text("Name: $name", fontSize = 24.sp)
        Text("Age: $age", fontSize = 24.sp)
        Text("Favorite Film Stock: $favoriteFilm", fontSize = 24.sp)
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = {
                val (name, age, favFilm) = viewModel.getProfileData()

                registerViewModel.saveUserProfile(
                    name = name,
                    age = age,
                    favFilm = favFilm,
                    onComplete = { success ->
                        if (success) {
                            Toast.makeText(context, "Profile Saved!", Toast.LENGTH_SHORT).show()
                            navController.navigate("homescreen")
                        } else {
                            Toast.makeText(context, "Failed to save profile!", Toast.LENGTH_SHORT).show()
                        }

                    }
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(amareloTorrado, Color.Black)
        ) { Text("Start", fontSize = 20.sp) }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeSummaryScreenPreview(){
    val welcomeViewModel = WelcomeViewModel().apply {
        updateName("John Doe")
        updateAge("30")
        updateFilm("Kodak Portra 400")
    }

    val registerViewModel = RegisterViewModel()

    WelcomeSummaryScreen(
        navController = rememberNavController(),
        viewModel = welcomeViewModel,
        registerViewModel = registerViewModel
    )
}
