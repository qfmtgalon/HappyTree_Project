package com.example.happytree

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.happytree.database.*
import com.example.happytree.databinding.FragmentRegistrationBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class Registration : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        binding.btnLoginText.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_registration_to_login)
        }

        viewModel = ViewModelProvider(
            this,
            AuthViewModelFactory(
                AuthDatabase.getInstance(requireContext()).authDao
            )
        ).get(AuthViewModel::class.java)

        binding.btnSignup.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val password2 = binding.password2EditText.text.toString()

            if (username.isBlank() || password.isBlank() || password2.isBlank()) {
                val fillMessage = "Please fill in all the fields"
                val fillSnackbar = Snackbar.make(requireView(), fillMessage, Snackbar.LENGTH_LONG)
                fillSnackbar.show()
                return@setOnClickListener
            }

            if (password != password2) {
                val passwordMessage = "Password and Confirm Password does not match!"
                val passwordSnackbar = Snackbar.make(requireView(), passwordMessage, Snackbar.LENGTH_LONG)
                passwordSnackbar.show()
                return@setOnClickListener
            }

            viewLifecycleOwner.lifecycleScope.launch {
                val count = viewModel.countUsers(username)
                if (count > 0) {
                    val errorMessage = "User already exists!"
                    val errorSnackbar = Snackbar.make(requireView(), errorMessage, Snackbar.LENGTH_LONG)
                    errorSnackbar.show()
                } else {
                    viewModel.insertAuth(Auth(username, password))
                    val successfulMessage = "Account Successfully Created!"
                    val successfullSnackbar = Snackbar.make(requireView(), successfulMessage, Snackbar.LENGTH_LONG)
                    successfullSnackbar.show()
                    findNavController().navigate(R.id.action_registration_to_login)
                }
            }
        }

        return binding.root
    }
}

