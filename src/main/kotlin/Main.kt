import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App(appState: AppState) {
    MaterialTheme {
        Column {
            TextField(value = appState.text.value, onValueChange = { nextValue -> appState.text.value = nextValue })
            Text(text = appState.message)
            Button(onClick = { appState.text.value = "" }, enabled = appState.buttonEnabled) {
                Text("Clean")
            }
        }

    }
}

class AppState {
    var text = mutableStateOf("")
    val message
        get() = "Hello ${text.value}"

    val buttonEnabled
        get() = text.value.isNotEmpty()
}


fun main() {
    val appState = AppState()

    application {
        Window(onCloseRequest = ::exitApplication, title = "My Notes") {
            App(appState)
        }
    }
}
