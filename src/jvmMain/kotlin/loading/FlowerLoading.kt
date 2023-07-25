package loading

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FlowerLoading(modifier: Modifier) {
    val width = remember { mutableStateOf(800f) }
    val height = remember { mutableStateOf(800f) }
    val centerX = width.value / 2
    val centerY = height.value / 2
    val colorList = listOf(
            Color(0xffFF1D1D),
            Color(0xffFF8723),
            Color(0xffFFBA23),
            Color(0xff43B988),
            Color(0xff00D16E),
            Color(0xff0055FF),
    )
    val duration = 3000
    val transtion = rememberInfiniteTransition()
    val diffAngle = transtion.animateFloat(
            0f, 360f, animationSpec = InfiniteRepeatableSpec(
            tween(duration)
        )
    )
    val diffDis = transtion.animateFloat(
            40f, 60f, animationSpec = InfiniteRepeatableSpec(
            tween(duration), repeatMode = RepeatMode.Reverse
        )
    )
    val colorIndex = transtion.animateValue(
            0, colorList.size, Int.VectorConverter,
            animationSpec = InfiniteRepeatableSpec(
                    tween(duration)
            )
    )
    val color = animateColorAsState(colorList[colorIndex.value], animationSpec = TweenSpec(1000))
    val offsetList = Array(10) {
        Offset(
                pointX(diffDis.value, centerX, it * 36f+diffAngle.value),
                pointY(diffDis.value, centerY, it * 36f+diffAngle.value)
        )
    }
    Canvas(
            modifier = modifier
    ) {
        width.value = size.width
        height.value = size.height
        for (oo in offsetList.indices) {
            drawCircle(
                    color = color.value,
                    radius = 50f,
                    center = offsetList[oo],
                    blendMode = BlendMode.Plus
            )
        }
    }
}