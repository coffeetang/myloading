package loading

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

@Composable
fun TailLoading(modifier: Modifier) {
    val mSize = remember { mutableStateOf(Size(0f, 0f)) }
    val centerX = mSize.value.center.x
    val centerY = mSize.value.center.y
    val radius = centerX.coerceAtLeast(centerY)/2
    val transition = rememberInfiniteTransition()
    val angle = transition.animateFloat(
        0f, 360f, animationSpec = InfiniteRepeatableSpec(
            tween(1500, easing = LinearEasing)
        )
    )
    val strokeList = Array(100) { 0f + it * 0.2f }
    val sweepAngleList = Array(100) { 150f - it * 1.5f }
    val startInterval = Array(100) { 0f + it * 1.5f }
    val index = remember { mutableStateOf(0) }
    val colorList = listOf(
        Color(0xffFF1D1D), Color(0xffFF8723),
        Color(0xffFFBA23), Color(0xff00D16E),
        Color(0xff0055FF), Color(0xff39E7FF)
    )
    LaunchedEffect(true) {
        flow {
            while (true) {
                delay(1000)
                emit(1)
            }
        }.collect {
            if (index.value < colorList.lastIndex) {
                index.value++
            } else {
                index.value = 0
            }
        }
    }

    val myColor = animateColorAsState(
        colorList[index.value], animationSpec = TweenSpec(
            durationMillis = 1000, easing = LinearEasing
        )
    )

    Canvas(modifier = modifier) {
        mSize.value = size
        for (pos in strokeList.indices) {
            drawArc(
                myColor.value,
                startAngle = angle.value + startInterval[pos],
                sweepAngle = sweepAngleList[pos],
                useCenter = false,
                style = Stroke(strokeList[pos], cap = StrokeCap.Round),
                size = Size(radius * 2 + 40f, radius * 2 + 40f),
                topLeft = Offset(centerX - radius - 20f, centerY - radius - 20f)
            )
        }
    }
}