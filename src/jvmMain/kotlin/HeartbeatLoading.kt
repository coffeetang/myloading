import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp

@Composable
fun HeartbeatLoading(modifier: Modifier) {
    val width = remember { mutableStateOf(800f) }
    val height = remember { mutableStateOf(800f) }
    val centerX = width.value / 2
    val centerY = height.value / 2
    val transition = rememberInfiniteTransition()
    val diff = transition.animateFloat(
        0f, 15f, animationSpec = InfiniteRepeatableSpec(
            tween(
                durationMillis = 1000, easing = LinearEasing
            )
        )
    )
    val path = Path()
    path.moveTo(centerX, height.value * 5 / 6 - diff.value)
    path.cubicTo(
        diff.value, centerY,
        width.value / 6, diff.value,
        centerX, height.value / 3 + diff.value
    )
    val path1 = Path()
    path.moveTo(centerX, height.value * 5 / 6 - diff.value)
    path.cubicTo(
        width.value - diff.value, centerY,
        width.value * 5 / 6, diff.value,
        centerX, height.value / 3 + diff.value
    )
    Canvas(
        modifier = modifier.background(Color.Black).padding(10.dp)
    ) {
        width.value = size.width
        height.value = size.height
        drawPath(path, brush = Brush.verticalGradient(listOf(Color.Red, Color.White)))
        drawPath(path1, brush = Brush.verticalGradient(listOf(Color.Red, Color.White)))
    }
}