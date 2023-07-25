package loading

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun LineLoading(modifier: Modifier) {
    val width = remember { mutableStateOf(800f) }
    val height = remember { mutableStateOf(800f) }
    val centerX = width.value / 2
    val centerY = height.value / 2
    val colorList = listOf(
            Color(0xff00D16E),
            Color(0xff0055FF),
            Color(0xff43B988),
            Color(0xffFF1D1D),
    )
    val transition = rememberInfiniteTransition()
    val count = 30 * 3
    val unitAngle = 360f / count
    val baseRadius = 50f

    val angleDiff = transition.animateFloat(60f, 300f, animationSpec = InfiniteRepeatableSpec(
            tween(5000), repeatMode = RepeatMode.Reverse
    ))

    val xList = Array(count) { pointX(baseRadius, centerX, it * unitAngle) }
    val yList = Array(count) { pointY(baseRadius, centerY, it * unitAngle) }
    val xList2 = Array(count) {
        pointX(baseRadius + 200f, centerX,
                it * unitAngle + angleDiff.value / 2)
    }

    val yList2 = Array(count) {
        pointY(baseRadius + 200f, centerY,
                it * unitAngle + angleDiff.value * 2)
    }

    val xList3 = Array(count) {
        pointX(baseRadius + 110f, centerX,
                it * unitAngle + angleDiff.value / 3)
    }

    val yList3 = Array(count) {
        pointY(baseRadius + 110f, centerY,
                it * unitAngle + angleDiff.value * 3)
    }

    val xList4 = Array(count) {
        pointX(baseRadius + 100f, centerX,
                it * unitAngle + angleDiff.value * 4)
    }

    val yList4 = Array(count) {
        pointY(baseRadius + 100f, centerY,
                it * unitAngle + angleDiff.value / 4)
    }
    val xList5 = Array(count) {
        pointX(baseRadius + 150f, centerX,
                it * unitAngle + angleDiff.value * 5)
    }

    val yList5 = Array(count) {
        pointY(baseRadius + 150f, centerY,
                it * unitAngle + angleDiff.value / 5)
    }
    val xList6 = Array(count) {
        pointX(baseRadius + 180f, centerX,
                it * unitAngle + angleDiff.value * 6)
    }

    val yList6 = Array(count) {
        pointY(baseRadius + 180f, centerY,
                it * unitAngle + angleDiff.value / 6)
    }
    val xList7 = Array(count) {
        pointX(baseRadius + 120f, centerX,
                it * unitAngle + angleDiff.value * 7)
    }

    val yList7 = Array(count) {
        pointY(baseRadius + 120f, centerY,
                it * unitAngle + angleDiff.value / 7)
    }

    val pathList = Array(count) {
        val path = Path()
        path.moveTo(xList[it], yList[it])
        path.cubicTo(xList2[it], yList2[it], xList3[it], yList3[it], xList4[it], yList4[it])
        path.cubicTo(xList5[it], yList5[it], xList6[it], yList6[it], xList7[it], yList7[it])
        path
    }

    Canvas(modifier = modifier) {
        width.value = size.width
        height.value = size.height
        for (pa in pathList) {
            drawPath(path = pa, brush = Brush.verticalGradient(colorList),
                    style = Stroke(
                            width = 5f,
                            cap = StrokeCap.Round,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f))
                    ),
                    blendMode = BlendMode.Screen)
        }

    }
}

@Composable
fun JiheLoading(modifier: Modifier) {
    val width = remember { mutableStateOf(800f) }
    val height = remember { mutableStateOf(800f) }
    val centerX = width.value / 2
    val centerY = height.value / 2
    val count = 30*3
    val unitAngle = 360f / count
    val baseRadius = 10f
    val transition = rememberInfiniteTransition()
    val angleDiff = transition.animateFloat(60f, 300f, animationSpec = InfiniteRepeatableSpec(
            tween(5000), repeatMode = RepeatMode.Reverse
    ))

    val xList = Array(count) { pointX(baseRadius, centerX, it * unitAngle) }
    val yList = Array(count) { pointY(baseRadius, centerY, it * unitAngle) }
    val xList1 = Array(count) { pointX(baseRadius + 200f, centerX,
                it * unitAngle + angleDiff.value / 2) }
    val yList1 = Array(count) { pointY(baseRadius + 200f, centerY,
                it * unitAngle + angleDiff.value * 2) }
    val xList2 = Array(count) { pointX(baseRadius + 110f, centerX,
                it * unitAngle + angleDiff.value / 3) }
    val yList2 = Array(count) { pointY(baseRadius + 110f, centerY,
                it * unitAngle + angleDiff.value * 3) }
    val xList3 = Array(count) { pointX(baseRadius + 100f, centerX,
                it * unitAngle + angleDiff.value / 4) }
    val yList3 = Array(count) { pointY(baseRadius + 100f, centerY,
                it * unitAngle + angleDiff.value / 4) }
    val xList4 = Array(count) { pointX(baseRadius + 150f, centerX,
                it * unitAngle + angleDiff.value * 5) }
    val yList4 = Array(count) { pointY(baseRadius + 150f, centerY,
                it * unitAngle + angleDiff.value / 5) }
    val xList5 = Array(count) { pointX(baseRadius + 180f, centerX,
                it * unitAngle + angleDiff.value * 6) }
    val yList5 = Array(count) { pointY(baseRadius + 180f, centerY,
                it * unitAngle + angleDiff.value / 6) }
    val xList6 = Array(count) { pointX(baseRadius + 120f, centerX,
                it * unitAngle + angleDiff.value * 7) }
    val yList6 = Array(count) { pointY(baseRadius + 120f, centerY,
                it * unitAngle + angleDiff.value / 7) }
    val pathList = Array(count) {
        val path = Path()
        path.moveTo(xList[it], yList[it])
        path.cubicTo(xList1[it], yList1[it], xList2[it], yList2[it], xList3[it], yList3[it])
        path.cubicTo(xList4[it], yList4[it], xList5[it], yList5[it], xList6[it], yList6[it])
        path
    }
    val colorList = listOf(
            Color(0xff00D16E),
            Color(0xff0055FF),
            Color(0xff43B988),
            Color(0xffFF1D1D),
    )
    Canvas(modifier = modifier) {
        width.value = size.width
        height.value = size.height
        for (pa in pathList) {
            drawPath(path = pa, brush = Brush.verticalGradient(colorList),
                    style = Stroke(width = 5f, cap = StrokeCap.Round,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f))
                    ), blendMode = BlendMode.Plus)
        }
    }
}