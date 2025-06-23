package dam.a50799.prj_roloapp.ui.theme.settings.account

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.amareloTorrado
import dam.a50799.prj_roloapp.ui.theme.laranja
import dam.a50799.prj_roloapp.utils.dropShadow
import dam.a50799.prj_roloapp.utils.customTextFieldColors

@Composable
fun AccountScreenContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onUpdateEmailClick: () -> Unit,
    onUpdatePasswordClick: () -> Unit,
    onSignOutClick: () -> Unit,
    onBackClick: () -> Unit,
    navController: NavController
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Account",
            fontFamily = Roboto,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 10.dp)
        )

        Text(
            text = "Account Settings",
            fontSize = 42.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.Black
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            placeholder = {
                Text("New Email",
                    fontSize = 24.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 4.sp)
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .dropShadow(),
            shape = RoundedCornerShape(20.dp),
            colors = customTextFieldColors()
        )

        Button(
            onClick = onUpdateEmailClick,
            colors = ButtonDefaults.buttonColors(amareloTorrado, Color.Black),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier
                .width(310.dp)
                .height(60.dp)
                .dropShadow()
        ) {
            Text(
                text = "Change Email",
                fontSize = 20.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = {
                Text("New Password",
                    fontSize = 24.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 4.sp)
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .dropShadow(),
            shape = RoundedCornerShape(20.dp),
            colors = customTextFieldColors()
        )
        Button(
            onClick = onUpdatePasswordClick,
            colors = ButtonDefaults.buttonColors(amareloTorrado, Color.Black),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier
                .height(60.dp)
                .width(310.dp)
                .dropShadow()
        ) {
            Text(
                text = "Change Password",
                fontSize = 20.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Sign Out",
            fontSize = 24.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.clickable{
                onSignOutClick()
            }
        )

        Spacer(modifier = Modifier.height(32.dp))



        Button(
            onClick = { navController.popBackStack() },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(laranja, Color.Black),
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .dropShadow()
        ) {
            Text(
                text = "Back",
                fontSize = 20.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium
            )
        }


    }
}

@Composable
fun AccountScreen(
    navController: NavController,
    viewModel: AccountViewModel = viewModel()
) {
    val context = LocalContext.current
    val email by viewModel.newEmail.collectAsState()
    val password by viewModel.newPassword.collectAsState()
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    AccountScreenContent(
        email = email,
        password = password,
        onEmailChange = { viewModel.updateEmail(it) },
        onPasswordChange = { viewModel.updatePassword(it) },
        onUpdateEmailClick = {
            if (email.isNotEmpty()) {
                user?.updateEmail(email)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful)
                            Toast.makeText(context, "Email updated", Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(context, "Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        },
        onUpdatePasswordClick = {
            if (password.isNotEmpty()) {
                user?.updatePassword(password)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful)
                            Toast.makeText(context, "Password updated", Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(context, "Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        },
        onSignOutClick = {
            auth.signOut()
            navController.navigate("sign_in") {
                popUpTo("account_page") { inclusive = true }
            }
        },

        onBackClick = {
            navController.popBackStack()
        },

        navController = navController
    )
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    AccountScreenContent(
        email = "example@mail.com",
        password = "1234",
        onEmailChange = {},
        onPasswordChange = {},
        onUpdateEmailClick = {},
        onUpdatePasswordClick = {},
        onSignOutClick = {},
        onBackClick = {},
        navController = rememberNavController()
    )
}
