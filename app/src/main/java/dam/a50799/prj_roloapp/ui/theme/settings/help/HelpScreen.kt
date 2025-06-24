package dam.a50799.prj_roloapp.ui.theme.settings.help
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.laranja
import dam.a50799.prj_roloapp.utils.dropShadow

@Composable
fun HelpScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Help",
            fontSize = 42.sp,
            fontWeight = FontWeight.Black,
            fontFamily = Roboto,
            modifier = Modifier.padding(top = 30.dp)
        )

        Text(
            text = "How to Use RoloApp",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Roboto
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "1. Set up your profile with your name, age, and favorite film stock.\n" +
                    "2. Browse films and chemicals.\n" +
                    "3. Start a new development session.\n" +
                    "4. Follow the step-by-step guide. \n",
            fontSize = 18.sp,
            fontFamily = Roboto
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "FAQs",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Roboto
        )

        Text(
            text = "• Can I add custom films? Not yet.\n",
            fontSize = 18.sp,
            fontFamily = Roboto
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { navController.popBackStack() },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(laranja, Color.Black),
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .dropShadow()
        ) {
            Text(
                text = "Back",
                fontSize = 20.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HelpScreenPreview() {
    HelpScreen(navController = rememberNavController())
}
