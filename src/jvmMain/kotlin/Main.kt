import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import loading.*
import widget.ElectricAnim
import java.awt.Toolkit
import java.security.KeyFactory
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import java.security.spec.X509EncodedKeySpec


var screenWidth = Toolkit.getDefaultToolkit().screenSize.width.dp
var screenHeight = Toolkit.getDefaultToolkit().screenSize.height.dp
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(
            size = DpSize(600.dp, 400.dp),
            position = WindowPosition(Alignment.Center)
        ),
    ) {
        Box(modifier = Modifier.fillMaxSize().background(color = Color.Black)) {
//            WaveProgress(modifier = Modifier.fillMaxSize())
            ElectricAnim(modifier = Modifier.fillMaxSize())

        }
    }
}
fun sort(names: Array<String>): Array<String> {
    return names.sortedWith(Comparator { a, b ->
        val aDigits = a.filter { it.isDigit() }
        val bDigits = b.filter { it.isDigit() }

        if (aDigits.isNotEmpty() && bDigits.isNotEmpty()) {
            val aInt = aDigits.toInt()
            val bInt = bDigits.toInt()

            if (aInt != bInt) {
                aInt - bInt
            } else {
                a.compareTo(b)
            }
        } else {
            a.compareTo(b)
        }
    }).toTypedArray()
}
//            val raw = listOf("file2.gif", "file01.gif",
//                    "1file.jpg",
//                    "1file.gif",
//                    "file10.gif",
//                    "file1.gif",
//                    "file1a.gif").toTypedArray()
//            val turnList = sort(raw)
//            turnList.forEach {
//                println(it)
//            }


