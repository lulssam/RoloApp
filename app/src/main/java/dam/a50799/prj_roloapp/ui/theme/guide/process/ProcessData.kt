package dam.a50799.prj_roloapp.ui.theme.guide.process

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dam.a50799.prj_roloapp.R
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.amareloTorrado
import dam.a50799.prj_roloapp.ui.theme.cinzento
import dam.a50799.prj_roloapp.utils.dropShadow
import dam.a50799.prj_roloapp.utils.loadChemicalsJson
import dam.a50799.prj_roloapp.utils.loadFilmsJson


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProcessDataContent(
    film: String,
    developer: String,
    fixer: String,
    onFilmChange: (String) -> Unit,
    onDeveloperChange: (String) -> Unit,
    onFixerChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onProcessClick: () -> Unit,
) {

    val context = LocalContext.current
    val allFilms = remember { loadFilmsJson(context) }
    val allChemicals = remember { loadChemicalsJson(context) }

    var filmExpanded by remember { mutableStateOf(false) }
    var developerExpanded by remember { mutableStateOf(false) }
    var fixerExpanded by remember { mutableStateOf(false) }

    // filtar filmes
    val filteredFilms = remember(film, allFilms) {
        if (film.isEmpty()) allFilms
        else allFilms.filter { it.name.contains(film, ignoreCase = true) }
    }

    // filtrar developers
    val filteredDevelopers = remember(developer, allChemicals) {
        if (developer.isEmpty()) {
            allChemicals.filter { it.type == "DEVELOPER" }
        } else {
            allChemicals.filter {
                it.type == "DEVELOPER" && it.name.contains(developer, ignoreCase = true)
            }
        }
    }

    // val filtrar fixers
    val filteredFixers = remember(fixer, allChemicals) {
        if (fixer.isEmpty()) {
            allChemicals.filter { it.type == "FIXER" }
        } else {
            allChemicals.filter {
                it.type == "FIXER" && it.name.contains(fixer, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back), // Certifique-se de ter este drawable
                    contentDescription = "Voltar",
                    tint = Color.Black
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Choose Process",
                    fontSize = 24.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            IconButton(
                onClick = onSettingsClick,
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings), // Certifique-se de ter este drawable
                    contentDescription = "Configurações",
                    tint = Color.Black
                )
            }
        }

        // COLUNA PRINCIPAL
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // FILM DROPDOWN
            Text(
                "Choose film stock",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Roboto
            )
            Spacer(modifier = Modifier.height(20.dp))

            ExposedDropdownMenuBox(
                expanded = filmExpanded,
                onExpandedChange = { filmExpanded = it }
            ) {
                OutlinedTextField(
                    value = film,
                    onValueChange = {
                        onFilmChange(it)
                        filmExpanded = true
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
                        ExposedDropdownMenuDefaults.TrailingIcon(filmExpanded)
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
                        .dropShadow()
                        .onFocusChanged { focusState ->
                            filmExpanded = focusState.isFocused
                        },
                    shape = RoundedCornerShape(20.dp)
                )

                ExposedDropdownMenu(
                    expanded = filmExpanded,
                    onDismissRequest = { filmExpanded = false },
                    modifier = Modifier
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
                                filmExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // DEVELOPER DROPDOWN
            Text(
                "Choose developer",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Roboto
            )
            Spacer(modifier = Modifier.height(20.dp))

            ExposedDropdownMenuBox(
                expanded = developerExpanded,
                onExpandedChange = { developerExpanded = it }
            ) {
                OutlinedTextField(
                    value = developer,
                    onValueChange = {
                        onDeveloperChange(it)
                        developerExpanded = true
                    },
                    placeholder = {
                        Text(
                            text = "Choose your developer",
                            fontFamily = Roboto,
                            fontWeight = FontWeight.Medium,
                            color = cinzento,
                            fontSize = 20.sp,
                            letterSpacing = 4.sp
                        )
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(developerExpanded)
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
                        .dropShadow()
                        .onFocusChanged { focusState ->
                            developerExpanded = focusState.isFocused
                        },
                    shape = RoundedCornerShape(20.dp)
                )

                ExposedDropdownMenu(
                    expanded = developerExpanded,
                    onDismissRequest = { developerExpanded = false },
                    modifier = Modifier
                        .dropShadow()
                        .background(Color.White)
                ) {
                    filteredDevelopers.forEach { chemical ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = chemical.name,
                                    fontFamily = Roboto,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 18.sp
                                )
                            },
                            onClick = {
                                onDeveloperChange(chemical.name)
                                developerExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // FIXER DROPDOWN
            Text(
                "Choose fixer",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Roboto
            )
            Spacer(modifier = Modifier.height(20.dp))

            ExposedDropdownMenuBox(
                expanded = fixerExpanded,
                onExpandedChange = { fixerExpanded = it }
            ) {
                OutlinedTextField(
                    value = fixer,
                    onValueChange = {
                        onFixerChange(it)
                        fixerExpanded = true
                    },
                    placeholder = {
                        Text(
                            text = "Choose your fixer",
                            fontFamily = Roboto,
                            fontWeight = FontWeight.Medium,
                            color = cinzento,
                            fontSize = 24.sp,
                            letterSpacing = 4.sp
                        )
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(fixerExpanded)
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
                        .dropShadow()
                        .onFocusChanged { focusState ->
                            fixerExpanded = focusState.isFocused
                        },
                    shape = RoundedCornerShape(20.dp)
                )

                ExposedDropdownMenu(
                    expanded = fixerExpanded,
                    onDismissRequest = { fixerExpanded = false },
                    modifier = Modifier
                        .dropShadow()
                        .background(Color.White)
                ) {
                    filteredFixers.forEach { chemical ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = chemical.name,
                                    fontFamily = Roboto,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 18.sp
                                )
                            },
                            onClick = {
                                onFixerChange(chemical.name)
                                fixerExpanded = false
                            }
                        )
                    }
                }
            }
        }

        // BOTÃO NEXT NO FUNDO
        Button(
            onClick = onProcessClick,
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(amareloTorrado, Color.Black),
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier
                .padding(bottom = 40.dp)
                .fillMaxWidth()
                .height(60.dp)
                .dropShadow()
        ) {
            Text(
                text = "Process",
                fontSize = 20.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ProcessDataScreen(
    viewModel: ProcessDataViewModel = viewModel(),
    navController: NavController
){

    val context = LocalContext.current
    val chemicals = remember { loadChemicalsJson(context) }

    // passar para o viewmodel só uma vez
    LaunchedEffect(Unit) {
        viewModel.setChemicals(chemicals)
    }
    ProcessDataContent(
        film = viewModel.film,
        developer = viewModel.developer,
        fixer = viewModel.fixer,
        onFilmChange = { viewModel.onFilmChange(it) },
        onDeveloperChange = { viewModel.onDeveloperChange(it) },
        onFixerChange = { viewModel.onFixerChange(it) },
        onBackClick = { navController.popBackStack() },
        onSettingsClick = { navController.navigate("settings") },
        onProcessClick = {
            navController.navigate("develop")
        }
    )
    
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProcessDataScreenPreview() {
    ProcessDataContent(
        film = "Ilford HP5 Plus 400",
        developer = "Kodak D-76",
        fixer = "Ilford Rapid Fixer",
        onFilmChange = {},
        onDeveloperChange = {},
        onFixerChange = {},
        onProcessClick = {},
        onBackClick = {},
        onSettingsClick = {}
    )
}