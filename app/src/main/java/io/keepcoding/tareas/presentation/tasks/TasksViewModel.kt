package io.keepcoding.tareas.presentation.tasks

import androidx.lifecycle.MutableLiveData
import io.keepcoding.tareas.domain.TaskRepository
import io.keepcoding.tareas.domain.model.Task
import io.keepcoding.tareas.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TasksViewModel(
    private val taskRepository: TaskRepository
): BaseViewModel() {

    val tasksState = MutableLiveData<List<Task>>()
    val isLoadingState = MutableLiveData<Boolean>()

    init {
        loadTasks()
    }

    fun loadTasks() {
        launch {
            showLoading(true)

            val result = withContext(Dispatchers.IO) { taskRepository.getAll() }
            tasksState.value = result

            showLoading(false)
        }
    }

    fun toggleFinished(task: Task) {
        val newTask = task.copy(isFinished = !task.isFinished)

        launch(Dispatchers.IO) {
            taskRepository.updateTask(newTask)
            loadTasks()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        isLoadingState.value = isLoading
    }

}