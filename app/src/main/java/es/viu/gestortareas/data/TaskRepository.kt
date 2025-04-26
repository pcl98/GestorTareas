package es.viu.gestortareas.data

import es.viu.gestortareas.model.Task

object TaskRepository {

    private val taskList = mutableListOf<Task>(
        Task(id = 1, title = "Comprar el pan", description = "Pablo compra el pan", isCompleted = false),
        Task(id = 2, title = "Fregar el suelo", description = "Jesús friega el suelo", isCompleted = true),
        Task(id = 3, title = "Sacar al perro", description = "Jesús saca a pasear a Rocky", isCompleted = false),
        Task(id = 4, title = "Hacer la compra", description = "Comprar frutas y verduras", isCompleted = false),
        Task(id = 5, title = "Estudiar Kotlin", description = "Pablo estudia programación", isCompleted = true)
    )

    // Obtener todas las tareas
    fun getTasks(): List<Task> {
        return taskList
    }

    // Añadir una nueva tarea
    fun addTask(task: Task) {
        taskList.add(task)
    }

    // Eliminar una tarea
    fun deleteTask(taskId: Int) {
        taskList.removeAll { it.id == taskId }
    }

    // Cambiar el estado de una tarea
    fun toggleTaskStatus(taskId: Int) {
        val task = taskList.find { it.id == taskId }
        task?.let {
            it.isCompleted = !it.isCompleted
        }
    }
    fun updateTask(updatedTask: Task) {
        val index = taskList.indexOfFirst { it.id == updatedTask.id }
        if (index != -1) {
            taskList[index] = updatedTask
        }
    }
    fun clear() {
        taskList.clear()
    }
}
