import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

@Composable
fun ThreeBallLoading(modifier: Modifier) {
    val width = remember { mutableStateOf(800f) }
    val height = remember { mutableStateOf(800f) }
    val centerX = width.value / 2
    val centerY = height.value / 2
    val anglist = Array(3) {
        120f * it
    }
    val ballradius = 20f
    val colorList = listOf(
        Color(0xffFF1D1D),
        Color(0xff0055FF),
        Color(0xff43B988),
    )
    val transition = rememberInfiniteTransition()
    val radiusDiff = transition.animateFloat(
        ballradius / 2, ballradius * 4, animationSpec = InfiniteRepeatableSpec(
            tween(durationMillis = 500, easing = LinearEasing), repeatMode = RepeatMode.Reverse
        )
    )
    val diff = remember { mutableStateOf(0f) }
    LaunchedEffect(true) {
        flow {
            while (true) {
                emit(1)
                delay(1000)
            }
        }.collect {
            diff.value += 90f
        }
    }
    val angleDiff = animateFloatAsState(
        diff.value, TweenSpec(
            durationMillis = 500, easing = LinearEasing
        )
    )
    Canvas(
        modifier = modifier.background(Color.Black).padding(10.dp)
    ) {
        width.value = size.width
        height.value = size.height

        for (index in anglist.indices) {
            drawCircle(
                colorList[index], radius = 20f, center = Offset(
                    pointX(radiusDiff.value, centerX, anglist[index] + angleDiff.value),
                    pointY(radiusDiff.value, centerY, anglist[index] + angleDiff.value)
                )
            )
        }

    }
}

@Composable
fun TwoBallLoading(modifier: Modifier) {
    val width = remember { mutableStateOf(800f) }
    val height = remember { mutableStateOf(800f) }
    val centerX = width.value / 2
    val centerY = height.value / 2
    val radius = (centerX / 3).coerceAtLeast(centerY / 3)
    val radiusList = Array(360) { it * 0.1f }
    val transition = rememberInfiniteTransition()
    val index = transition.animateValue(
        0, 360,Int.VectorConverter ,animationSpec = InfiniteRepeatableSpec(
            tween(durationMillis = 1000), repeatMode = RepeatMode.Reverse
        )
    )
    val angDiff = transition.animateFloat(
        0f, 360f, animationSpec = InfiniteRepeatableSpec(
            tween(durationMillis = 2000)
        )
    )
    val alphaDiff = transition.animateFloat(
        0f, 1f, animationSpec = InfiniteRepeatableSpec(
            tween(durationMillis = 1000), repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(
        modifier = modifier.background(Color.Black).padding(10.dp)
    ) {
        width.value = size.width
        height.value = size.height
        drawCircle(
            Color.Red, radiusList[index.value], center = Offset(
                pointX(radius, centerX, angDiff.value),
                pointY(radius, centerY,  angDiff.value)
            ), alpha = alphaDiff.value
        )
        drawCircle(
            Color.Blue, radiusList[index.value], center = Offset(
                pointX(radius, centerX,  -angDiff.value-180f),
                pointY(radius, centerY,  -angDiff.value-180f)
            ), alpha = alphaDiff.value
        )
    }
}