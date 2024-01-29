package loading

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

val ANGLES = 0..180
val RADIUS = 20..30


@Composable
fun fireLoading(modifier: Modifier) {
    var width by remember { mutableStateOf(600f) }
    var height by remember { mutableStateOf(600f) }
    val centerx = width / 2
    val centery = height / 2
    val radius = width / 6
    val colorList = listOf(
        Color(0xffFF966B),
        Color(0xffFFBA23),
        Color(0xffF2C371),
        Color(0xffFFC000),
    )
    val transtion = rememberInfiniteTransition()
    val angle by transtion.animateFloat(
        0f, 360f, animationSpec = InfiniteRepeatableSpec(
            keyframes {
                durationMillis = 2000
                360f.at(2000)
            }
        )
    )
    var tapx = pointX(radius, centerx, angle)
    var tapy = pointY(radius, centery, angle)
    val colorList2 = listOf(
        Color(0xff337AFF),
        Color(0xff01B9FF),
        Color(0xff00C1F2),
        Color(0xff3A69FF),
    )
    var tapx2 = pointX(radius, centerx, angle + 180f)
    var tapy2 = pointY(radius, centery, angle + 180f)

    val particleList = remember { mutableStateListOf<Particle>() }
    particleList.forEach {
        it.update()
    }

    LaunchedEffect(particleList.size) {
        flow {
            delay(10)
            emit(1)
        }.collect {
            val tempAngle = ANGLES.random()
            val tempRadius = RADIUS.random()
            val tempColor = colorList.random()
            val tempColor2 = colorList2.random()
            val ball = Particle(
                startX = tapx, startY = tapy,
                angle = tempAngle,
                dis = 2f,
                size = tempRadius.toFloat(),
                color = tempColor
            )
            val ball2 = Particle(
                startX = tapx2, startY = tapy2,
                angle = tempAngle,
                dis = 2f,
                size = tempRadius.toFloat(),
                color = tempColor2
            )
            particleList.add(ball2)
            particleList.add(ball)
        }
    }


    Canvas(modifier = modifier.blur(7.dp)) {
        width = size.width
        height = size.height
        particleList.forEach {
            drawCircle(
                color = it.color,
                radius = it.size,
                center = Offset(it.endx, it.endy),
                alpha = it.alpha
            )
        }
    }
}

class Particle(
    var startX: Float = 0f,
    var startY: Float = 0f,
    var endx: Float = 0f,
    var endy: Float = 0f,
    val angle: Int = 0,
    var dis: Float = 1f,
    var size: Float = 5f,
    val color: Color = Color.Red,
    var alpha: Float = 1f
) {
    init {
        endx = pointX(dis, startX, angle.toFloat())
        endy = pointY(dis, startY, angle.toFloat())
    }

    fun update() {
        endx = pointX(dis, endx, angle.toFloat())
        endy = pointY(dis, endy, angle.toFloat())
        alpha -= 0.02f
        if (alpha < 0) {
            alpha = 0f
        }
        size -= 0.5f
        if (size < 0f) {
            size = 0f
        }
    }
}