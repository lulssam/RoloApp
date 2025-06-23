package dam.a50799.prj_roloapp.ui.theme.settings.about

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.laranja
import dam.a50799.prj_roloapp.utils.dropShadow

@Composable
fun AboutScreenContent(
    navController: NavController,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(
            text = "About",
            fontFamily = Roboto,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,

            modifier = Modifier
                .padding(top = 42.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "RoloApp",
            fontFamily = Roboto,
            fontSize = 48.sp,
            fontWeight = FontWeight.ExtraBold,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "This app is designed for film photography"+
                    " enthusiasts, created to simplify the home" +
                    "development process and make it easier to" +
                    "track results.",
            fontFamily = Roboto,
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 26.sp,
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "About Me",
            fontFamily = Roboto,
            fontSize = 48.sp,
            fontWeight = FontWeight.ExtraBold,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = buildAnnotatedString {
                append("Developed by Luísa Sampaio, passionate about analog photography " +
                        "and mobile development. This project was created as part of a final assignment in ")

                // Parte em bold
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Mobile Application Development")
                }

                append(".")
            },
            fontFamily = Roboto,
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 26.sp,
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { navController.popBackStack() },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(laranja, Color.Black),
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier
                .padding(bottom = 60.dp)
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

@Composable
fun AboutScreen(
    navController: NavController
){
    AboutScreenContent(navController)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreenPreview(){
    AboutScreenContent(
        navController = rememberNavController(),
    )
}
