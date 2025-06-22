package dam.a50799.prj_roloapp.ui.theme.films


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import dam.a50799.prj_roloapp.data.local.dao.FilmDao
import dam.a50799.prj_roloapp.data.local.entities.Film
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.utils.dropShadow
import dam.a50799.prj_roloapp.R
import dam.a50799.prj_roloapp.ui.theme.cinzento

val DEFAULT_PLACEHOLDER = R.drawable.ic_launcher_foreground

@Composable
fun FilmScreenContent(
    films: List<Film>,
    onFilmClick: (Film) -> Unit,
    onBackClick: () -> Unit,
    navController: NavController?
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
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
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Films",
                    fontFamily = Roboto,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(45.dp),
            horizontalArrangement = Arrangement.spacedBy(45.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(films) { film ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.75f)
                        .dropShadow(),
                    onClick = { onFilmClick(film) },
                    colors = cardColors(containerColor = Color.White),
                    border = BorderStroke(2.dp, cinzento),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column {
                        AsyncImage(
                            model = film.imageUri ?: DEFAULT_PLACEHOLDER,
                            contentDescription = film.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun FilmDetailScreen(
    filmId: Int,
    navController: NavController,
    filmDao: FilmDao
) {
    val film = remember { mutableStateOf<Film?>(null) }

    // estado para a imagem atualmente selecionada ou null
    val selectedImage = remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(filmId) {
        film.value = filmDao.getFilmById(filmId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
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

            film.value?.let { film ->
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = film.name,
                        fontFamily = Roboto,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Black,
                        lineHeight = 32.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "ISO: ${film.iso}",
                        fontFamily = Roboto,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "FORMAT: ${film.format}",
                        fontFamily = Roboto,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light
                    )

                    Spacer(modifier = Modifier.height(10.dp))


                    Text(
                        text = "TYPE: ${film.type}",
                        fontFamily = Roboto,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light
                    )

                    Spacer(modifier = Modifier.height(15.dp))


                    Text(
                        text = "Description:",
                        fontFamily = Roboto,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = film.description,
                        fontFamily = Roboto,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 20.sp

                    )

                    if (film.exampleImages.isNotEmpty()) {
                        Spacer(Modifier.height(25.dp))
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            film.exampleImages.forEach { imgRes ->
                                AsyncImage(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(4f / 3f)
                                        .clickable { selectedImage.value = imgRes },

                                    model = imgRes,
                                    contentDescription = "Exemplo do filme ${film.name}"
                                )
                            }
                        }
                    }
                } ?: run {
                    // mostra loading ou mensagem de erro
                    Text("Carregando ou filme não encontrado...")
                }

            }

            // dialog para mostrar imagem em fullscreen quando clicada
            selectedImage.value?.let { imgRes ->
                Dialog(onDismissRequest = { selectedImage.value != null }) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.8f))
                            .clickable(
                                // clique fora da imagem fecha fullscreen
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                selectedImage.value = null
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .aspectRatio(1f)
                                .clickable(
                                    // evitar que clicar na imagem feche o diálogo
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() },
                                    onClick = {/*Nada aqui para não fechar*/ }
                                ),
                            model = imgRes,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FilmScreen(
    viewModel: FilmViewModel,
    navController: NavController?
) {
    val films by viewModel.films.collectAsState()

    FilmScreenContent(
        films = films, onFilmClick = { film ->
            navController?.navigate("film_detail/${film.id}")
        }, onBackClick = {
            navController?.popBackStack()
        }, navController = navController
    )
}

