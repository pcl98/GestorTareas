package es.viu.gestortareas.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.viu.gestortareas.ui.theme.GestorTareasTheme

/**
 * Componente reutilizable que muestra una barra de aplicaciones con un título.
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    title: String,
    showBackButton: Boolean = false,
    onClick: () -> Unit = {}) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver"
                    )
                }
            } else {
                // Espacio reservado para mantener el centrado del título
                Spacer(modifier = Modifier.width(130.dp))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CustomTopBarPreview() {
    GestorTareasTheme {
        CustomTopBar(title = "Mis Tareas")
    }
}