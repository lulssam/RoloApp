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
import dam.a50799.prj_roloapp.ui.theme.home.HomeScreen
import dam.a50799.prj_roloapp.ui.theme.register.RegisterScreen
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

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
                        }
                    }
                }
            }
        }
    }
}