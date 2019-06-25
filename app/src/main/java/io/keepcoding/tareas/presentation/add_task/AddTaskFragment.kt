package io.keepcoding.tareas.presentation.add_task

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.keepcoding.tareas.R
import io.keepcoding.tareas.domain.model.Task
import io.keepcoding.util.extensions.consume
import io.keepcoding.util.extensions.observe
import kotlinx.android.synthetic.main.fragment_add_task.*
import org.koin.android.viewmodel.ext.android.viewModel

class AddTaskFragment : Fragment() {


    val addTaskViewModel: AddTaskViewModel by viewModel()
    var task: Task? = null


    override fun onAttach(context: Context?) {
        super.onAttach(context)

        arguments?.getSerializable("obj")?.let {
            task = it as Task
        }

    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindEvents()
        bindActions()
        loadData()
    }

    private fun loadData() {


        val arrayAdapter = ArrayAdapter.createFromResource(activity!!.applicationContext,R.array.priority_list, android.R.layout.simple_spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spPriority?.adapter = arrayAdapter


        when {
            task != null -> {
                taskContent.setText(task!!.content)
                taskDescription.setText(task!!.description)

                when {
                    task!!.isHighPriority -> spPriority.setSelection(0)
                    else -> spPriority.setSelection(1)
                }
            }
        }

    }





    private fun bindActions() {

        saveButton.setOnClickListener {


            val taskContent = taskContent.text.toString()
            val taskDescription = taskDescription.text.toString()
            val taskPriority = spPriority.selectedItem == "Alta"

            if(task == null) {
                addTaskViewModel.save(taskContent,taskDescription,taskPriority)

            }else {
                addTaskViewModel.update(task!!,taskContent,taskDescription,taskPriority)
            }
        }
    }

    private fun bindEvents() {
        with (addTaskViewModel) {
            observe(closeAction) {
                it.consume {
                    onClose()
                }
            }
        }
    }

    private fun onClose() {
        requireActivity().finish()
    }



}