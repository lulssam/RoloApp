package dam.a50799.prj_roloapp.ui.theme.welcome

import android.graphics.Paint
import android.graphics.Typeface
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dam.a50799.prj_roloapp.ui.theme.Roboto
import dam.a50799.prj_roloapp.ui.theme.cinzento
import dam.a50799.prj_roloapp.ui.theme.laranja
import dam.a50799.prj_roloapp.utils.customTextFieldColors
import dam.a50799.prj_roloapp.utils.dropShadow
@Composable
fun WelcomeAgeContent(
    userName: String,
    age: String,
    onAgeChange: (String) -> Unit,
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AutoResizeTitle(text = "Hello $userName!")

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "What's your age?",
                fontSize = 32.sp,
                fontWeight = FontWeight.Light,
                fontFamily = Roboto
            )
            Spacer(Modifier.height(24.dp))

            OutlinedTextField(
                value = age,
                onValueChange = onAgeChange,
                placeholder = {
                    Text(
                        text = "Insert Age",
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Medium,
                        color = cinzento,
                        fontSize = 24.sp,
                        letterSpacing = 4.sp
                    )
                },
                textStyle = LocalTextStyle.current.copy(
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    letterSpacing = 4.sp,
                    color = Color.Black
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .width(340.dp)
                    .dropShadow(),
                shape = RoundedCornerShape(20.dp),
                colors = customTextFieldColors()
            )
            Spacer(Modifier.height(48.dp))
        }

        Button(
            onClick = onNextClick,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp)
                .width(310.dp)
                .height(60.dp)
                .dropShadow(),
            colors = ButtonDefaults.buttonColors(laranja, Color.Black),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Text(
                text = "Next",
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                letterSpacing = 7.sp
            )
        }
    }
}

@Composable
fun WelcomeScreenAge(
    navController: NavController,
    viewModel: WelcomeViewModel = viewModel()
) {
    val age by viewModel.age.collectAsState()
    val name by viewModel.name.collectAsState()

    WelcomeAgeContent(
        userName = name,
        age = age,
        onAgeChange = { viewModel.updateAge(it) },
        onNextClick = { navController.navigate("welcome_favFilm") }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeAgeContentPreview() {
    WelcomeAgeContent(
        userName = "Mariana",
        age = "25",
        onAgeChange = {},
        onNextClick = {}
    )
}

@Composable
fun AutoResizeTitle(text: String) {
    var fontSize by remember { mutableStateOf(64.sp) }
    var parentWidth by remember { mutableStateOf(0f) }
    var textWidth by remember { mutableStateOf(0f) }
    val density = LocalDensity.current

    LaunchedEffect(text, parentWidth) {
        if (parentWidth == 0f) return@LaunchedEffect

        val paint = Paint().apply {
            isAntiAlias = true
            typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD)
        }

        var currentSize = 64f
        var lastFitSize = 12f

        while (currentSize >= 12f) {
            val sizePx = with(density) { currentSize.sp.toPx() }
            paint.textSize = sizePx
            val measuredWidth = paint.measureText(text)

            if (measuredWidth <= parentWidth) {
                lastFitSize = currentSize
                textWidth = measuredWidth
                break
            }
            currentSize -= 1f
        }

        fontSize = lastFitSize.sp
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .onGloballyPositioned {
                parentWidth = it.size.width.toFloat()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontFamily = Roboto,
            fontSize = fontSize,
            fontWeight = FontWeight.Black,
            maxLines = 1,
            overflow = TextOverflow.Visible,
            softWrap = false,
            modifier = Modifier.offset {
                IntOffset(
                    x = ((parentWidth - textWidth) / 2).toInt(),
                    y = 0
                )
            }
        )
    }
}
