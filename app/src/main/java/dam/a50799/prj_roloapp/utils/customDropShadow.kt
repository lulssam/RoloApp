package dam.a50799.prj_roloapp.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.dropShadow(
    color: Color = Color(0xCC000000),
    offsetX: Dp = 6.dp,
    offsetY: Dp = 5.dp,
    spread: Dp = 1.dp,
    cornerRadius: Dp = 20.dp
) = this.then(
    Modifier.drawBehind {
        val paint = Paint().apply {
            this.color = color
        }

        val left = 0f + offsetX.toPx()
        val top = 0f + offsetY.toPx()
        val width = size.width + spread.toPx()
        val height = size.height + spread.toPx()

        drawRoundRect(
            color = color,
            topLeft = Offset(left, top),
            size = androidx.compose.ui.geometry.Size(width, height),
            cornerRadius = CornerRadius(cornerRadius.toPx(), cornerRadius.toPx())
        )
    }
)