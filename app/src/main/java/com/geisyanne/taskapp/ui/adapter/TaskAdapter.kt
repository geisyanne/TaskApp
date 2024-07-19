package com.geisyanne.taskapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geisyanne.taskapp.data.model.Status
import com.geisyanne.taskapp.data.model.Task
import com.geisyanne.taskapp.databinding.ItemTaskBinding

class TaskAdapter(
    private val taskSelected: (Task, Int) -> Unit
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(DIFF_CALLBACK) {

    // EVENTOS DE CLICK
    companion object {
        val SELECT_BACK: Int = 1
        val SELECT_REMOVE: Int = 2
        val SELECT_EDIT: Int = 3
        val SELECT_DETAILS: Int = 4
        val SELECT_NEXT: Int = 5

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id && oldItem.description == newItem.description
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem == newItem && oldItem.description == newItem.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)

        holder.binding.txtDescriptionTask.text = task.description

        setIndicators(task, holder)

        holder.binding.btnRemoveTask.setOnClickListener { taskSelected(task, SELECT_REMOVE) }
        holder.binding.btnEditTask.setOnClickListener { taskSelected(task, SELECT_EDIT) }
        holder.binding.btnDetailsTask.setOnClickListener { taskSelected(task, SELECT_DETAILS) }
    }

    private fun setIndicators(task: Task, holder: TaskViewHolder) {
        when (task.status) {
            Status.TODO -> {
                holder.binding.btnBackTask.isVisible = false
                holder.binding.btnNextTask.setOnClickListener { taskSelected(task, SELECT_NEXT) }
            }

            Status.DOING -> {
                holder.binding.btnBackTask.setOnClickListener { taskSelected(task, SELECT_BACK) }
                holder.binding.btnNextTask.setOnClickListener { taskSelected(task, SELECT_NEXT) }
            }

            Status.DONE -> {
                holder.binding.btnNextTask.isVisible = false
                holder.binding.btnBackTask.setOnClickListener { taskSelected(task, SELECT_BACK) }
            }
        }
    }

    inner class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

}