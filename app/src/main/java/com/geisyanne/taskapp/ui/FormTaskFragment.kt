package com.geisyanne.taskapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.geisyanne.taskapp.R
import com.geisyanne.taskapp.databinding.FragmentFormTaskBinding
import com.geisyanne.taskapp.util.initToolbar
import com.geisyanne.taskapp.util.showBottomSheet

class FormTaskFragment : Fragment() {

    private var _binding: FragmentFormTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormTaskBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(binding.toolbarNewTask)
        initListeners()

    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val description = binding.edtDescription.text.toString().trim()

        if (description.isNotEmpty()) {
            Toast.makeText(requireContext(), "TUDO OK", Toast.LENGTH_SHORT).show()
        } else {
            showBottomSheet(message = getString(R.string.enter_description))
        }
    }

}