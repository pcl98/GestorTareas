package es.viu.gestortareas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * TaskCard.kt
 *
 * Componente reutilizable que representa visualmente una tarjeta de tarea.
 * Este componente muestra de forma resumida la información principal de una tarea,
 * como el título y la descripción, y permite su reutilización en diferentes pantallas
 * como la lista de tareas o vistas de resumen.
 *
 */
@Composable
fun TaskCard(taskTitle: String, taskDescription: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column (modifier = Modifier.padding(16.dp)){
            Text(text = taskTitle, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = taskDescription, style = MaterialTheme.typography.bodyMedium)
        }
    }
}