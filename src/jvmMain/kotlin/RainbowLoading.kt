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
fun RainbowLoading(modifier: Modifier) {
    val width = remember { mutableStateOf(800f) }
    val height = remember { mutableStateOf(800f) }
    val singleWidth = 10f
    val gap = 15f
    val colorList = listOf(
        Color(0xffFF1D1D),
        Color(0xffFF8723),
        Color(0xffFFBA23),
        Color(0xff43B988),
        Color(0xff00D16E),
        Color(0xff366EFF),
        Color(0xff1E2FA2),
    )
    val circleRdius = width.value / 2f.coerceAtMost(height.value / 2f) - 20f
    val transition = rememberInfiniteTransition()
    val start = transition.animateFloat(
        0f, 360f, animationSpec = InfiniteRepeatableSpec(
            keyframes {
                durationMillis = 1500
                430f.at(800)
                360f.at(1500)
            })
    )
    val start1 = transition.animateFloat(
        0f, 360f, animationSpec = InfiniteRepeatableSpec(
            keyframes {
                durationMillis = 1400
                delayMillis = 100
                420f.at(700)
                360f.at(1400)
            })
    )
    val start2 = transition.animateFloat(
        0f, 360f, animationSpec = InfiniteRepeatableSpec(
            keyframes {
                durationMillis = 1300
                delayMillis = 200
                410f.at(600)
                360f.at(1300)
            })
    )
    val start3 = transition.animateFloat(
        0f, 360f, animationSpec = InfiniteRepeatableSpec(
            keyframes {
                durationMillis = 1200
                delayMillis = 300
                400f.at(500)
                360f.at(1200)
            })
    )
    val start4 = transition.animateFloat(
        0f, 360f, animationSpec = InfiniteRepeatableSpec(
            keyframes {
                durationMillis = 1100
                delayMillis = 400
                390f.at(400)
                360f.at(1100)
            })
    )
    val start5 = transition.animateFloat(
        0f, 360f, animationSpec = InfiniteRepeatableSpec(
            keyframes {
                durationMillis = 1000
                delayMillis = 500
                380f.at(300)
                360f.at(1000)
            })
    )
    val start6 = transition.animateFloat(
        0f, 360f, animationSpec = InfiniteRepeatableSpec(
            keyframes {
                durationMillis = 900
                delayMillis = 600
                370f.at(200)
                360f.at(900)
            })
    )
    Canvas(
        modifier = modifier.background(Color.Black).padding(10.dp)
    ) {
        width.value = size.width
        height.value = size.height
        for (index in colorList.indices) {
            drawArc(
                color = colorList[index],
                startAngle = when (index) {
                    0 -> start.value
                    1 -> start1.value
                    2 -> start2.value
                    3 -> start3.value
                    4 -> start4.value
                    5 -> start5.value
                    else -> start6.value
                },
                sweepAngle = -180f,
                useCenter = false,
                style = Stroke(singleWidth, cap = StrokeCap.Round),
                size = Size(
                    circleRdius * 2 - singleWidth * 2 * index - gap * 2 * index,
                    circleRdius * 2 - singleWidth * 2 * index - gap * 2 * index
                ),
                topLeft = Offset(singleWidth * index + gap * index, singleWidth * index + gap * index),
            )
        }
    }
}

