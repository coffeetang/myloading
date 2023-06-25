import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
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
fun pointX(radius: Float, centerX: Float, angle: Float): Float {
    val res = Math.toRadians(angle.toDouble())
    return centerX - cos(res).toFloat() * (radius)
}

fun pointY(radius: Float, centerY: Float, angle: Float): Float {
    val res = Math.toRadians(angle.toDouble())
    return centerY - sin(res).toFloat() * (radius)
}