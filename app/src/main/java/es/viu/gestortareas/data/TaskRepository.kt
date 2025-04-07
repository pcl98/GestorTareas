package es.viu.gestortareas.data

import es.viu.gestortareas.model.Task

object TaskRepository {
    private val tasks = mutableListOf(
        Task(1, "Comprar pan", "Ir a la panadería antes de las 10"),
        Task(2, "Estudiar", "Repasar patrón MVVM para la práctica"),
        Task(3, "Entrenar", "Hacer 30 minutos de ejercicio en casa")
    )

    fun getTasks(): List<Task> = tasks
}