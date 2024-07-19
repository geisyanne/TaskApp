package com.geisyanne.taskapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.geisyanne.taskapp.R
import com.geisyanne.taskapp.databinding.FragmentRecoverAccountBinding
import com.geisyanne.taskapp.util.initToolbar
import com.geisyanne.taskapp.util.showBottomSheet

class RecoverAccountFragment : Fragment() {

    private var _binding: FragmentRecoverAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecoverAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(binding.toolbarRecover)
        initListeners()
    }

    private fun initListeners() {
        binding.btnRecover.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val email = binding.edtEmailRecover.text.toString().trim()

        if (email.isNotEmpty()) {
            Toast.makeText(requireContext(), "Tudo OK", Toast.LENGTH_SHORT).show()
        } else {
            showBottomSheet(message = getString(R.string.enter_email_valid))

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}