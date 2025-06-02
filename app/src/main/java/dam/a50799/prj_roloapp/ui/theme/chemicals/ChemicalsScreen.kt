package dam.a50799.prj_roloapp.ui.theme.chemicals

import android.widget.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dam.a50799.prj_roloapp.data.local.dao.ChemicalDao
import dam.a50799.prj_roloapp.data.local.entities.Chemical
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.films.FilmViewModel

@Composable
fun ChemicalsScreenContent(
    chemicals: List<Chemical>,
    onChemicalClick: (Chemical) -> Unit,
    onBackClick: () -> Unit,
    navController: NavController?
) {
    Column {
        chemicals.forEach { chemical ->
            Button(
                onClick = { onChemicalClick(chemical) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(chemical.name)
            }
        }
        Button(
            onClick = onBackClick,
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Back")
        }
    }
}

@Composable
fun ChemicalDetailScreen(
    chemicalId: Int,
    navController: NavController,
    chemicalDao: ChemicalDao
) {
    val chemical = remember { mutableStateOf<Chemical?>(null) }

    LaunchedEffect(chemicalId) {
        chemical.value = chemicalDao.getChemicalsById(chemicalId)
    }

    chemical.value?.let { chemical ->
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Name: ${chemical.name}", fontFamily = Roboto, fontSize = 24.sp)
            Text(text = "Type: ${chemical.type}")
            Text(text = "Dilution: ${chemical.dilution}")
            Text(text = "Time (in minutes): ${chemical.timeInMinutes}")
            Text(text = "Temperature ${chemical.temperature}")
            Text(text = "Notes: ${chemical.notes}")
            Button(onClick = { navController.popBackStack() }) {
                Text("Back")
            }
        } ?: run {
            Text("Loading or chemical not found")
        }
    }
}

@Composable
fun ChemicalScreen(
    viewModel: ChemicalsViewModel,
    navController: NavController?
) {
    val chemicals by viewModel.chemicals.collectAsState()

    ChemicalsScreenContent(
        chemicals = chemicals,
        onChemicalClick = {chemical ->
            navController?.navigate("chemical_detail/${chemical.id}")
        },
        onBackClick = {
            navController?.popBackStack()
        },
        navController = navController
    )
}

@Preview(showBackground = true)
@Composable
fun ChemicalScreenPreview() {
    ChemicalsScreenContent(
        chemicals = listOf(
            Chemical(
                id = 1,
                name = "Kodak D-76",
                type = "Developer",
                dilution = "1+1",
                timeInMinutes = 9,
                temperature = 20,
                notes = "Standard developer for BW",
                imageUri = null
            ),
            Chemical(
                id = 2,
                name = "Kodak Fixer",
                type = "Fixer",
                dilution = "1+4",
                timeInMinutes = 5,
                temperature = 20,
                notes = "Standard fixer",
                imageUri = null
            )
        ),
        onChemicalClick = {},
        onBackClick = {},
        navController = null
    )
}