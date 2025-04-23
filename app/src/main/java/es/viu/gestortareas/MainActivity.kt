package es.viu.gestortareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import es.viu.gestortareas.model.Task
import es.viu.gestortareas.viewmodel.TaskListViewModel
import es.viu.gestortareas.ui.theme.GestorTareasTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private val taskListViewModel: TaskListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestorTareasTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Gestor de Tareas") }
                        )
                    }
                ) { innerPadding ->
                    TaskListScreen(
                        viewModel = taskListViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TaskListScreen(viewModel: TaskListViewModel, modifier: Modifier = Modifier) {
    val tasks by viewModel.tasks.observeAsState(emptyList())

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Lista de Tareas:", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        tasks.forEach { task ->
            TaskCard(
                title = task.title,
                description = task.description,
                isCompleted = task.isCompleted,
                onToggle = { viewModel.toggleTaskStatus(task.id) },
                onDelete = { viewModel.deleteTask(task.id) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            val tarea = Task(1, "Leer", "Leer 20 páginas")
            viewModel.addTask(tarea)
        }) {
            Text("Añadir tarea")
        }
    }
}

@Composable
fun TaskCard(
    title: String,
    description: String,
    isCompleted: Boolean,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = onToggle) {
                    Text(if (isCompleted) "Marcar como pendiente" else "Marcar como hecha")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onDelete) {
                    Text("Eliminar")
                }
            }
        }
    }
}







