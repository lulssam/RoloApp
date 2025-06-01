package dam.a50799.prj_roloapp.ui.theme.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import dam.a50799.prj_roloapp.ui.theme.cinzento
import dam.a50799.prj_roloapp.utils.customTextFieldColors
import dam.a50799.prj_roloapp.utils.dropShadow

/**
 * Função que lida do UI do homescreen
 * @param onSBSClick: Ação quando clicarmos no step-by-step
 * @param onProfileClick  Ação quando clicarmos no perfil
 * @param onSettingsClick  Ação quando clicarmos nas definições
 * @param navController Instância do nav controller*/
@Composable
fun HomeScreenContent(
    onSBSClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    navController: NavController?
) {

    // region Box principal
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
    ) {

        // region icone do perfil
        IconButton(
            onClick = onProfileClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 24.dp)
                .size(72.dp)
        ) {
            Icon(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.default_icon),
                contentDescription = "Profile Icon",
                tint = Color.Unspecified
            )
        } // endregion
        // region texto inicial
        Text(
            text = "Hello, Luísa",
            fontSize = 28.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.Light,
            letterSpacing = 0.7.sp,
            modifier = Modifier.padding(start = 15.dp, top = 20.dp)
        )//endregion

        // region coluna principal
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(64.dp))

            // region texto develop film at home
            Text(
                "Develop Film At Home",
                fontSize = 60.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                lineHeight = 75.sp,
                modifier = Modifier.heightIn(min = 100.dp)
            ) // endregion

            Spacer(modifier = Modifier.height(12.dp))

            // region welcome text
            Text(
                "Welcome! RoloApp is an Android application that helps analog" + " photography enthusiasts who want to start developing rolls at home and" + " share them with others.",
                fontSize = 20.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Light,
                letterSpacing = 2.sp,
                lineHeight = 25.sp
                //textAlign = TextAlign.Justify
            )// endregion

            Spacer(modifier = Modifier.height(18.dp))

            // region botão step by step
            Button(
                modifier = Modifier
                    .width(372.dp)
                    .height(56.dp)
                    .dropShadow(),
                onClick = onSBSClick,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = amareloTorrado, contentColor = Color.Black
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
            } // endregion

            Spacer(modifier = Modifier.height(32.dp))

            // region box das opções
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .border(
                        BorderStroke(2.dp, cinzento), shape = RoundedCornerShape(20.dp)
                    )
                    .dropShadow()
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
            ) {

                // region colunas opções
                Column {
                    // region row timer
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clickable {/*TODO pagina timer*/ }
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = "Timer",
                            fontFamily = Roboto,
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.navigate_next),
                            contentDescription = "Go to Timer",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    } // endregion

                    HorizontalDivider(thickness = 2.dp, color = cinzento)

                    // region row films
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clickable {/*TODO página filmes*/ }
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = "Films",
                            fontFamily = Roboto,
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.navigate_next),
                            contentDescription = "Go to Community",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    } // endregion

                    HorizontalDivider(thickness = 2.dp, color = cinzento)

                    // region row chemicals
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clickable {/*TODO página quimicos*/ }
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween) {

                        Text(
                            text = "Chemicals",
                            fontFamily = Roboto,
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.navigate_next),
                            contentDescription = "Go to Films",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    } // endregion
                }

            }// endregion


        } // endregion
        // region icone settings
        IconButton(
            onClick = onSettingsClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 30.dp, bottom = 90.dp)
                .size(40.dp)
        ) {
            Icon(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.settings),
                contentDescription = "Profile Icon",
                tint = Color.Unspecified
            )
        } // endregion
    } // endregion
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(), navController: NavController
) {
    HomeScreenContent(
        onSBSClick = {
            // TODO navController.navigate("step_by_step")
        }, onProfileClick = {
            // TODO navController.navigate("profile")
        }, onSettingsClick = {
            navController.navigate("settings")
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
        onSettingsClick = {},
        navController = null
    )
}
