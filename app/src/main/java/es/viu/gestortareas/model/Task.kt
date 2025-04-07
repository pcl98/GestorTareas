package es.viu.gestortareas.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val isDone: Boolean = false
)