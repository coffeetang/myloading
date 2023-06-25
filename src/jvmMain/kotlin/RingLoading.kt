import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

@Composable
fun RingLoading(modifier: Modifier) {
    val transition = rememberInfiniteTransition()
    val ring1 = transition.animateFloat(
        0f, 360f, animationSpec = InfiniteRepeatableSpec(
            tween(
                2000,
                easing = LinearEasing
            )
        )
    )
    val ring2 = transition.animateFloat(
        60f, 420f, animationSpec = InfiniteRepeatableSpec(
            tween(
                2000,
                easing = LinearEasing
            )
        )
    )
    val ring3 = transition.animateFloat(
        90f, 450f, animationSpec = InfiniteRepeatableSpec(
            tween(
                2000,
                easing = LinearEasing
            )
        )
    )
    Box(modifier = modifier) {
        Ring(
            modifier = modifier.graphicsLayer(
                rotationX = ring1.value,
                rotationY = ring2.value,
                rotationZ = ring3.value
            ),
            0
        )
        Ring(
            modifier = modifier.graphicsLayer(
                rotationX = ring2.value,
                rotationY = ring1.value,
                rotationZ = ring3.value
            ),
            1
        )
        Ring(
            modifier = modifier.graphicsLayer(
                rotationX = ring1.value,
                rotationY = ring3.value,
                rotationZ = ring2.value
            ),
            2
        )
        Ring(
            modifier = modifier.graphicsLayer(
                rotationX = ring3.value,
                rotationY = ring2.value,
                rotationZ = ring1.value
            ),
            3
        )
        Ring(
            modifier = modifier.graphicsLayer(
                rotationX = ring3.value,
                rotationY = ring1.value,
                rotationZ = ring2.value
            ),
            4
        )
        Ring(
            modifier = modifier.graphicsLayer(
                rotationX = ring2.value,
                rotationY = ring3.value,
                rotationZ = ring1.value
            ),
            5
        )
    }
}
@Composable
fun Ring(modifier: Modifier,startIndex:Int) {
    val mSize = remember { mutableStateOf(Size(0f, 0f)) }
    val centerX = mSize.value.center.x
    val centerY = mSize.value.center.y
    val radius = centerX.coerceAtLeast(centerY)
    val index = remember { mutableStateOf(startIndex) }
    val colorList = listOf(
        Color(0xffFF1D1D), Color(0xffFF8723), Color(0xffFFBA23),
        Color(0xff00D16E), Color(0xff0055FF), Color(0xff39E7FF)
    )
    LaunchedEffect(true) {
        flow {
            while (true) {
                delay(500)
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
    Canvas(modifier = modifier) {
        mSize.value = size
        drawCircle(
            colorList[index.value],
            radius = radius,
            center = Offset(centerX, centerY),
            style = Stroke(5f)
        )
    }
}