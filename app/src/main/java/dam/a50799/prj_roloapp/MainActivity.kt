package dam.a50799.prj_roloapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dam.a50799.prj_roloapp.ui.theme.PRJ_RoloAppTheme
import dam.a50799.prj_roloapp.ui.theme.login.LoginScreen
import dam.a50799.prj_roloapp.ui.theme.login.LoginScreenContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PRJ_RoloAppTheme {
                var isLoggedIn by remember { mutableStateOf(false) }

                if (isLoggedIn){
                    // TODO
                } else {
                    LoginScreen(
                        onLoginSuccess = {isLoggedIn = true}
                    )
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PRJ_RoloAppTheme {
        Greeting("Android")
    }
}