package dam.a50799.prj_roloapp.ui.theme.guide.process.develop

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dam.a50799.prj_roloapp.ui.theme.amareloTorrado
import dam.a50799.prj_roloapp.ui.theme.guide.process.ProcessDataViewModel
import org.checkerframework.checker.units.qual.Temperature

@Composable
fun DevelopScreenContent(
    developerName: String,
    temperature: Int,
    timeLeft: Int,
    onNextClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Developer Step",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Using: $developerName at $temperature ºC",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // TIMER
        Text(
            text = formatTime(timeLeft),
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onNextClick,
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = amareloTorrado)
        ) {
            Text("Next: Stop Bath", fontSize = 20.sp)
        }
    }
}

@Composable
fun DeveloperScreen(
    viewModel: ProcessDataViewModel = viewModel(),
    navController: NavController
) {

    val timeLeft by remember { derivedStateOf { viewModel.currentTime } }

    // iniciar timer quando entra no ecrã
    LaunchedEffect(Unit) {
        viewModel.startDevelopTimer()
    }

    DevelopScreenContent(
        developerName = viewModel.developer,
        temperature = viewModel.temperature,
        timeLeft = timeLeft,
        onNextClick = {
            viewModel.stopTimer()
            navController.navigate("stop_bath")
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DeveloperScreenPreview(){
    DevelopScreenContent(
        developerName = "Kodak-D76",
        temperature = 20,
        timeLeft = 4,
        onNextClick = {}
    )
}

fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val secs = seconds % 60
    return String.format("%02d:%02d", minutes, secs)
}