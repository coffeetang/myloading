package widget

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import loading.pointX
import loading.pointY
import kotlin.math.abs
import kotlin.math.sin

@Composable
fun ElectricAnim(modifier: Modifier) {
    var width by remember { mutableStateOf(600f) }
    var height by remember { mutableStateOf(600f) }
    val topLineHeight = height * 2 / 3
    val unitHeight = height / 30
    val secondHeight = topLineHeight + unitHeight
    val thirdHeight = topLineHeight + unitHeight * 3
    val fourHeight = topLineHeight + unitHeight * 6
    val transition = rememberInfiniteTransition()
    val rightAngleList = listOf(180f, 210f, 240f).map {
        transition.animateFloat(it, it + 90f, animationSpec = InfiniteRepeatableSpec(keyframes {
            durationMillis = 1000
            (it + 90f).at(1000)
        }))
    }
    val leftAngleList = listOf(0f, -30f, -60f).map {
        transition.animateFloat(it, it - 90f, animationSpec = InfiniteRepeatableSpec(keyframes {
            durationMillis = 1000
            (it - 90f).at(1000)
        }))
    }
    val rightPosList = Array(3) {
        Offset(pointX(width, 0f, rightAngleList[it].value), pointY(width, topLineHeight, rightAngleList[it].value))
    }
    val leftPosList = Array(3) {
        Offset(pointX(width, width, leftAngleList[it].value), pointY(width, topLineHeight, leftAngleList[it].value))
    }

    val waveOneY by transition.animateFloat(
        topLineHeight,
        height / 2,
        animationSpec = InfiniteRepeatableSpec(tween(500), repeatMode = RepeatMode.Restart)
    )
    val waveTwoY by transition.animateFloat(
        height / 4, thirdHeight,
        animationSpec = InfiniteRepeatableSpec(tween(400), repeatMode = RepeatMode.Restart)
    )
    val waveThreeY by transition.animateFloat(
        thirdHeight, height / 4,
        animationSpec = InfiniteRepeatableSpec(tween(500), repeatMode = RepeatMode.Restart)
    )
    val waveFourY by transition.animateFloat(
        height / 2, topLineHeight,
        animationSpec = InfiniteRepeatableSpec(tween(400), repeatMode = RepeatMode.Restart)
    )
    val path = Path().apply {
        moveTo(0f, waveOneY)
        cubicTo(width / 3, waveTwoY, width * 2 / 3, waveThreeY, width, waveFourY)
    }

    val xnum by transition.animateValue(
        40,
        20, typeConverter = Int.VectorConverter,
        animationSpec = InfiniteRepeatableSpec(keyframes {
            durationMillis = 2000
            20.at(2000)
        }, repeatMode = RepeatMode.Reverse)
    )

    val count = xnum
    val unit = width / count
    val xList = Array(count) {
        it * unit
    }

    val range by transition.animateFloat(
        10f,
        200f,
        animationSpec = InfiniteRepeatableSpec(keyframes {
            durationMillis = 500
            200f.at(500)
        }, repeatMode = RepeatMode.Reverse)
    )
    val yList = Array(count) {
        topLineHeight - abs(sin(it * unit))*range
    }
    val pList = Array(count) { Offset(xList[it], yList[it]) }.asList()

    val sList = remember { mutableStateListOf<Square>() }
    val offsetList = remember { mutableStateListOf<Offset>() }
    var top by remember { mutableStateOf(false) }
    var pointCount by remember { mutableStateOf(0) }
    var bottomPadding by remember { mutableStateOf((20..50).random()) }
    var topPadding by remember { mutableStateOf((-10..40).random()) }
    var horizonOffset by remember { mutableStateOf((5..70).random()) }
    offsetList.clear()
    sList.forEach {
        it.update()
        offsetList.add(Offset(it.x, it.y))
    }
    LaunchedEffect(sList.size) {
        flow {
            delay(500)
            emit(1)
        }.collect {
            if (top) {
                pointCount++
                sList.add(Square(horizonOffset+width+width/3 , height / 2  - topPadding))
                if (pointCount == 2) {
                    top = false
                    pointCount = 0
                    topPadding = (-5..60).random()
                    horizonOffset = (5..40).random()
                }
            } else {
                pointCount++
                sList.add(Square(horizonOffset+width+width/3 , topLineHeight - bottomPadding))
                if (pointCount == 2) {
                    top = true
                    pointCount = 0
                    bottomPadding = (30..50).random()
                    horizonOffset = (5..40).random()
                }
            }
        }
    }
    val bgTopOffset = listOf(
        Offset(0f, 0f),
        Offset(width / 3, 0f),
        Offset(width * 2 / 3, 0f),
        Offset(width, 0f))
    val bgBottomOffset = listOf(
        Offset(width, topLineHeight),
        Offset(width * 2 / 3, topLineHeight),
        Offset(width / 3, topLineHeight),
        Offset(0f, topLineHeight)
    )
    val alphaDiff by transition.animateFloat(
        0f, 0.3f,
        animationSpec = InfiniteRepeatableSpec(keyframes {
            durationMillis = 1000
            0.2f.at(300)
            0.3f.at(500)
        })
    )
    Box(modifier = modifier) {
        Canvas(modifier = modifier.blur(20.dp)) {
            bgTopOffset.forEachIndexed { index, offset ->
                drawLine(
                    color = Color(0xff4D36D4),
                    strokeWidth = 80f,
                    start = offset,
                    end = bgBottomOffset[index],
                    alpha = alphaDiff
                )
            }
        }
        Canvas(modifier = modifier) {
            width = size.width
            height = size.height
            drawLine(
                color = Color(0xff391ED9), start = Offset(0f, topLineHeight),
                end = Offset(width, topLineHeight), strokeWidth = 3f
            )
            drawLine(
                color = Color(0xff391ED9), start = Offset(0f, secondHeight),
                end = Offset(width, secondHeight), strokeWidth = 3f
            )
            drawLine(
                color = Color(0xff391ED9), start = Offset(0f, thirdHeight),
                end = Offset(width, thirdHeight), strokeWidth = 3f
            )
            drawLine(
                color = Color(0xff391ED9), start = Offset(0f, fourHeight),
                end = Offset(width, fourHeight), strokeWidth = 3f
            )


            rightPosList.forEach {
                drawLine(color = Color(0xff391ED9), start = Offset(0f, topLineHeight), end = it, strokeWidth = 3f)
            }
            leftPosList.forEach {
                drawLine(color = Color(0xff391ED9), start = Offset(width, topLineHeight), end = it, strokeWidth = 3f)
            }

            drawPoints(
                points = offsetList,
                cap = StrokeCap.Round,
                brush = Brush.verticalGradient(listOf(Color(0xff341CC6),Color.Black, Color(0xff341CC6),Color.Black)),
                pointMode = PointMode.Polygon,
                strokeWidth = 30f
            )

            pList.forEachIndexed { index, offset ->
                drawLine(
                    color = Color(0xff8F83DA),
                    strokeWidth = 10f,
                    start = Offset(xList[index], topLineHeight),
                    end = offset
                )
            }
            drawPath(path = path, color = Color(0xff8F83DA), style = Stroke(2f))

        }
    }

}