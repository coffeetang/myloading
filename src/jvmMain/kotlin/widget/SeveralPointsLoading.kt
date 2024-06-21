package widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.math.ln

@Composable
fun Bubbles(modifier: Modifier) {
    var width by remember { mutableStateOf(800f) }
    var height by remember { mutableStateOf(800f) }
    val wList = remember { mutableStateListOf<Bubble>() }
    val widthRange = width.toInt() *3/ 8..width.toInt() * 5 / 8
//    val widthRange = 0..width.toInt()
//    val heightRange = height.toInt() * 5 / 6..height.toInt()
    val heightRange = 0..height.toInt()
    val bubbleRadius = 50..100
    val shadowOffset = 0..8
    val startAngleRange = 0..360
    val dis = -6..6
    val colorList = listOf(
        Color(0xffFF1D1D),
        Color(0xffFF8723),
        Color(0xff00D16E),
        Color(0xff39E7FF),
    )
    wList.forEach {
        it.update()
    }
    LaunchedEffect(wList.size) {
        flow {
            delay(20)
            emit(1)
        }.collect {
            wList.add(
                Bubble(
                    x = widthRange.random().toFloat(),
                    y = heightRange.random().toFloat(),
                    shadowOffsetx = shadowOffset.random().toFloat(),
                    shadowOffsety = shadowOffset.random().toFloat(),
                    color = colorList.random(),
                    maxRadius = bubbleRadius.random().toFloat(),
                    startAngle = startAngleRange.random().toFloat(),
                    xDis = dis.random().toFloat(),
                )
            )
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        width = size.width
        height = size.height
        for (wIndex in wList.indices) {
            if (!wList[wIndex].delete) {
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(
                            Color.Transparent, Color.Transparent, Color.Transparent, Color.Transparent,
                            wList[wIndex].color,
                        ),
                        center = Offset(
                            wList[wIndex].x + wList[wIndex].shadowOffsetx,
                            wList[wIndex].y + wList[wIndex].shadowOffsety
                        ),
                        radius = wList[wIndex].radius + wList[wIndex].radius / 10
                    ),
                    wList[wIndex].radius,
                    center = Offset(wList[wIndex].x, wList[wIndex].y),
                )
                drawArc(
                    color = wList[wIndex].color,
                    startAngle = wList[wIndex].startAngle,
                    sweepAngle = 50f,
                    useCenter = false,
                    topLeft = Offset(
                        wList[wIndex].x - wList[wIndex].radius * 2 / 3,
                        wList[wIndex].y - wList[wIndex].radius * 2 / 3
                    ),
                    size = Size(wList[wIndex].radius * 4 / 3, wList[wIndex].radius * 4 / 3),
                    style = Stroke(2f),
                )
            }
        }
    }
}

class Bubble(
    var x: Float,
    var y: Float,
    val shadowOffsetx: Float = 0f,
    val shadowOffsety: Float = 0f,
    val color: Color,
    var radius: Float = 10f,
    var maxRadius: Float = 30f,
    val startAngle: Float = 90f,
    var life: Float = 1f,
    var delete: Boolean = false,
    var xDis: Float = 1f,
) {
    fun update() {
        x += xDis
        y -= ln(x)
        radius += 2f
        if (radius > maxRadius) {
            radius = maxRadius
            life -= 0.02f
            if (life < 0) {
                delete = true
            }
        }
    }
}


