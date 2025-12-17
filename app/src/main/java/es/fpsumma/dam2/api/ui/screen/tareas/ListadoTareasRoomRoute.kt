package es.fpsumma.dam2.api.ui.screen.tareas



import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import es.fpsumma.dam2.api.ui.navegation.Routes
import es.fpsumma.dam2.api.viewmodel.TareasViewModel

// Asegúrate de importar tu ViewModel y tu objeto Routes
// import ... TareasViewModel
// import ... Routes

@Composable
fun ListadoTareasRoomRoute(
    navController: NavController,
    viewModel: TareasViewModel
) {
    //Hacemos el collect state
    // con esto convierto el Flow del vm en un estado que jet compose entiende
    val ui by viewModel.state.collectAsState()

    ListadoTareasContent(
        ui = ui,
        //volver atrás
        onBack = {
            navController.popBackStack()
        },
        //ir a añadir
        onAdd = {
            navController.navigate(Routes.TAREA_ADD)
        },
        //ir al detalle pasando el ID
        onOpenDetalle = { id ->
            navController.navigate(Routes.tareaView(id))
        },
        //borrar tarea usando el vm
        onDelete = { id ->
            viewModel.deleteTareaById(id)
        }
    )
}