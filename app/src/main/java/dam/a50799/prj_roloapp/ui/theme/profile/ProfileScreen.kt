package dam.a50799.prj_roloapp.ui.theme.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dam.a50799.prj_roloapp.R
import dam.a50799.prj_roloapp.data.local.entities.UserProfile
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.home.HomeScreenContent
import dam.a50799.prj_roloapp.ui.theme.laranja
import dam.a50799.prj_roloapp.utils.dropShadow
import org.checkerframework.common.subtyping.qual.Bottom

@Composable
fun ProfileScreenContent(
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onEditClick: () -> Unit,
    onSaveClick: () -> Unit,
    navController: NavController?,
    userProfile: UserProfile?
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) {
        IconButton(
            onClick = onSettingsClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 24.dp)
                .size(40.dp)
        ) {
            Icon(
                modifier = Modifier.size(34.dp),
                painter = painterResource(id = R.drawable.settings),
                contentDescription = "Settings Icon",
                tint = Color.Black
            )
        }

        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 24.dp)
                .size(40.dp)
        ) {
            Icon(
                modifier = Modifier.size(34.dp),
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "Settings Icon",
                tint = Color.Black
            )
        }

        //Spacer(modifier = Modifier.height(50.dp))

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                modifier = Modifier
                    .size(100.dp),
                painter = painterResource(id = R.drawable.default_icon),
                contentDescription = "Profile Icon",
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = userProfile?.name ?: "User",
                fontSize = 36.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,

                )

            Text(
                text = "Age: ${userProfile?.age ?: "Not set"}",
                fontSize = 20.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.sp
            )

            Text(
                text = "Favorite Film: ${userProfile?.favFilm ?: "Not set"}",
                fontSize = 14.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Light,
            )
        }

        Button(
            modifier = Modifier
                .width(330.dp)
                .height(70.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
                .dropShadow(),
            onClick = {},
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = laranja,
                contentColor = Color.Black
            ),
            border = BorderStroke(2.dp, Color.Black)

        ) {
            Text(
                "Save Changes",
                fontSize = 24.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium,

                )
        }

    }
}

@Composable
fun ProfileScreen(
    navController: NavController
) {

    val user = remember { FirebaseAuth.getInstance().currentUser }
    var userProfile by remember { mutableStateOf<UserProfile?>(null) }

    // carregar os dados do perfil
    LaunchedEffect(user) {
        if (user != null) {
            val db = FirebaseFirestore.getInstance()
            db.collection("users").document(user.uid).get()
                .addOnSuccessListener { document ->
                    userProfile = UserProfile(
                        uid = user.uid,
                        name = document.getString("name") ?: "",
                        age = document.getString("age") ?: "",
                        favFilm = document.getString("favFilm") ?: ""
                    )
                }
        }
    }

    ProfileScreenContent(
        onBackClick = {
            navController.popBackStack()
        }, onSettingsClick = {
            navController.navigate("settings")
        }, onEditClick = {
            // TODO
        }, onSaveClick = {
            navController.navigate("homescreen")
        },
        navController = navController,
        userProfile = userProfile
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreenContent(
        onBackClick = {},
        onSettingsClick = {},
        onEditClick = {},
        onSaveClick = {},
        navController = null,
        userProfile = null
    )
}