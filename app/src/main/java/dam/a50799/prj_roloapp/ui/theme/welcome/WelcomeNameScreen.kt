package dam.a50799.prj_roloapp.ui.theme.welcome

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.laranja
import dam.a50799.prj_roloapp.utils.customTextFieldColors
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import dam.a50799.prj_roloapp.ui.theme.cinzento
import dam.a50799.prj_roloapp.utils.dropShadow

@Composable
fun WelcomeNameScreenContent(
    name: String,
    onNameChange: (String) -> Unit,
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
            text = "Welcome!",
            fontFamily = Roboto,
            fontSize = 64.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(top = 60.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "What's your name?",
                fontSize = 32.sp,
                fontWeight = FontWeight.Light,
                fontFamily = Roboto
            )
            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                placeholder = {
                    Text(
                        text = "Insert Name",
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Medium,
                        color = cinzento,
                        fontSize = 20.sp,
                        letterSpacing = 4.sp
                    )
                },
                textStyle = LocalTextStyle.current.copy(
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    letterSpacing = 4.sp,
                    color = Color.Black
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .width(340.dp)
                    .dropShadow(),
                shape = RoundedCornerShape(20.dp),
                colors = customTextFieldColors(),
            )
            Spacer(Modifier.height(24.dp))
        }

        Button(
            onClick = onNextClick,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp)
                .width(310.dp)
                .height(60.dp)
                .dropShadow(),
            colors = ButtonDefaults.buttonColors(laranja, Color.Black),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Text(
                text = "Next",
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                letterSpacing = 7.sp
            )
        }

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

