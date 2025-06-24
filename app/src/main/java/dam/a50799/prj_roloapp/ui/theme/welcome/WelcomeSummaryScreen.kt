package dam.a50799.prj_roloapp.ui.theme.guide.process.stopbath

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.amareloTorrado
import dam.a50799.prj_roloapp.ui.theme.cinzento
import dam.a50799.prj_roloapp.ui.theme.register.RegisterViewModel
import dam.a50799.prj_roloapp.ui.theme.welcome.WelcomeViewModel
import dam.a50799.prj_roloapp.utils.dropShadow

@Composable
fun SummaryScreenContent(
    name: String,
    age: String,
    favoriteFilm: String,
    onStartClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 32.dp)
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Summary",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = Roboto
        )

        // Conteúdo central
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Item de resumo - Nome
            SummaryItem(
                label = "Name",
                value = name
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Item de resumo - Idade
            SummaryItem(
                label = "Age",
                value = age
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Item de resumo - Filme favorito
            SummaryItem(
                label = "Favorite Film Stock",
                value = favoriteFilm
            )
        }

        // Botão de ação
        Button(
            onClick = onStartClick,
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = amareloTorrado,
                contentColor = Color.Black
            ),
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier
                .padding(bottom = 40.dp)
                .fillMaxWidth()
                .height(60.dp)
                .dropShadow()
        ) {
            Text(
                text = "Start",
                fontSize = 24.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// Componente reutilizável para cada item do resumo
@Composable
fun SummaryItem(
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontSize = 20.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = value,
            fontSize = 32.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun SummaryScreen(
    navController: NavController,
    viewModel: WelcomeViewModel = viewModel(),
    registerViewModel: RegisterViewModel = viewModel()
) {
    val context = LocalContext.current
    val name by viewModel.name.collectAsState()
    val age by viewModel.age.collectAsState()
    val favoriteFilm by viewModel.favFilm.collectAsState()

    SummaryScreenContent(
        name = name,
        age = age,
        favoriteFilm = favoriteFilm,
        onStartClick = {
            registerViewModel.saveUserProfile(
                name = name,
                age = age,
                favFilm = favoriteFilm,
                onComplete = { success ->
                    if (success) {
                        Toast.makeText(context, "Profile Saved!", Toast.LENGTH_SHORT).show()
                        navController.navigate("homescreen")
                    } else {
                        Toast.makeText(context, "Failed to save profile!", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SummaryScreenPreview() {
    SummaryScreenContent(
        name = "Mariana Silva",
        age = "28",
        favoriteFilm = "Kodak Portra 400",
        onStartClick = {}
    )
}