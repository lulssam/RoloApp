package dam.a50799.prj_roloapp.ui.theme.register

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import dam.a50799.prj_roloapp.ui.theme.amareloTorrado
import dam.a50799.prj_roloapp.R
import dam.a50799.prj_roloapp.utils.customTextFieldColors
import dam.a50799.prj_roloapp.utils.dropShadow

@Composable
fun RegisterScreenContent(
    email: String,
    password: String,
    verifyPassword: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onVerifyPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    // region Coluna principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        // region Texto Rolo
        Text(
            "Rolo",
            fontSize = 128.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.Black,
            letterSpacing = 17.sp

        ) // endregion

        // region Texto App
        Text(
            "App",
            fontSize = 128.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.Black,
            letterSpacing = 17.sp

        ) // endregion

        Spacer(modifier = Modifier.height(48.dp))

        // region Input Email
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
                .height(71.dp)
                .dropShadow(),
            shape = RoundedCornerShape(20.dp),
            colors = customTextFieldColors()
        ) // endregion

        Spacer(modifier = Modifier.height(18.dp))

        // region Input Password
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
                .height(71.dp)
                .dropShadow(),
            shape = RoundedCornerShape(20.dp),
            visualTransformation = PasswordVisualTransformation(),
            colors = customTextFieldColors()
        ) // endregion

        Spacer(modifier = Modifier.height(18.dp))

        //region Input Verify Password
        OutlinedTextField(
            value = verifyPassword,
            onValueChange = onVerifyPasswordChange,
            placeholder = {
                Text(
                    "Verify Password",
                    fontSize = 24.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 7.sp
                )
            },
            singleLine = true,
            modifier = Modifier
                .width(352.dp)
                .height(71.dp)
                .dropShadow(),
            shape = RoundedCornerShape(20.dp),
            visualTransformation = PasswordVisualTransformation(),
            colors = customTextFieldColors()
        ) // endregion

        Spacer(modifier = Modifier.height(18.dp))

        // region Botão Registar
        Button(
            modifier = Modifier
                .width(352.dp)
                .height(68.dp)
                .dropShadow(),
            onClick = onRegisterClick,
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = amareloTorrado,
                contentColor = Color.Black
            ),
            border = BorderStroke(2.dp, Color.Black)

        ) {
            Text(
                "Register",
                fontSize = 26.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.sp
            )

        } // endregion
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Voltar a Login",
            fontSize = 15.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.Light,
            modifier = Modifier.clickable { onBackClick() }
        )
    } // endregion
}

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = viewModel()
) {
    val context = LocalContext.current

    RegisterScreenContent(
        email = viewModel.email.value,
        password = viewModel.password.value,
        verifyPassword = viewModel.verifyPassword.value,
        onEmailChange = { viewModel.email.value = it },
        onPasswordChange = { viewModel.password.value = it },
        onVerifyPasswordChange = { viewModel.verifyPassword.value = it },
        onRegisterClick = {
            viewModel.registerUser(
                onSuccess = {
                    Toast.makeText(context, "Registration successful!", Toast.LENGTH_LONG).show()
                    navController.navigate("welcome_name") // ir diretamente para welcomescreen
                },
                onFailure = {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                }
            )
        },
        onBackClick = { navController.popBackStack() }
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreenContent(
        email = "teste@exemplo.com",
        password = "1234",
        verifyPassword = "1234",
        onEmailChange = {},
        onPasswordChange = {},
        onVerifyPasswordChange = {},
        onRegisterClick = {},
        onBackClick = {}
    )
}