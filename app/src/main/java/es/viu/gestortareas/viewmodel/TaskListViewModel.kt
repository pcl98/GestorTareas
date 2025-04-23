package es.viu.gestortareas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.viu.gestortareas.data.TaskRepository
import es.viu.gestortareas.model.Task
import android.util.Log

class TaskListViewModel : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>(TaskRepository.getTasks())
    val tasks: LiveData<List<Task>> get() = _tasks


    fun addTask(task: Task) {
        TaskRepository.addTask(task)
        _tasks.value = TaskRepository.getTasks() // Actualizar la lista de tareas
    }


    // Eliminar tarea
    fun deleteTask(taskId: Int) {
        TaskRepository.deleteTask(taskId)
        _tasks.value = TaskRepository.getTasks() // Actualizar la lista de tareas
    }

    // Cambiar el estado de la tarea (completada/no completada)
    fun toggleTaskStatus(taskId: Int) {
        TaskRepository.toggleTaskStatus(taskId)
        _tasks.value = TaskRepository.getTasks()
    }


    fun getTaskById(taskId: Int): Task? {
        return TaskRepository.getTasks().find { it.id == taskId }
    }

    fun getTaskCompleted(): List<Task> {
        return TaskRepository.getTasks().filter { it.isCompleted }

    }
    fun editTask(taskId: Int, title: String, description: String, isCompleted: Boolean? = null) {
        val task = TaskRepository.getTasks().find { it.id == taskId }
        task?.let {
            it.title = title
            it.description = description
            isCompleted?.let { newStatus -> it.isCompleted = newStatus }
            TaskRepository.updateTask(it)
            _tasks.value = TaskRepository.getTasks()
        }
    }
}
