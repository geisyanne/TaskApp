package com.geisyanne.taskapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.geisyanne.taskapp.R
import com.geisyanne.taskapp.databinding.FragmentRegisterBinding
import com.geisyanne.taskapp.util.initToolbar
import com.geisyanne.taskapp.util.showBottomSheet

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(binding.toolbarRegister)
        initListeners()

    }

    private fun initListeners() {
        binding.btnRegister.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val email = binding.edtEmailRegister.text.toString().trim()
        val password = binding.edtPasswordRegister.text.toString().trim()

        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {
                Toast.makeText(requireContext(), "Tudo OK", Toast.LENGTH_SHORT).show()
            } else {
                showBottomSheet(message = getString(R.string.enter_password_register))
            }
        } else {
            showBottomSheet(message = getString(R.string.enter_email_valid))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}