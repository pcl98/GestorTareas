package es.viu.gestortareas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import es.viu.gestortareas.ui.components.CustomTopBar
import es.viu.gestortareas.viewmodel.TaskListViewModel

@Composable
fun TaskDetailScreen(
    navController: NavHostController,
    taskId: Int,
    viewModel: TaskListViewModel = viewModel())
{
    val task = viewModel.getTaskById(taskId)

    Scaffold(
        topBar = { CustomTopBar("Detalle Tarea") }
    ) {
        padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            if (task != null) {
                Text(text = "Título:", style = MaterialTheme.typography.titleMedium)
                Text(text = task.title, style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(12.dp))

                Text(text = "Descripción:", style = MaterialTheme.typography.titleMedium)
                Text(text = task.description, style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    navController.navigate("task_form/${task.id}") // para editar
                }) {
                    Text("Editar")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    viewModel.deleteTask(task.id)
                    navController.popBackStack() // volver a pantalla anterior
                }) {
                    Text("Eliminar")
                }
            } else {
                Text(text = "Tarea no encontrada", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}