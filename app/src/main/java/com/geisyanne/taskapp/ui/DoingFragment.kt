package com.geisyanne.taskapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.geisyanne.taskapp.data.model.Status
import com.geisyanne.taskapp.data.model.Task
import com.geisyanne.taskapp.databinding.FragmentDoingBinding
import com.geisyanne.taskapp.ui.adapter.TaskAdapter

class DoingFragment : Fragment() {

    private var _binding: FragmentDoingBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViewTask()
        getTasks()
    }

    private fun initRecyclerViewTask() {
        taskAdapter = TaskAdapter { task, option ->
            optionSelected(task, option)
        }

        with(binding.rvTask) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    private fun optionSelected(task: Task, option: Int) {
        when (option) {
            TaskAdapter.SELECT_BACK -> {
                Toast.makeText(
                    requireContext(),
                    "Voltando: ${task.description}",
                    Toast.LENGTH_SHORT
                ).show()
            }

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
        val taskList = listOf(
            Task("0", "Validar informações na tela de cadastro", Status.DOING),
            Task("1", "Salvar foto do usuário no bd", Status.DOING),
            Task("2", "Ajustar tela de produtos do app", Status.DOING),
            Task("3", "Permitir remover produtos", Status.DOING),
            Task("4", "Criar opção de upload de imagem", Status.DOING)
        )
        taskAdapter.submitList(taskList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}