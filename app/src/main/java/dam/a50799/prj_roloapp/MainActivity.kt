package dam.a50799.prj_roloapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dam.a50799.prj_roloapp.ui.theme.PRJ_RoloAppTheme
import dam.a50799.prj_roloapp.ui.theme.login.LoginScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import dam.a50799.prj_roloapp.ui.theme.login.LoginViewModel
import dam.a50799.prj_roloapp.data.auth.GoogleAuthUiClient
import dam.a50799.prj_roloapp.data.local.database.AppDatabase
import dam.a50799.prj_roloapp.ui.theme.chemicals.ChemicalDetailScreen
import dam.a50799.prj_roloapp.ui.theme.chemicals.ChemicalScreen
import dam.a50799.prj_roloapp.ui.theme.chemicals.ChemicalsViewModel
import dam.a50799.prj_roloapp.ui.theme.chemicals.ChemicalsViewModelFactory
import dam.a50799.prj_roloapp.ui.theme.films.FilmDetailScreen
import dam.a50799.prj_roloapp.ui.theme.films.FilmScreen
import dam.a50799.prj_roloapp.ui.theme.films.FilmViewModel
import dam.a50799.prj_roloapp.ui.theme.films.FilmViewModelFactory
import dam.a50799.prj_roloapp.ui.theme.home.HomeScreen
import dam.a50799.prj_roloapp.ui.theme.profile.ProfileScreen
import dam.a50799.prj_roloapp.ui.theme.register.RegisterScreen
import dam.a50799.prj_roloapp.ui.theme.settings.SettingsScreen
import dam.a50799.prj_roloapp.ui.theme.settings.about.AboutScreen
import dam.a50799.prj_roloapp.ui.theme.settings.account.AccountScreen
import dam.a50799.prj_roloapp.ui.theme.settings.help.HelpScreen
import dam.a50799.prj_roloapp.ui.theme.welcome.WelcomeFilmScreen
import dam.a50799.prj_roloapp.ui.theme.welcome.WelcomeNameScreen
import dam.a50799.prj_roloapp.ui.theme.welcome.WelcomeNameScreenContent
import dam.a50799.prj_roloapp.ui.theme.welcome.WelcomeScreenAge
import dam.a50799.prj_roloapp.ui.theme.welcome.WelcomeSummaryScreen
import dam.a50799.prj_roloapp.ui.theme.welcome.WelcomeViewModel
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    // criar base de dados e viewmodelfactory
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val filmViewModel: FilmViewModel by viewModels {
        FilmViewModelFactory(database.filmDao())
    }

    private val chemicalsViewModel: ChemicalsViewModel by viewModels {
        ChemicalsViewModelFactory(database.chemicalDao())
    }
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PRJ_RoloAppTheme {
                Scaffold { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()
                        val welcomeViewModel: WelcomeViewModel = viewModel()

                        NavHost(navController = navController, startDestination = "splash") {

                            composable("splash") {
                                LaunchedEffect(key1 = Unit) {
                                    googleAuthUiClient.signOut()
                                    val user = googleAuthUiClient.getSignedInUser()
                                    Log.d("USER ", user.toString())

                                    if (user != null) {
                                        navController.navigate("homescreen") {
                                            popUpTo("splash") { inclusive = true }
                                        }
                                    } else {
                                        navController.navigate("sign_in") {
                                            popUpTo("splash") { inclusive = true }
                                        }
                                    }
                                }
                            }

                            composable("sign_in") {

                                val viewModel = viewModel<LoginViewModel>()
                                val state by viewModel.state.collectAsStateWithLifecycle()
                                val isLoggedIn by viewModel.isLoggedIn.collectAsStateWithLifecycle()

                                LaunchedEffect(key1 = isLoggedIn) {
                                    if (isLoggedIn) {
                                        Toast.makeText(
                                            applicationContext,
                                            "Login successful",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        navController.navigate("homescreen") {
                                            popUpTo("sign_in") { inclusive = true }
                                        }
                                    }
                                }

                                val launcher = rememberLauncherForActivityResult(
                                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                                    onResult = { result ->
                                        if (result.resultCode == RESULT_OK) {
                                            lifecycleScope.launch {
                                                val signInResult =
                                                    googleAuthUiClient.signInWithIntent(
                                                        intent = result.data ?: return@launch
                                                    )
                                                viewModel.onSignInResult(signInResult)
                                            }
                                        }
                                    })

                                LaunchedEffect(key1 = state.isSignedSuccessfull) {
                                    if (state.isSignedSuccessfull) {
                                        Toast.makeText(
                                            applicationContext,
                                            "Sign in successful",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }

                                LoginScreen(
                                    viewModel = viewModel(), onGoogleClick = {
                                        lifecycleScope.launch {
                                            val signInIntentSender = googleAuthUiClient.signIn()
                                            launcher.launch(
                                                IntentSenderRequest.Builder(
                                                    signInIntentSender ?: return@launch
                                                ).build()
                                            )
                                        }
                                    }, navController = navController
                                )

                            }
                            composable("register") {
                                RegisterScreen(navController = navController)
                            }
                            composable("homescreen") {
                                HomeScreen(navController = navController)
                            }

                            composable("settings") {
                                SettingsScreen(navController = navController)
                            }

                            composable("profile"){
                                ProfileScreen(navController = navController)
                            }

                            composable("films") {
                                FilmScreen(
                                    viewModel = filmViewModel,
                                    navController = navController)
                            }

                            composable("film_detail/{filmId}") { backStackEntry ->
                                val filmId =
                                    backStackEntry.arguments?.getString("filmId")?.toIntOrNull()
                                if (filmId != null) {
                                    FilmDetailScreen(
                                        filmId = filmId,
                                        navController = navController,
                                        filmDao = database.filmDao()
                                    )
                                } else {
                                    navController.popBackStack()
                                }
                            }

                            composable("chemicals"){
                                ChemicalScreen(
                                    viewModel = chemicalsViewModel,
                                    navController = navController
                                )
                            }

                            composable("chemical_detail/{chemicalId}"){backStackEntry ->
                                val chemicalId =
                                    backStackEntry.arguments?.getString("chemicalId")?.toIntOrNull()
                                if (chemicalId != null){
                                    ChemicalDetailScreen(
                                        chemicalId = chemicalId,
                                        navController = navController,
                                        chemicalDao = database.chemicalDao()
                                    )
                                } else {
                                    navController.popBackStack()
                                }
                            }

                            composable("welcome_name"){
                                WelcomeNameScreen(navController, welcomeViewModel)
                            }
                            composable("welcome_age"){
                                WelcomeScreenAge(navController, welcomeViewModel)
                            }
                            composable("welcome_favFilm"){
                                WelcomeFilmScreen(navController, welcomeViewModel)
                            }
                            composable("welcome_summary"){
                                  WelcomeSummaryScreen(navController, welcomeViewModel)
                            }
                            composable("account_page"){
                                AccountScreen((navController))
                            }
                            composable("help_page"){
                                HelpScreen(navController)
                            }

                            composable("language_page"){
                                /*TODO*/
                            }

                            composable("about_page"){
                                AboutScreen(navController)
                            }

                        }
                    }
                }
            }
        }
    }
}