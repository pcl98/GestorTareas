package es.viu.gestortareas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import es.viu.gestortareas.ui.components.CustomTopBar
import es.viu.gestortareas.ui.components.TaskCard
import es.viu.gestortareas.viewmodel.TaskListViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue

/**
 * Pantalla que muestra una lista de tareas utilizando un LazyColumn.
 * Reutiliza el componente TaskCard para cada tarea individual.
 */
@Composable
fun TaskListScreen(
    navController: NavController,
    /*taskList: List<Task>,*/
    viewModel: TaskListViewModel = viewModel()
) {
    val taskList by viewModel.tasks.observeAsState(initial = emptyList())

    Scaffold(
        topBar = { CustomTopBar("Mis Tareas") },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("task_form")}) {
                Icon(Icons.Default.Add, "Añadir Tarea")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(taskList) { task ->
                TaskCard(
                    task.title,
                    task.description,
                    onClick = {
                        navController.navigate("task_detail/${task.id}")
                    }
                )
            }
        }
    }
}
