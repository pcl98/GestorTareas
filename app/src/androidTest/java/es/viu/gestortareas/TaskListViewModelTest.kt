package es.viu.gestortareas

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import es.viu.gestortareas.data.TaskRepository
import es.viu.gestortareas.model.Task
import es.viu.gestortareas.viewmodel.TaskListViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class  TaskListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        TaskRepository.clear()
    }

    @Test
    fun add_test() {
        // Dado un ViewModel vacío
        val viewModel = TaskListViewModel()

        // Cuando añado una nueva tarea
        val nuevaTarea = Task(
            id = 1,
            title = "Hacer ejercicio",
            description = "Estirar y hacer yoga"
        )
        viewModel.addTask(nuevaTarea)

        // Entonces, la tarea debe estar en la lista
        val listaTareas = viewModel.tasks.value ?: emptyList()
        assertTrue(listaTareas.contains(nuevaTarea))
    }

    @Test
    fun change_test() {
        val viewModel = TaskListViewModel()
        val tarea = Task(1, "Leer", "Leer 20 páginas")
        viewModel.addTask(tarea)

        // Cambiar el estado de la tarea
        viewModel.toggleTaskStatus(1)

        // Verificar que el estado de la tarea ha cambiado
        val tareaModificada = viewModel.tasks.value?.find { it.id == tarea.id }
        assertNotNull(tareaModificada) // Asegurarse de que la tarea está presente
        assertTrue(tareaModificada?.isCompleted == true) // Comprobar que el estado ha cambiado a true
    }
    @Test
    fun getTaskById_returnsCorrectTask() {
        val viewModel = TaskListViewModel()
        val tarea = Task(1, "Estudiar", "Repasar Kotlin", false)
        viewModel.addTask(tarea)

        val result = viewModel.getTaskById(1)
        assertNotNull(result)
        assertEquals("Estudiar", result?.title)
    }

    @Test
    fun getTaskCompleted_returnsOnlyCompletedTasks() {
        val viewModel = TaskListViewModel()
        viewModel.addTask(Task(1, "Dormir", "8 horas", false))
        viewModel.addTask(Task(2, "Comer", "Cenar bien", true))
        viewModel.addTask(Task(3, "Leer", "Un libro", true))

        val completadas = viewModel.getTaskCompleted()

        assertEquals(2, completadas.size)
        assertTrue(completadas.all { it.isCompleted })
    }
    @Test
    fun editTask_updatesTaskFieldsCorrectly() {
        val viewModel = TaskListViewModel()
        viewModel.addTask(Task(1, "Antiguo título", "Antigua descripción", false))

        viewModel.editTask(1, "Nuevo título", "Nueva descripción", true)

        val tareaEditada = viewModel.getTaskById(1)
        assertNotNull(tareaEditada)
        assertEquals("Nuevo título", tareaEditada?.title)
        assertEquals("Nueva descripción", tareaEditada?.description)
        assertTrue(tareaEditada?.isCompleted == true)
    }

}


