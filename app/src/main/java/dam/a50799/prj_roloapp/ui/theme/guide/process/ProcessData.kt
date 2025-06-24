package dam.a50799.prj_roloapp.ui.theme.guide.process

import androidx.compose.foundation.excludeFromSystemGesture
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dam.a50799.prj_roloapp.data.local.entities.Chemical
import dam.a50799.prj_roloapp.data.local.entities.Film
import dam.a50799.prj_roloapp.ui.theme.guide.develop.DevelopViewModel
import dam.a50799.prj_roloapp.utils.loadChemicalsJson
import dam.a50799.prj_roloapp.utils.loadFilmsJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProcessDataScreenContent(
    viewModel: ProcessDataViewModel,
    onProcessClick: (film: Film, developer: Chemical, fixer: Chemical) -> Unit
) {
    val films by remember { derivedStateOf { viewModel.films } }
    val developers by remember { derivedStateOf { viewModel.developers } }
    val fixers by remember { derivedStateOf { viewModel.fixers } }

    val selectedFilm by viewModel.selectedFilm
    val selectedDeveloper by viewModel.selectedDeveloper
    val selectedFixer by viewModel.selectedFixer

    // Estados para controlar a expansão dos menus
    var expandedFilm by remember { mutableStateOf(false) }
    var expandedDeveloper by remember { mutableStateOf(false) }
    var expandedFixer by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Film Dropdown
        ExposedDropdownMenuBox(
            expanded = expandedFilm,
            onExpandedChange = { expandedFilm = it }
        ) {
            TextField(
                value = selectedFilm?.name ?: "",
                onValueChange = {},
                readOnly = true,
                label = { Text("Select Film") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedFilm)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expandedFilm,
                onDismissRequest = { expandedFilm = false },
                modifier = Modifier.excludeFromSystemGesture() // Melhora o comportamento em dispositivos com gestos
            ) {
                films.forEach { film ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = film.name,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        onClick = {
                            viewModel.selectFilm(film)
                            expandedFilm = false
                        }
                    )
                }
            }
        }

        // Developer Dropdown
        ExposedDropdownMenuBox(
            expanded = expandedDeveloper,
            onExpandedChange = { expandedDeveloper = it }
        ) {
            TextField(
                value = selectedDeveloper?.name ?: "",
                onValueChange = {},
                readOnly = true,
                label = { Text("Select Developer") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedDeveloper)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expandedDeveloper,
                onDismissRequest = { expandedDeveloper = false },
                modifier = Modifier.excludeFromSystemGesture()
            ) {
                developers.forEach { developer ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = developer.name,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        onClick = {
                            viewModel.selectDeveloper(developer)
                            expandedDeveloper = false
                        }
                    )
                }
            }
        }

        // Fixer Dropdown
        ExposedDropdownMenuBox(
            expanded = expandedFixer,
            onExpandedChange = { expandedFixer = it }
        ) {
            TextField(
                value = selectedFixer?.name ?: "",
                onValueChange = {},
                readOnly = true,
                label = { Text("Select Fixer") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedFixer)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expandedFixer,
                onDismissRequest = { expandedFixer = false },
                modifier = Modifier.excludeFromSystemGesture()
            ) {
                fixers.forEach { fixer ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = fixer.name,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        onClick = {
                            viewModel.selectFixer(fixer)
                            expandedFixer = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (selectedFilm != null && selectedDeveloper != null && selectedFixer != null) {
                    onProcessClick(
                        selectedFilm!!,
                        selectedDeveloper!!,
                        selectedFixer!!
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(16.dp),
            enabled = selectedFilm != null &&
                    selectedDeveloper != null &&
                    selectedFixer != null
        ) {
            Text(
                "Process",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ProcessDataScreen(
    onProcessClick: (film: Film, developer:Chemical, fixer: Chemical) -> Unit,
    navController: NavController
) {
    val context = LocalContext.current
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)

    var films by remember { mutableStateOf<List<Film>>(emptyList()) }
    var chemicals by remember { mutableStateOf<List<Chemical>>(emptyList()) }

    val developViewModel: DevelopViewModel = viewModel(viewModelStoreOwner)

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            films = loadFilmsJson(context)
            chemicals = loadChemicalsJson(context)
        }
    }

    // Mostrar loading enquanto carrega
    if (films.isEmpty() || chemicals.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        val viewModel: ProcessDataViewModel = viewModel(
            viewModelStoreOwner = viewModelStoreOwner,
            factory = ProcessDataViewModelFactory(films, chemicals)
        )
        ProcessDataScreenContent(
            viewModel = viewModel,
            onProcessClick = { film, developer, fixer ->
                navController.navigate("develop")

                // chamar gemini
                developViewModel.fetchInstructions(
                    film = film.name,
                    developer = developer.name,
                    fixer = fixer.name,
                    temperature = "20", // valor padrão
                    iso = film.iso.toString(),
                )
            }
        )
    }
}