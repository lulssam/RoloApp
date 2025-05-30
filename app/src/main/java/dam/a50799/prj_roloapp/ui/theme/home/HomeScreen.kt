package dam.a50799.prj_roloapp.ui.theme.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.amareloTorrado
import dam.a50799.prj_roloapp.R

@Composable
fun HomeScreenContent(
    onSBSClick: () -> Unit,
    onProfileClick: () -> Unit,
    navController: NavController?
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(
            onClick = onProfileClick,
            modifier = Modifier.align(Alignment.TopEnd)
                .size(64.dp)
        ) {
            Icon(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.default_icon),
                contentDescription = "Profile Icon",
                tint = Color.Unspecified
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(64.dp))

            Text(
                "Develop Film At Home",
                fontSize = 60.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                lineHeight = 55.sp,
                modifier = Modifier.heightIn(min = 100.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Welcome! RoloApp is an Android application that helps analog" +
                        " photography enthusiasts who want to start developing rolls at home and" +
                        " share them with others.",
                fontSize = 20.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Light,
                letterSpacing = 1.sp,
                lineHeight = 25.sp
                //textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(18.dp))

            Button(
                modifier = Modifier
                    .width(372.dp)
                    .height(56.dp),
                onClick = onSBSClick,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = amareloTorrado,
                    contentColor = Color.Black
                ),
                border = BorderStroke(2.dp, Color.Black)

            ) {
                Text(
                    "Step-by-step Guide",
                    fontSize = 24.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 3.sp
                )
            }

        }
    }
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navController: NavController
) {
    HomeScreenContent(
        onSBSClick = {
            // TODO navController.navigate("step_by_step")
        },
        onProfileClick = {
            // TODO navController.navigate("profile")
        },
        navController = navController
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(
        onSBSClick = {},
        onProfileClick = {},
        navController = null
    )
}
