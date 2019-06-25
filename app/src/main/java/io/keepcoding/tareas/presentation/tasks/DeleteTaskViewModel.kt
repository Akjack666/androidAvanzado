package io.keepcoding.tareas.presentation.tasks

import androidx.lifecycle.MutableLiveData
import io.keepcoding.tareas.domain.TaskRepository
import io.keepcoding.tareas.domain.model.Task
import io.keepcoding.tareas.presentation.BaseViewModel
import io.keepcoding.util.DispatcherFactory
import io.keepcoding.util.Event
import io.keepcoding.util.extensions.call
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.Instant

class DeleteTaskViewModel(
        private val taskRepository: TaskRepository,
        private val dispatcherFactory: DispatcherFactory
) : BaseViewModel(dispatcherFactory) {

    val closeAction = MutableLiveData<Event<Unit>>()


    fun delete(task: Task) {

        launch {
            withContext(dispatcherFactory.getIO()) {
                taskRepository.deleteTask(task)
            }
            closeAction.call()
        }
    }


}