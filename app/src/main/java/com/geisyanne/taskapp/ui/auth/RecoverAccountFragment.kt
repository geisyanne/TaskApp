package com.geisyanne.taskapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.geisyanne.taskapp.R
import com.geisyanne.taskapp.databinding.FragmentRecoverAccountBinding
import com.geisyanne.taskapp.ui.BaseFragment
import com.geisyanne.taskapp.util.FirebaseHelper
import com.geisyanne.taskapp.util.initToolbar
import com.geisyanne.taskapp.util.showBottomSheet

class RecoverAccountFragment : BaseFragment() {

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
            hideKeyboard()
            binding.progressRecover.isVisible = true
            recoverAccountUser(email)
        } else {
            showBottomSheet(message = getString(R.string.enter_email_valid))
        }
    }

    private fun recoverAccountUser(email: String) {
        FirebaseHelper.getAuth().sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                binding.progressRecover.isVisible = false

                if (task.isSuccessful) {
                    showBottomSheet(
                        message = getString(R.string.msg_recover_account),
                    )
                } else {
                    showBottomSheet(
                        message = getString(FirebaseHelper.validError(task.exception?.message.toString()))
                    )
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}