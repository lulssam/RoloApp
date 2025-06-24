package dam.a50799.prj_roloapp.ui.theme.guide.process.wash

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dam.a50799.prj_roloapp.ui.theme.amareloTorrado

@Composable
fun WashScreenContent(
    onFinishClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Wash Step",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Rinse the film thoroughly in running water for 5 to 10 minutes.",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = onFinishClick,
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = amareloTorrado)
        ) {
            Text("Finish Process", fontSize = 20.sp)
        }
    }
}

@Composable
fun WashScreen(
    navController: NavController
) {
    WashScreenContent(
        onFinishClick = {
            navController.navigate("process") {
                popUpTo("process") { inclusive = true }
            }
        }
    )
}

