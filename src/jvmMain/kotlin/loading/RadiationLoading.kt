package loading

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun RadiationLoading(modifier: Modifier){
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
        Color(0xff4E6FD7),
    )
    val radiusList = Array(7) { 20f + 20f * it }
    val anglist = Array(360) { 1f + it * 1f }
    val transition = rememberInfiniteTransition()
    val angleIndex = transition.animateValue(
        0, 359, Int.VectorConverter, animationSpec = InfiniteRepeatableSpec(
            tween(durationMillis = 3000, easing = LinearEasing)
        )
    )
    val colorIndex = transition.animateValue(
        0, 7, Int.VectorConverter, animationSpec = InfiniteRepeatableSpec(
            tween(durationMillis = 1000, easing = LinearEasing)
        )
    )
    Canvas(
        modifier = modifier.background(Color.Black).padding(10.dp)
    ) {
        width.value = size.width
        height.value = size.height
        for (index in radiusList.indices) {
            drawArc(
                color = if (colorIndex.value == index) colorList[index] else Color.White,
                startAngle = anglist[angleIndex.value]+120f, sweepAngle = 60f, useCenter = false,
                topLeft = Offset(centerX - radiusList[index], centerY - radiusList[index]),
                size = Size(radiusList[index] * 2, radiusList[index] * 2),
                style = Stroke(if (colorIndex.value == index) 8f else 2f, cap = StrokeCap.Round)
            )
            drawArc(
                color = if (colorIndex.value == index) colorList[index] else Color.White,
                startAngle = anglist[angleIndex.value]+240f, sweepAngle = 60f, useCenter = false,
                topLeft = Offset(centerX - radiusList[index], centerY - radiusList[index]),
                size = Size(radiusList[index] * 2, radiusList[index] * 2),
                style = Stroke(if (colorIndex.value == index) 8f else 2f, cap = StrokeCap.Round)
            )
            drawArc(
                color = if (colorIndex.value == index) colorList[index] else Color.White,
                startAngle = anglist[angleIndex.value], sweepAngle = 60f, useCenter = false,
                topLeft = Offset(centerX - radiusList[index], centerY - radiusList[index]),
                size = Size(radiusList[index] * 2, radiusList[index] * 2),
                style = Stroke(if (colorIndex.value == index) 8f else 2f, cap = StrokeCap.Round)
            )
        }
        drawCircle(colorList[colorIndex.value], radius = 10f, center = Offset(centerX,centerY))
    }
}