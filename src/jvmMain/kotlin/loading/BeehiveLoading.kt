package loading

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt

@Composable
fun BeehiveLoading(modifier: Modifier) {
    val width = remember { mutableStateOf(800f) }
    val height = remember { mutableStateOf(800f) }
    val centerX = width.value / 2
    val centerY = height.value / 2
    val sideWith = width.value / 20
    val xList = mutableListOf<Float>()
    val yList = mutableListOf<Float>()
    val p2pAngleList = Array(6) { 30f + 60f * it }
    val p2pDis = sqrt(sideWith * sideWith - sideWith * sideWith / 4) * 2

    val anglist = Array(6) { 60f * it }
    with(xList) {
        add(centerX)
        addAll(Array(6) { pointX(p2pDis, centerX, p2pAngleList[it]) })
        addAll(Array(6) { pointX(p2pDis * 2, centerX, p2pAngleList[it]) })
        addAll(Array(6) { pointX(p2pDis * 3, centerX, p2pAngleList[it]) })
        addAll(Array(6) { pointX(sideWith * 3, centerX, anglist[it]) })
    }
    with(yList) {
        add(centerY)
        addAll(Array(6) { pointY(p2pDis, centerY, p2pAngleList[it]) })
        addAll(Array(6) { pointY(p2pDis * 2, centerY, p2pAngleList[it]) })
        addAll(Array(6) { pointY(p2pDis * 3, centerY, p2pAngleList[it]) })
        addAll(Array(6) { pointY(sideWith * 3, centerY, anglist[it]) })
    }
    val pathList = remember { mutableStateOf(mutableListOf<Path>()) }
    pathList.value.clear()
    for (pIndex in xList.indices) {
        val path = Path()
        path.moveTo(pointX(sideWith, xList[pIndex], anglist[0]), pointY(sideWith, yList[pIndex], anglist[0]))
        for (index in anglist.indices) {
            if (index < anglist.lastIndex) {
                path.lineTo(
                        pointX(sideWith, xList[pIndex], anglist[index + 1]),
                        pointY(sideWith, yList[pIndex], anglist[index + 1])
                )
            }
        }
        pathList.value.add(path)
    }
    val colorList = listOf(
            Color(0xffFF8723),
            Color(0xffFFBA23),
            Color(0xff00D16E),
            Color(0xff0055FF),
            Color(0xff43B988),
            Color(0xffFF1D1D),
            Color(0xff4E6FD7),
    )
    val transition = rememberInfiniteTransition()
    val pathIndex = transition.animateValue(
            0, colorList.size, Int.VectorConverter, animationSpec = InfiniteRepeatableSpec(
            tween(3000)
    )
    )
    val color = animateColorAsState(
            colorList[pathIndex.value], animationSpec = TweenSpec(
            1000
    )
    )
    val canvasAngle = transition.animateFloat(
            0f, 360f, animationSpec = InfiniteRepeatableSpec(
            tween(durationMillis = 3000)
    )
    )
    val canvasYAngle = transition.animateFloat(
            -30f, 30f, animationSpec = InfiniteRepeatableSpec(
            tween(durationMillis = 2000), repeatMode = RepeatMode.Reverse
    )
    )
    val canvasZAngle = transition.animateFloat(
            0f, 380f, animationSpec = InfiniteRepeatableSpec(
            tween(durationMillis = 3000), repeatMode = RepeatMode.Reverse
    )
    )
    Canvas(modifier = modifier.graphicsLayer(
            rotationX = canvasAngle.value,
            rotationY = canvasYAngle.value,
            rotationZ = canvasZAngle.value)
    ) {
        width.value = size.width
        height.value = size.height
        for (pp in pathList.value) {
            drawPath(pp, brush = Brush.verticalGradient(listOf(color.value, Color(0xfff2f2f2))))
        }
        for (index in xList.indices) {
            for (ang in anglist) {
                drawLine(
                        Color.White, strokeWidth = 3f,
                        start = Offset(
                                pointX(sideWith, xList[index], ang),
                                pointY(sideWith, yList[index], ang)
                        ),
                        end = Offset(
                                pointX(sideWith, xList[index], ang + 60f),
                                pointY(sideWith, yList[index], ang + 60f)
                        )
                )
            }
        }
    }
}