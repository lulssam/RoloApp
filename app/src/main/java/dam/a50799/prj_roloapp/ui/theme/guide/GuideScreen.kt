package dam.a50799.prj_roloapp.ui.theme.guide

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GuideScreen(
    viewModel: GuideViewModel = viewModel()
){
    val instructions by viewModel.instructions.collectAsState()

    var film by remember { mutableStateOf("") }
    var developer by remember { mutableStateOf("") }
    var temperature by remember { mutableStateOf("") }
    var iso by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(24.dp)) {
        Text("Passo a Passo AI", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(value = film, onValueChange = { film = it }, label = { Text("Filme") })
        OutlinedTextField(value = developer, onValueChange = { developer = it }, label = { Text("Developer") })
        OutlinedTextField(value = temperature, onValueChange = { temperature = it }, label = { Text("Temperatura (ºC)") })
        OutlinedTextField(value = iso, onValueChange = { iso = it }, label = { Text("ISO") })

        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            viewModel.fetchInstructions(film, developer, temperature, iso)
        }) {
            Text("Gerar Instruções AI")
        }

        Spacer(Modifier.height(24.dp))
        Text(text = instructions)
    }
}