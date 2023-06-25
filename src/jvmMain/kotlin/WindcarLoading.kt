import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun WindCarLoading(width: Dp, height: Dp) {
    val transition = rememberInfiniteTransition()
    val angle = transition.animateFloat(
        0f, 360f, animationSpec = InfiniteRepeatableSpec(
            tween(durationMillis = 3000, easing = LinearEasing)
        )
    )
    Box(modifier = Modifier.size(width, height)) {
        val windSize = width.coerceAtLeast(height)
        Spacer(
            modifier = Modifier.padding(top = windSize / 4).width(5.dp).fillMaxHeight().align(Alignment.Center)
                .background(Color.Yellow)
        )
        Windcar(modifier = Modifier.size(width).rotate(angle.value))
    }
}
@Composable
fun Windcar(modifier: Modifier) {
    val mSize = remember { mutableStateOf(Size(0f, 0f)) }
    val width = mSize.value.width
    val height = mSize.value.height
    val path = Path()
    path.moveTo(width/2, height/2)
    path.quadraticBezierTo(0f, 0f, width / 2, 0f)
    val path1 = Path()
    path1.moveTo(width/2, height/2)
    path1.quadraticBezierTo(width, 0f, width, height / 2)
    val path2 = Path()
    path2.moveTo(width/2, height/2)
    path2.quadraticBezierTo(width, height, width / 2, height)
    val path3 = Path()
    path3.moveTo(width/2, height/2)
    path3.quadraticBezierTo(0f, width, 0f, height / 2)
    Canvas(modifier = modifier) {
        mSize.value = size
        drawPath(path, Color.Red)
        drawPath(path1, Color.Red)
        drawPath(path2, Color.Red)
        drawPath(path3, Color.Red)
    }
}