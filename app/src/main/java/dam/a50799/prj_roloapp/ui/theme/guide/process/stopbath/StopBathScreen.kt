package dam.a50799.prj_roloapp.ui.theme.guide.process.stopbath

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dam.a50799.prj_roloapp.ui.theme.amareloTorrado

@Composable
fun StopBathScreenContent(
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
            text = "Stop Bath",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Rinse the film with water to stop development.",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = onNextClick,
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = amareloTorrado)
        ) {
            Text("Next: Fixer", fontSize = 20.sp)
        }
    }
}

@Composable
fun StopBathScreen(
    navController: NavController
) {
    StopBathScreenContent(
        onNextClick = {
            navController.navigate("fixer")
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StopBathPreview() {
    StopBathScreenContent(
        onNextClick = {}
    )
}