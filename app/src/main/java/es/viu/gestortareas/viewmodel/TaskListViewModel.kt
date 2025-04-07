package es.viu.gestortareas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.viu.gestortareas.data.TaskRepository
import es.viu.gestortareas.model.Task

class TaskListViewModel : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>(TaskRepository.getTasks())
    val tasks: LiveData<List<Task>> get() = _tasks
}
