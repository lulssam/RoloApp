package dam.a50799.prj_roloapp.ui.theme.settings

import android.widget.Button
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dam.a50799.prj_roloapp.R
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.amareloTorrado
import dam.a50799.prj_roloapp.ui.theme.cinzento
import dam.a50799.prj_roloapp.utils.dropShadow

@Composable
fun SettingsScreenContent(
    onAccountClick: () -> Unit,
    onHelpClick: () -> Unit,
    onLanguageClick: () -> Unit,
    onAboutClick: () -> Unit,
    navController: NavController?
) {
    // region Coluna principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp), // 100
        verticalArrangement = Arrangement.Top, //Arrangement.spacedBy(35.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // region top bar com botão de voltar e settings
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
        ) {
            IconButton(
                onClick = { navController?.popBackStack() },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Voltar",
                    tint = Color.Black
                )
            }

            Box( // region box settings
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Settings",
                    fontSize = 24.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            } // endregion
        } // endregion

        Spacer(modifier = Modifier.height(40.dp))

        // region coluna opções
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(35.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SettingsButtons(text = "Account", onClick = onAccountClick)
            SettingsButtons(text = "Help", onClick = onHelpClick)
            SettingsButtons(text = "Language", onClick = onLanguageClick)
            SettingsButtons(text = "About", onClick = onAboutClick)
        } // endregion

    } // endregion
}

@Composable
fun SettingsButtons(
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .width(330.dp)
            .height(70.dp)
            .dropShadow(),
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        border = BorderStroke(2.dp, cinzento)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                fontSize = 24.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.sp,
            )

            Icon(
                painter = painterResource(id = R.drawable.navigate_next),
                contentDescription = "Ir",
                tint = Color.Black,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun SettingsScreen(navController: NavController) {
    SettingsScreenContent(
        onAccountClick = {navController.navigate("account_page")},
        onHelpClick = {navController.navigate("help_page")},
        onLanguageClick = {navController.navigate("language_page")},
        onAboutClick = {navController.navigate("about_page")},
        navController = navController
    )
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    SettingsScreenContent(
        onAccountClick = {},
        onHelpClick = {},
        onLanguageClick = {},
        onAboutClick = {},
        navController = null
    )
}