package dam.a50799.prj_roloapp.ui.theme.films

import android.R.attr.onClick
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dam.a50799.prj_roloapp.data.local.dao.FilmDao
import dam.a50799.prj_roloapp.data.local.database.AppDatabase
import dam.a50799.prj_roloapp.data.local.entities.Film
import dam.a50799.prj_roloapp.ui.theme.Roboto

@Composable
fun FilmScreenContent(
    films: List<Film>,
    onFilmClick: (Film) -> Unit,
    onBackClick: () -> Unit,
    navController: NavController?
) {
    Column {
        films.forEach { film ->
            Button(
                onClick = { onFilmClick(film) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(film.name)
            }
        }

        Button(
            onClick = onBackClick,
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Voltar")
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

    LaunchedEffect(filmId) {
        film.value = filmDao.getFilmById(filmId)
    }

    film.value?.let { film ->
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Nome: ${film.name}", fontFamily = Roboto, fontSize = 24.sp)
            Text(text = "ISO: ${film.iso}")
            Text(text = "Formato: ${film.format}")
            Text(text = "Tipo: ${film.type}")
            Text(text = "Descrição: ${film.description}")
            Button(onClick = { navController.popBackStack() }) {
                Text("Voltar")
            }
        }
    } ?: run {
        // mostra loading ou mensagem de erro
        Text("Carregando ou filme não encontrado...")
    }

}

@Composable
fun FilmScreen(
    viewModel: FilmViewModel,
    navController: NavController?
) {

    //val context = LocalContext.current
    //val filmDao = remember { AppDatabase.getDatabase(context).filmDao() }
    /*val viewModel: FilmViewModel = viewModel(
        factory = FilmViewModelFactory(filmDao = filmDao)
    )*/

    val films by viewModel.films.collectAsState()

    FilmScreenContent(
        films = films,
        onFilmClick = { film ->
            navController?.navigate("film_detail/${film.id}")
        },
        onBackClick = {
            navController?.popBackStack()
        },
        navController = navController
    )


}

@Preview(showBackground = true)
@Composable
fun FilmScreenPreview() {
    FilmScreenContent(
        films = listOf(
            Film(
                id = 1, name = "Kodak Gold",
                iso = 200,
                format = "35mm",
                type = "Cor",
                description = "lorem",
                imageUri = null
            ),
            Film(
                id = 2, name = "Ilford HP5",
                iso = 400,
                format = "35mm",
                type = "Preto e Branco",
                description = "lorem",
                imageUri = null
            )
        ),
        onFilmClick = {},
        onBackClick = {},
        navController = null
    )
}
