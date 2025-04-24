package es.viu.gestortareas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import es.viu.gestortareas.model.Task
import es.viu.gestortareas.ui.components.CustomTopBar
import es.viu.gestortareas.ui.components.CustomOutlinedTextField
import es.viu.gestortareas.viewmodel.TaskListViewModel

/**
 *
 * Pantalla para crear o editar una tarea. Contiene campos de texto y botón de guardar.
 *
 */
@Composable
fun TaskFormScreen(
    navController: NavController,
    viewModel: TaskListViewModel = viewModel()) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        topBar = { CustomTopBar("Nueva Tarea") }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(padding)
        ) {
            CustomOutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = "Título",
                modifier = Modifier.fillMaxWidth()
            )
            CustomOutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = "Descripción",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            Button(
                onClick = {
                    if (title.isNotBlank() && description.isNotBlank()) {
                        val id = viewModel.getLastTaskId() + 1
                        val task = Task(id, title, description)
                        viewModel.addTask(task)  // Añadir la tarea
                        navController.popBackStack()  // Volver a la lista de tareas
                    }
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                Text("Guardar")
            }
        }
    }

}