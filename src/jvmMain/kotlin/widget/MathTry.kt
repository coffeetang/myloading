package widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import loading.pointX
import loading.pointY
import kotlin.math.*

@Composable
fun MathTest() {
    var width by remember { mutableStateOf(800f) }
    var height by remember { mutableStateOf(800f) }
    val centerX = width / 2
    val centerY = height / 2
    val radius = centerX.coerceAtLeast(centerY) / 2
    val colorList = listOf(
        Color(0xffFF1D1D),
        Color(0xff39E7FF),
        Color(0xffFF8723),
        Color(0xffFFBA23),
        Color(0xff00D16E),
        Color(0xff0055FF)
    )
    val transition = rememberInfiniteTransition()
    val index by transition.animateValue(
        0, colorList.size, Int.VectorConverter, animationSpec = InfiniteRepeatableSpec(
            tween(1000), repeatMode = RepeatMode.Reverse
        )
    )
    val color by animateColorAsState(colorList[index], animationSpec = TweenSpec(durationMillis = 500))
    val angle by transition.animateFloat(
        0f, 360f, animationSpec = InfiniteRepeatableSpec(
            keyframes {
                durationMillis = 20000
                360f.at(20000)
            }, repeatMode = RepeatMode.Reverse

        )
    )

    val count = 4800
    val xList = Array(count) {
        pointX(radius +cos(it+angle/60)*radius/3, centerX,  it+angle)
    }
    val yList = Array(count) {
        pointY(radius+sin(it+angle/10)*radius/3, centerY, it+angle)
    }
    val pList = Array(count) { Offset(xList[it], yList[it]) }.asList()

    Canvas(modifier = Modifier.fillMaxSize()) {
        width = size.width
        height = size.height
        drawPoints(
            points = pList,
            pointMode = PointMode.Points,
            brush = Brush.radialGradient(listOf(color, Color.White)),
            strokeWidth = 3f
        )
    }
}