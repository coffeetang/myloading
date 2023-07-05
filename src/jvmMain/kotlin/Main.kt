import androidx.compose.animation.core.*
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(
            size = DpSize(600.dp, 600.dp),
            position = WindowPosition(Alignment.TopCenter)
        )
    )  {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                TwoArcLoading(modifier = Modifier.size(200.dp,200.dp))
                ThreeBallLoading(modifier = Modifier.size(200.dp,200.dp))
                TwoBallLoading(modifier = Modifier.size(200.dp,200.dp))
            }
            Row(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                HeartbeatLoading(modifier = Modifier.size(200.dp,200.dp))
                ScrewLoading(modifier = Modifier.size(200.dp,200.dp))
                RadiationLoading(modifier = Modifier.size(200.dp,200.dp))
            }
            Row(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                RecPointLoading(modifier = Modifier.size(200.dp,200.dp))
                JumpPointLoading(modifier = Modifier.size(200.dp,200.dp))
                RainbowLoading(modifier = Modifier.size(200.dp,200.dp))
            }
        }
    }
}
