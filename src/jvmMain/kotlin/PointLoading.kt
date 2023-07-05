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
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun PointLoading(modifier: Modifier){
    val mSize = remember { mutableStateOf(Size(0f, 0f)) }
    val centerX = mSize.value.center.x
    val centerY = mSize.value.center.y
    val radius = centerX.coerceAtLeast(centerY)
    val angleList = Array(9) { 40f * it }
    val radiusList = Array(9) {
        3f * it
    }
    val alphaList = Array(9) {
        0.1f + 0.1f * it
    }
    val transition = rememberInfiniteTransition()
    val listIndex = transition.animateValue(
        0, 8, Int.VectorConverter,
        animationSpec = InfiniteRepeatableSpec(
            tween(1000, easing = LinearEasing)
        )
    )
    Canvas(modifier = modifier) {
        mSize.value = size
        for (index in angleList.indices) {
            var mIndex = listIndex.value+index
            if(mIndex > 8){
                mIndex = 0
            }
            drawCircle(
                Color.Red, radiusList[mIndex], center = Offset(
                    pointX(radius, centerX, angleList[index]),
                    pointY(radius, centerY, angleList[index])
                ), alpha = alphaList[mIndex]
            )
        }
    }
}

@Composable
fun RecPointLoading(modifier: Modifier){
    val width = remember { mutableStateOf(800f) }
    val height = remember { mutableStateOf(800f) }
    val xUnit = width.value / 5
    val yUnit = height.value / 5
    val radius = xUnit.coerceAtLeast(yUnit) / 3
    val xList = Array(4) {
        xUnit + it * xUnit
    }
    val yList = Array(4) {
        yUnit + it * yUnit
    }
    val transition = rememberInfiniteTransition()
    val target = transition.animateValue(
        0, 7, Int.VectorConverter, animationSpec = InfiniteRepeatableSpec(
            tween(durationMillis = 1000, easing = LinearEasing)
        )
    )
    Canvas(
        modifier = modifier.background(Color.Black).padding(10.dp)
    ) {
        width.value = size.width
        height.value = size.height
        for (xIndex in xList.indices) {
            for (yIndex in yList.indices) {
                val temp = xIndex + yIndex
                val tempAlpha = abs(target.value - temp) * 0.1f+0.5f
                if (tempAlpha < 1) {
                    drawCircle(Color.Red,
                        radius = radius * tempAlpha,
                        center = Offset(xList[xIndex], yList[yIndex]),
                        alpha = tempAlpha
                    )
                } else {
                    drawCircle(Color.Red, radius = radius, center = Offset(xList[xIndex], yList[yIndex]))
                }
            }
        }
    }
}

@Composable
fun JumpPointLoading(modifier: Modifier){
    val width = remember { mutableStateOf(800f) }
    val height = remember { mutableStateOf(800f) }
    val xList = Array(6) { width.value / 7 + it * width.value / 7 }
    val colorList = listOf(
        Color(0xffFF1D1D),
        Color(0xffFF8723),
        Color(0xffFFBA23),
        Color(0xff43B988),
        Color(0xff00D16E),
        Color(0xff0055FF),
    )
    val transition = rememberInfiniteTransition()
    val diffIndex = transition.animateFloat(
        0f, 60f, animationSpec = InfiniteRepeatableSpec(
            tween(durationMillis = 1000, easing = FastOutLinearInEasing), repeatMode = RepeatMode.Reverse
        )
    )
    Canvas(
        modifier = modifier.background(Color.Black).padding(10.dp)
    ) {
        width.value = size.width
        height.value = size.height
        for (index in xList.indices) {
            val value = diffIndex.value - index * 10f
            drawCircle(
                colorList[index],
                radius = 10f,
                center = Offset(xList[index], height.value * 3 / 7 - abs(value))
            )
            drawCircle(
                brush = Brush.verticalGradient(listOf(colorList[index], Color.Transparent)),
                radius = 10f,
                center = Offset(xList[index], height.value * 4 / 7 + abs(value)),
                alpha = 0.6f
            )
        }
    }
}
fun pointX(radius: Float, centerX: Float, angle: Float): Float {
    val res = Math.toRadians(angle.toDouble())
    return centerX - cos(res).toFloat() * (radius)
}

fun pointY(radius: Float, centerY: Float, angle: Float): Float {
    val res = Math.toRadians(angle.toDouble())
    return centerY - sin(res).toFloat() * (radius)
}