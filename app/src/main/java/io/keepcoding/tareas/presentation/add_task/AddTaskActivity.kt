package io.keepcoding.tareas.presentation.add_task

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import io.keepcoding.tareas.R
import io.keepcoding.tareas.domain.model.Task
import kotlinx.android.synthetic.main.activity_main.*

class AddTaskActivity : AppCompatActivity() {

    val destiny = "create"
    var task: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        setUpToolbar()
        loadExtras()
        setUpFragment(savedInstanceState)
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar as Toolbar)
        setTitle(R.string.add_task_title)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
    }

    private fun setUpFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {

            val destiny = intent.getStringExtra("destiny")
                    if(destiny=="create") {
                        supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.fragmentContainer, AddTaskFragment())
                                .commit()
                    }else {

                        val fragment: AddTaskFragment = AddTaskFragment()
                        val args = Bundle()
                        args.putSerializable("obj",task)
                        fragment.arguments = args
                        supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.fragmentContainer, fragment)
                                .commit()
                    }

        }
    }

    private fun loadExtras() {


        val destiny = intent.getStringExtra("destiny")

        if(destiny == "edit") {
            setTitle(getString(R.string.edit))
            task = intent.getSerializableExtra("obj") as Task
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}