package com.geisyanne.taskapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.geisyanne.taskapp.R
import com.geisyanne.taskapp.data.model.Status
import com.geisyanne.taskapp.data.model.Task
import com.geisyanne.taskapp.databinding.FragmentTodoBinding
import com.geisyanne.taskapp.ui.adapter.TaskAdapter
import com.geisyanne.taskapp.ui.adapter.TaskTopAdapter

class TodoFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskTopAdapter: TaskTopAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initRecyclerViewTask()
        getTasks()
    }

    private fun initListeners() {
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_formTaskFragment)
        }
    }

    private fun initRecyclerViewTask() {
        taskTopAdapter = TaskTopAdapter { task, option ->
            optionSelected(task, option)
        }

        taskAdapter = TaskAdapter { task, option ->
            optionSelected(task, option)
        }

        val concatAdapter = ConcatAdapter(taskTopAdapter, taskAdapter)

        with(binding.rvTask) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = concatAdapter
        }
    }

    private fun optionSelected(task: Task, option: Int) {
        when (option) {
            TaskAdapter.SELECT_REMOVE -> {
                Toast.makeText(
                    requireContext(),
                    "Removendo: ${task.description}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            TaskAdapter.SELECT_EDIT -> {
                Toast.makeText(
                    requireContext(),
                    "Editando: ${task.description}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            TaskAdapter.SELECT_DETAILS -> {
                Toast.makeText(
                    requireContext(),
                    "Detalhes: ${task.description}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            TaskAdapter.SELECT_NEXT -> {
                Toast.makeText(requireContext(), "Next: ${task.description}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun getTasks() {
        val taskTopList = listOf(
            Task("0", "Topo na lista", Status.TODO),

        )

        val taskList = listOf(
            Task("0", "Criar nova tela do app", Status.TODO),
            Task("1", "Validar informações na tela de login", Status.TODO),
            Task("2", "Adicionar nova funcionalidade no app", Status.TODO),
            Task("3", "Salvar token localmente", Status.TODO),
            Task("4", "Criar funcionalidade de logout no app", Status.TODO)
        )

        taskTopAdapter.submitList(taskTopList)
        taskAdapter.submitList(taskList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}