package widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

@Composable
fun Raining() {
    var width by remember { mutableStateOf(800f) }
    var height by remember { mutableStateOf(800f) }
    val top = 50f
    var lengthRange = 12..35
    var widthRange = 1..5
    val pList = remember { mutableStateListOf<Rain>() }
    for (rain in pList) {
        rain.update()
    }

    LaunchedEffect(pList.size) {
        flow {
            delay(10)
            emit(1)
        }.collect {
            pList.add(
                Rain(
                    x = (Math.random() * width).toFloat(),
                    y = top,
                    rainLength = lengthRange.random().toFloat(),
                    rainWidth = widthRange.random().toFloat()
                )
            )
        }
    }
    Canvas(modifier = Modifier.fillMaxSize()) {
        width = size.width
        height = size.height
        pList.forEach {
            drawLine(
                brush = Brush.verticalGradient(listOf(Color.Transparent,Color.White)),
                start = Offset(it.x, it.y),
                end = Offset(it.x, it.y + it.rainLength),
                strokeWidth = it.rainWidth,
                cap = StrokeCap.Round,
                alpha = it.alpha
            )
        }
    }
}

class Rain(
    var x: Float,
    var y: Float,
    var rainLength: Float,
    var rainWidth: Float,
    var alpha:Float = 1f,
) {
    fun update() {
        y += 10f
        alpha -= 0.008f
        if(alpha<0f){
            alpha = 0f
        }
    }
    fun updateAlpha(){

    }
}