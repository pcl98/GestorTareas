package es.viu.gestortareas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import es.viu.gestortareas.ui.components.CustomTopBar

@Composable
fun TaskDetailScreen(navController: NavHostController, taskId: Int) {
    Scaffold(
        topBar = { CustomTopBar("Detalle Tarea") }
    ) {
        padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(padding))
        {
            Text(text = "Título: Ejemplo de título", modifier = Modifier.padding(top = 8.dp))
            Text(text = "Descripción: Ejemplo de descripción", modifier = Modifier.padding(4.dp))
        }
    }
}