import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import loading.*


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(
            size = DpSize(800.dp, 800.dp),
            position = WindowPosition(Alignment.TopCenter)
        )
    )  {
        Column(modifier = Modifier.fillMaxSize().background(Color.Black)) {
            Row(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                PointLoading(modifier = Modifier.width(200.dp).fillMaxHeight())
                RingLoading(modifier = Modifier.width(200.dp).fillMaxHeight())
                TailLoading(modifier = Modifier.width(200.dp).fillMaxHeight())
                WindCarLoading(200.dp,200.dp)
            }
            Row(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                TwoArcLoading(modifier = Modifier.width(200.dp).fillMaxHeight())
                ThreeBallLoading(modifier = Modifier.width(200.dp).fillMaxHeight())
                TwoBallLoading(modifier = Modifier.width(200.dp).fillMaxHeight())
                HeartbeatLoading(modifier = Modifier.width(200.dp).fillMaxHeight())
            }
            Row(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                ScrewLoading(modifier = Modifier.width(200.dp).fillMaxHeight())
                RadiationLoading(modifier = Modifier.width(200.dp).fillMaxHeight())
                RecPointLoading(modifier = Modifier.width(200.dp).fillMaxHeight())
                JumpPointLoading(modifier = Modifier.width(200.dp).fillMaxHeight())
            }
            Row(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                RainbowLoading(modifier = Modifier.width(200.dp).fillMaxHeight())
                FlowerLoading(modifier = Modifier.width(200.dp).fillMaxHeight())
                BeehiveLoading(modifier = Modifier.width(200.dp).fillMaxHeight())
                JiheLoading(modifier = Modifier.width(200.dp).fillMaxHeight())
            }
        }
    }
}
