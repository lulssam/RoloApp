package dam.a50799.prj_roloapp.ui.theme.welcome

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.cinzento
import dam.a50799.prj_roloapp.ui.theme.laranja
import dam.a50799.prj_roloapp.utils.dropShadow
import dam.a50799.prj_roloapp.data.local.entities.Film
import dam.a50799.prj_roloapp.utils.loadFilmsJson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeFilmScreenContent(
    userName: String,
    film: String,
    onFilmChange: (String) -> Unit,
    onNextClick: () -> Unit
) {
    val context = LocalContext.current
    val allFilms = remember { loadFilmsJson(context) }

    var expanded by remember { mutableStateOf(false) }

    val filteredFilms = remember(film, allFilms) {
        if (film.isEmpty()) {
            allFilms
        } else {
            allFilms.filter {
                it.name.contains(film, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*Text(
            text = "Hello $userName!",
            fontFamily = Roboto,
            fontSize = 64.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(top = 60.dp)
        )*/

        AutoResizeTitle(text = "Hello $userName!")

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "What's your favorite film?",
                fontSize = 32.sp,
                fontWeight = FontWeight.Light,
                fontFamily = Roboto,
                lineHeight = 40.sp
            )
            Spacer(Modifier.height(24.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it }
            ) {
                OutlinedTextField(
                    value = film,
                    onValueChange = {
                        onFilmChange(it)
                        expanded = true
                    },
                    placeholder = {
                        Text(
                            text = "Choose your film",
                            fontFamily = Roboto,
                            fontWeight = FontWeight.Medium,
                            color = cinzento,
                            fontSize = 24.sp,
                            letterSpacing = 4.sp
                        )
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.DarkGray,
                        focusedTrailingIconColor = Color.Black,
                        unfocusedTrailingIconColor = Color.DarkGray,
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.LightGray
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        letterSpacing = 4.sp,
                        color = Color.Black
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .height(70.dp)
                        .width(340.dp)
                        .dropShadow()
                        .onFocusChanged { focusState ->
                            expanded = focusState.isFocused
                        },
                    shape = RoundedCornerShape(20.dp)
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(340.dp)
                        .dropShadow()
                        .background(Color.White)
                ) {
                    filteredFilms.forEach { filmItem ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = filmItem.name,
                                    fontFamily = Roboto,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 18.sp
                                )
                            },
                            onClick = {
                                onFilmChange(filmItem.name)
                                expanded = false
                            }
                        )
                    }
                }
            }

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
fun WelcomeFilmScreen(
    navController: NavController,
    viewModel: WelcomeViewModel = viewModel()
) {
    val film by viewModel.favFilm.collectAsState()
    val name by viewModel.name.collectAsState()
    WelcomeFilmScreenContent(
        userName = name,
        film = film,
        onFilmChange = { viewModel.updateFilm(it) },
        onNextClick = { navController.navigate("welcome_summary") }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeFilmScreenContentPreview() {
    WelcomeFilmScreenContent(
        userName = "Luísa",
        film = "Kodak Portra 400",
        onFilmChange = {},
        onNextClick = {}
    )
}
