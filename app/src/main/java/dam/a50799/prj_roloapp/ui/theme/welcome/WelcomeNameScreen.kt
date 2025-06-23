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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.laranja
import dam.a50799.prj_roloapp.utils.customTextFieldColors
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WelcomeNameScreenContent(
    name: String,
    onNameChange: (String) -> Unit,
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("What's your name?", fontSize = 32.sp, fontWeight = FontWeight.Bold, fontFamily = Roboto)
        Spacer(Modifier.height(24.dp))
        OutlinedTextField(
            value = name, onValueChange = onNameChange,
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
fun WelcomeNameScreen(
    navController: NavController,
    viewModel: WelcomeViewModel
) {
    val name by viewModel.name.collectAsState()
    WelcomeNameScreenContent(
        name = name,
        onNameChange = { viewModel.updateName(it) },
        onNextClick = { navController.navigate("welcome_age") }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeNameScreenContentPreview() {
    WelcomeNameScreenContent(
        name = "John Doe",
        onNameChange = {},
        onNextClick = {}
    )
}

