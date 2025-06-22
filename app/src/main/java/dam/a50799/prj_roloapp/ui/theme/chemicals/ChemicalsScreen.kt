package dam.a50799.prj_roloapp.ui.theme.chemicals

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import coil.compose.AsyncImage
import dam.a50799.prj_roloapp.R
import dam.a50799.prj_roloapp.data.local.dao.ChemicalDao
import dam.a50799.prj_roloapp.data.local.entities.Chemical
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.cinzento
import dam.a50799.prj_roloapp.ui.theme.films.DEFAULT_PLACEHOLDER
import dam.a50799.prj_roloapp.utils.dropShadow

@Composable
fun ChemicalsScreenContent(
    chemicals: List<Chemical>,
    onChemicalClick: (Chemical) -> Unit,
    onBackClick: () -> Unit,
    navController: NavController?
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                IconButton(
                    onClick = { navController?.popBackStack() }, modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "Voltar",
                        tint = Color.Black
                    )
                }

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Chemicals",
                        fontFamily = Roboto,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Developers",
                        fontFamily = Roboto,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        verticalArrangement = Arrangement.spacedBy(45.dp),
                        contentPadding = PaddingValues(bottom = 100.dp)
                    ) {
                        items(chemicals.filter { it.type.lowercase() == "developer" }) { chemical ->
                            ChemicalCard(chemical, onChemicalClick)
                        }
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Fixers",
                        fontFamily = Roboto,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        verticalArrangement = Arrangement.spacedBy(45.dp),
                        contentPadding = PaddingValues(bottom = 100.dp)
                    ) {
                        items(chemicals.filter { it.type.lowercase() == "fixer" }) { chemical ->
                            ChemicalCard(chemical, onChemicalClick)
                        }
                    }
                }
            }
        }

        // Footer fixo no fundo da screen
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .padding(bottom = 100.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "To simplify the process, we will use water as Stop Bath :)",
                fontFamily = Roboto,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center

            )
        }
    }
}

@Composable
fun ChemicalCard(chemical: Chemical, onChemicalClick: (Chemical) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.75f)
            .dropShadow(),
        onClick = { onChemicalClick(chemical) },
        colors = cardColors(containerColor = Color.White),
        border = BorderStroke(2.dp, cinzento),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column {
            AsyncImage(
                model = chemical.imageUri?.toInt() ?: DEFAULT_PLACEHOLDER,
                contentDescription = chemical.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
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

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // botão voltar
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Voltar",
                    tint = Color.Black
                )
            }
            chemical.value?.let { chemical ->
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = chemical.name,
                        fontFamily = Roboto,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Black,
                        lineHeight = 32.sp,
                        textAlign = TextAlign.Center)

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "TYPE: ${chemical.type}",
                        fontFamily = Roboto,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "DILUTION: ${chemical.dilution}",
                        fontFamily = Roboto,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light)

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "TIME: ${chemical.timeInMinutes} MINUTES",
                        fontFamily = Roboto,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light)

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "TEMPERATURE ${chemical.temperature} ºC",
                        fontFamily = Roboto,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Description:",
                        fontFamily = Roboto,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light)

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = chemical.description,
                        fontFamily = Roboto,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 20.sp)
                } ?: run {
                    Text("Loading or chemical not found")
                }
            }
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
        onChemicalClick = { chemical ->
            navController?.navigate("chemical_detail/${chemical.id}")
        },
        onBackClick = {
            navController?.popBackStack()
        },
        navController = navController
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChemicalsScreenContentPreview() {
    // Exemplo de lista de chemicals
    val sampleChemicals = listOf(
        Chemical(
            id = 1,
            name = "Ilford ID-11",
            type = "Developer",
            dilution = "1+1",
            timeInMinutes = "9",
            temperature = 20,
            description = "Um developer clássico para preto e branco.",
            imageUri = null
        ),
        Chemical(
            id = 2,
            name = "Kodak D-76",
            type = "Developer",
            dilution = "1+1",
            timeInMinutes = "10",
            temperature = 20,
            description = "Outro developer muito usado em filme p&b.",
            imageUri = null

        ),
        Chemical(
            id = 3,
            name = "Ilford Rapid Fixer",
            type = "Fixer",
            dilution = "1+4",
            timeInMinutes = "5",
            temperature = 20,
            description = "Fixador rápido para processos de revelação.",
            imageUri = null
        ),
        Chemical(
            id = 4,
            name = "Kodak Fixer",
            type = "Fixer",
            dilution = "1+3",
            timeInMinutes = "6",
            temperature = 20,
            description = "Fixador clássico para preto e branco.",
            imageUri = null
        )
    )

    ChemicalsScreenContent(
        chemicals = sampleChemicals,
        onChemicalClick = {},
        onBackClick = {},
        navController = null
    )
}
