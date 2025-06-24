package dam.a50799.prj_roloapp.ui.theme.guide.process.stopbath

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.amareloTorrado
import dam.a50799.prj_roloapp.utils.dropShadow

@Composable
fun StopBathScreenContent(
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 32.dp)
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Stop Bath",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
        )
        Text(
            text = "Simply rinse the film with water to stop the development",
            fontFamily = Roboto,
            fontWeight = FontWeight.Black,
            fontSize = 40.sp,
            lineHeight = 53.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .width(300.dp)
                .padding(vertical = 16.dp)
        )

        Button(
            onClick = onNextClick,
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = amareloTorrado, Color.Black),
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier
                .padding(bottom = 40.dp)
                .fillMaxWidth()
                .height(60.dp)
                .dropShadow()
        ) {
            Text(
                text = "Next: Fixer",
                fontSize = 24.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium
            )
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