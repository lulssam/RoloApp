package dam.a50799.prj_roloapp.ui.theme.login


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dam.a50799.prj_roloapp.ui.theme.Roboto
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import dam.a50799.prj_roloapp.ui.theme.amareloTorrado
import dam.a50799.prj_roloapp.R
import dam.a50799.prj_roloapp.data.auth.SignInState


@Composable
fun LoginScreenContent(
    email: String,
    password: String,
    state: SignInState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onFacebookClick: () -> Unit,
    navController: NavController
) {

    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            "Rolo",
            fontSize = 128.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.Black,
            letterSpacing = 17.sp

        )
        Text(
            "App",
            fontSize = 128.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.Black,
            letterSpacing = 17.sp

        )
        Spacer(modifier = Modifier.height(48.dp))

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            placeholder = {
                Text(
                    "Email",
                    fontSize = 24.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 7.sp
                )
            },
            singleLine = true,
            modifier = Modifier
                .width(352.dp)
                .height(71.dp),
            shape = RoundedCornerShape(20.dp),
            // TODO colors
        )

        Spacer(modifier = Modifier.height(18.dp))

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = {
                Text(
                    "Password",
                    fontSize = 24.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 7.sp
                )
            },
            singleLine = true,
            modifier = Modifier
                .width(352.dp)
                .height(71.dp),
            shape = RoundedCornerShape(20.dp),
            visualTransformation = PasswordVisualTransformation()
            // TODO colors
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier
                .width(352.dp)
                .height(68.dp),
            onClick = onLoginClick,
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = amareloTorrado,
                contentColor = Color.Black
            ),
            border = BorderStroke(2.dp, Color.Black)

        ) {
            Text(
                "Login",
                fontSize = 26.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            "or",
            fontSize = 20.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.Medium,
            letterSpacing = 1.sp

        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onGoogleClick) {
                Icon(
                    painter = painterResource(id = R.drawable.googleicon),
                    contentDescription = "Login com google",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Unspecified
                )
            }
            IconButton(onClick = onFacebookClick) {
                Icon(
                    painter = painterResource(id = R.drawable.facebookicon),
                    contentDescription = "Login com facebook",
                    modifier = Modifier.size(48.dp),
                    tint = Color.Unspecified
                )
            }

            IconButton(onClick = {navController.navigate("register")}) {
                Icon(
                    painter = painterResource(id = R.drawable.emailicon),
                    contentDescription = "Login com email e password",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Unspecified
                )
            }
        }
    }

}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onGoogleClick: () -> Unit,
    navController: NavController
) {
    val email by viewModel.email
    val password by viewModel.password
    val state by viewModel.state.collectAsState()

    LoginScreenContent(
        email = email,
        password = password,
        state = state,
        onEmailChange = { viewModel.email.value = it },
        onPasswordChange = { viewModel.password.value = it },
        onLoginClick = { viewModel.login() },
        onGoogleClick = onGoogleClick,
        onFacebookClick = {/*TODO: chamar loginFacebook() */ },
        navController = navController

    )
}
/*
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreenContent(
        email = "teste@exemplo.com",
        password = "1234",
        isLoggedIn = false,
        state = SignInState(),
        onEmailChange = {},
        onPasswordChange = {},
        onLoginClick = {},
        onLoginSuccess = {},
        onGoogleClick = {},
        onFacebookClick = {},

    )
}*/