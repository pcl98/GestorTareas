package es.viu.gestortareas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import es.viu.gestortareas.ui.components.TaskCard

// Simulación de un modelo de datos
data class Task(val title: String, val description: String)

/**
 * Pantalla que muestra una lista de tareas utilizando un LazyColumn.
 * Reutiliza el componente TaskCard para cada tarea individual.
 */
@Composable
fun TaskListScreen(navController: NavController, taskList: List<Task>) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Mis Tareas",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(taskList) { task ->
                TaskCard(
                    task.title,
                    task.description,
                    onClick = {
                        navController.navigate("task_detail/$(task.title)")
                    }
                )
            }
        }
    }
}
