package dam.a50799.prj_roloapp.ui.theme.guide

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dam.a50799.prj_roloapp.R
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.amareloTorrado
import dam.a50799.prj_roloapp.ui.theme.azulinho
import dam.a50799.prj_roloapp.utils.dropShadow

@Composable
fun GuideScreenContent(
    onNextClick: () -> Unit,
    onSettingsClick: () -> Unit,
    navController: NavController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, bottom = 80.dp)
    ) {
        IconButton(
            onClick = onSettingsClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 24.dp)
                .size(40.dp)
        ) {
            Icon(
                modifier = Modifier.size(34.dp),
                painter = painterResource(id = R.drawable.settings),
                contentDescription = "Settings Icon",
                tint = Color.Black
            )
        }

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 24.dp)
                .size(40.dp)
        ) {
            Icon(
                modifier = Modifier.size(34.dp),
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "Back Icon",
                tint = Color.Black
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            // box da imagem
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .dropShadow()
                    .background(
                        color = azulinho,
                        shape = RoundedCornerShape(20.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(170.dp),
                    painter = painterResource(R.drawable.cannister),
                    contentDescription = "Cannister",
                    colorFilter = null
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Prepare the Equipment",
                fontFamily = Roboto,
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Gather all necessary equipment such as a developing tank, reals and bottles of processing chemicals.",
                fontSize = 20.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Light,
                modifier = Modifier.width(300.dp),
                textAlign = TextAlign.Justify
            )
        }

        Button(
            onClick = { onNextClick() },  // chama a função
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(amareloTorrado, Color.Black),
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 24.dp)
                .align(Alignment.BottomCenter)
                .dropShadow()
        ) {
            Text(
                text = "Next",
                fontSize = 20.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun GuideScreen(
    viewModel: GuideViewModel,
    navController: NavController
) {
    GuideScreenContent(
        onNextClick = { navController.navigate("process") },
        onSettingsClick = { navController.navigate("settings") },
        navController = navController
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GuidePreview() {
    GuideScreenContent(
        onNextClick = {},
        onSettingsClick = {},
        navController = rememberNavController()
    )
}
