package io.keepcoding.tareas.presentation.tasks

import io.keepcoding.tareas.domain.model.Task

interface Listener {
    fun onDelete(task: Task)
    fun onClick(task: Task)
}