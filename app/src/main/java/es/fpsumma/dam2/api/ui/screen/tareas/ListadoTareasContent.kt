package es.fpsumma.dam2.api.ui.screen.tareas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.fpsumma.dam2.api.model.Tarea

@Composable
fun ListadoTareasContent(
    ui: TareasUIState,
    onBack: () -> Unit,
    onAdd: () -> Unit,
    onOpenDetalle: (Int) -> Unit,
    onDelete: (Int) -> Unit
) {

    when {
        //
        ui.loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }


        ui.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = ui.error, color = MaterialTheme.colorScheme.error)
            }
        }


        ui.tareas.isEmpty() -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No hay tareas aún")
            }
        }


        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(ui.tareas) { tarea ->

                    TareaItem(
                        tarea = tarea,
                        onClick = { onOpenDetalle(tarea.id) },
                        onDelete = { onDelete(tarea.id) }
                    )
                }
            }
        }
    }
}

// este lo creo como auxiliar para no poner todo e codigo el el lazy
@Composable
fun TareaItem(
    tarea: Tarea,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() } // Al hacer click en la tarjeta
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = tarea.titulo) // O el campo que tengas en tu modelo Tarea

            IconButton(onClick = onDelete) { // Al hacer click en la papelera
                Icon(Icons.Default.Delete, contentDescription = "Borrar")
            }
        }
    }
}

