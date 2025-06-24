package dam.a50799.prj_roloapp.ui.theme.guide.process.develop

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.Room
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.amareloTorrado
import dam.a50799.prj_roloapp.ui.theme.cinzento
import dam.a50799.prj_roloapp.ui.theme.guide.process.ProcessDataViewModel
import dam.a50799.prj_roloapp.utils.dropShadow
import org.checkerframework.checker.units.qual.Temperature

@Composable
fun DevelopScreenContent(
    developerName: String,
    temperature: Int,
    timeLeft: Int,
    onNextClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .padding(top = 20.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Develop",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(38.dp))

            // Timer box
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .border(2.dp, cinzento, RoundedCornerShape(20.dp))
                    .dropShadow()
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(26.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = developerName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Roboto
                    )

                    Text(
                        text = formatTime(timeLeft),
                        fontSize = 80.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Roboto
                    )
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "During this step:",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Roboto
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "• Invert the tank continuously for the first 30 seconds.\n" +
                        "• Then, invert 3 times every minute.\n" +
                        "• Keep the temperature steady.\n" +
                        "• Tap the tank gently after each agitation to remove air bubbles.",
                fontSize = 18.sp,
                lineHeight = 30.sp,
                textAlign = TextAlign.Justify
            )
        }

        Button(
            onClick = onNextClick,
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = amareloTorrado, Color.Black),
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier
                .padding(bottom = 40.dp)
                .fillMaxWidth()
                .height(60.dp)
                .align(Alignment.BottomCenter)
                .dropShadow()
        ) {
            Text(
                text = "Next: Stop Bath",
                fontSize = 24.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium
            )
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