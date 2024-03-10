import Note.Type

data class Note(
    val title: String,
    val description: String = "",
    val type: Type = Type.TEXT
) {
    enum class Type { TEXT, AUDIO }
}

fun getNotes(callback: (List<Note>) -> Unit) {
    Thread.sleep(2000)

    val notes = (0..10).map {
        Note(
            title = "Title $it",
            description = "Description $it",
            type = if (it % 3 == 0) Type.AUDIO else Type.TEXT
        )
    }
    callback(notes)
}
