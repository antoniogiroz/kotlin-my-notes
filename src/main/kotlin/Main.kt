import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlin.concurrent.thread

@Composable
@Preview
fun App(appState: AppState) {
    val notes = appState.notes.value

    MaterialTheme {
        Box(contentAlignment = Alignment.Center) {
            if (appState.loading.value) {
                CircularProgressIndicator()
            }
            NotesList(notes)
        }
    }
}

@Composable
private fun NotesList(notes: List<Note>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(notes) { note ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(.8f)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row {
                        Text(
                            text = note.title,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.weight(1f)
                        )
                        if (note.type == Note.Type.AUDIO) {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = null
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = note.description
                    )
                }
            }
        }
    }
}

class AppState {
    var notes = mutableStateOf(emptyList<Note>())
    var loading = mutableStateOf(false)

    fun loadNotes() {
        thread {
            loading.value = true
            getNotes() {
                notes.value = it
                loading.value = false
            }
        }
    }
}


fun main() {
    val appState = AppState()

    appState.loadNotes()

    application {
        Window(onCloseRequest = ::exitApplication, title = "My Notes") {
            App(appState)
        }
    }
}
