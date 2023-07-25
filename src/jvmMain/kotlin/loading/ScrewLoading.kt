package loading

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ScrewLoading(modifier: Modifier){
    val width = remember { mutableStateOf(800f) }
    val height = remember { mutableStateOf(800f) }
    val centerX = width.value / 2
    val centerY = height.value / 2
    val radiusList = Array(1080) { it.toFloat() * 0.1f }
    val anglist = Array(1080) { it.toFloat() }
    val transition = rememberInfiniteTransition()
    val diff = transition.animateFloat(
        0f, 360f, animationSpec = InfiniteRepeatableSpec(
            tween(durationMillis = 1000, easing = LinearEasing)
        )
    )
    val colorList = listOf(
        Color(0xffFF1D1D),
        Color(0xffFF8723),
        Color(0xffFFBA23),
        Color(0xff43B988),
        Color(0xff00D16E),
        Color(0xff0055FF),
        Color(0xff4E6FD7),
    )
    val colorIndex = transition.animateValue(
        0, 7, Int.VectorConverter, animationSpec = InfiniteRepeatableSpec(
            tween(durationMillis = 3000, easing = LinearEasing)
        )
    )
    val colorSet = animateColorAsState(
        colorList[colorIndex.value], animationSpec = TweenSpec(
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
                colorSet.value,
                radius = 2f,
                center = Offset(
                    pointX(radiusList[index], centerX, anglist[index]+diff.value),
                    pointY(radiusList[index], centerY, anglist[index]+diff.value)
                )
            )
        }
    }
}