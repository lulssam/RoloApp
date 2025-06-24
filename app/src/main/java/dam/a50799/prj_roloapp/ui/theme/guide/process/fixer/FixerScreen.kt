package dam.a50799.prj_roloapp.ui.theme.guide.process.fixer

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
import dam.a50799.prj_roloapp.ui.theme.guide.process.develop.formatTime

@Composable
fun FixerScreenContent(
    fixerName: String,
    temperature: Int,
    timeLeft: Int,
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Fixer Step",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Using: $fixerName at $temperature ºC",
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
            Text("Next: Wash", fontSize = 20.sp)
        }
    }
}

@Composable
fun FixerScreen(
    viewModel: ProcessDataViewModel = viewModel(),
    navController: NavController
) {
    val timeLeft by remember { derivedStateOf { viewModel.currentTime } }

    // Inicia timer quando entra no ecrã
    LaunchedEffect(Unit) {
        viewModel.startFixerTimer()
    }

    FixerScreenContent(
        fixerName = viewModel.fixer,
        temperature = viewModel.temperature,
        timeLeft = timeLeft,
        onNextClick = {
            viewModel.stopTimer()
            navController.navigate("wash")
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FixerPreview(){
    FixerScreenContent(
        fixerName = "Ilford Rapid Fixer",
        temperature = 20,
        timeLeft = 10,
        onNextClick = {}
    )
}
