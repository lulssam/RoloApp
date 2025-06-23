package dam.a50799.prj_roloapp.ui.theme.welcome

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.laranja
import dam.a50799.prj_roloapp.utils.customTextFieldColors

@Composable
fun WelcomeFilmScreenContent(
    film: String,
    onFilmChange: (String) -> Unit,
    onNextClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "What's your favorite film stock?",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Roboto
        )
        Spacer(Modifier.height(24.dp))
        OutlinedTextField(
            value = film, onValueChange = onFilmChange,
            placeholder = { Text("Nome") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = customTextFieldColors()
        )
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = onNextClick,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(laranja, Color.Black)
        ) { Text("Next", fontSize = 20.sp) }
    }
}


@Composable
fun WelcomeFilmScreen(
    navController: NavController,
    viewModel: WelcomeViewModel
) {
    val film by viewModel.favFilm.collectAsState()
    WelcomeFilmScreenContent(
        film = film,
        onFilmChange = { viewModel.updateFilm(it) },
        onNextClick = { navController.navigate("welcome_summary") }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeFilmScreenContentPreview() {
    WelcomeFilmScreenContent(
        film = "Kodak Portra 400",
        onFilmChange = {},
        onNextClick = {}
    )
}
