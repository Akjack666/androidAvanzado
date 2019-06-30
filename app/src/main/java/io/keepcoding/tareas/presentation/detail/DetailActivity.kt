package io.keepcoding.tareas.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import io.keepcoding.tareas.R
import io.keepcoding.tareas.domain.model.Task
import io.keepcoding.tareas.presentation.add_task.AddTaskViewModel
import io.keepcoding.util.extensions.observe
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import java.util.*

class DetailActivity : AppCompatActivity() {

    var task: Task? = null
    val getTaskViewModel: GetTaskViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        loadExtra()
        loadData()
    }

    fun loadExtra() {

        task = intent.getSerializableExtra("obj") as Task
       /* val id : Long = intent.getLongExtra("id",0)
        task = getTaskViewModel.getTask(id) as Task*/


    }



    fun loadData() {

        val formatter : DateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(
                Locale.UK
        ).withZone(ZoneId.systemDefault())

        if(task != null) {

            tvTaskDateDetail.text = formatter.format(task!!.createdAt).toString()
            tvTaskDescriptionDetail.text = task!!.description

            if(task!!.isFinished) {

                tvTaskStateDetail.setBackgroundColor(ContextCompat.getColor(this, R.color.color_Complete))
                tvTaskStateDetail.text = getString(R.string.finished)

            }else {

                tvTaskStateDetail.setBackgroundColor(ContextCompat.getColor(this, R.color.color_no_Complete))
                tvTaskStateDetail.text = getString(R.string.pending)

            }

            tvTaskTitleDetail.text = task!!.content

            if(task!!.isHighPriority) {

                tvTaskPriorityDetail.text = getString(R.string.hight_priority)
            }else {

                tvTaskPriorityDetail.text = getString(R.string.low_priority)
            }
        }

    }


}
