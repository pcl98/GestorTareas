package es.viu.gestortareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.viu.gestortareas.ui.screens.TaskListScreen
import es.viu.gestortareas.ui.screens.TaskFormScreen
import es.viu.gestortareas.ui.screens.TaskDetailScreen
import es.viu.gestortareas.ui.theme.GestorTareasTheme
import es.viu.gestortareas.viewmodel.TaskListViewModel

/**
 *
 * Clase principal de la aplicación Gestor de Tareas.
 * Es la actividad que inicializa el tema de la aplicación y gestiona la navegación entre pantallas
 * utilizando Jetpack Compose y Navigation Compose.
 *
 * La navegación parte de la pantalla "TaskListScreen" que muestra la lista de tareas.
 * Desde ahí se puede acceder al formulario de creación/edición de tareas ("TaskFormScreen")
 * y al detalle de cada tarea ("TaskDetailScreen").
 *
 */
class MainActivity : ComponentActivity() {

    private val taskListViewModel: TaskListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestorTareasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "task_list") {
                        composable("task_list") {
                            TaskListScreen(navController, taskListViewModel)
                        }
                        composable("task_form/{taskId}") { backStackEntry ->
                            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull() ?: -1
                            TaskFormScreen(navController, taskListViewModel, taskId)
                        }
                        composable("task_detail/{taskId}") { backStackEntry ->
                            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull() ?: 0
                            TaskDetailScreen(navController, taskId, taskListViewModel)
                        }
                    }
                }
            }
        }
    }
}
