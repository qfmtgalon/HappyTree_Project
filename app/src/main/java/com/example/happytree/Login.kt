package com.example.happytree

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.happytree.database.Auth
import com.example.happytree.database.AuthDatabase
import com.example.happytree.database.AuthViewModel
import com.example.happytree.database.AuthViewModelFactory
import com.example.happytree.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar


class Login : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnSignUpText.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_login_to_registration)
        }

        viewModel = ViewModelProvider(
            this,
            AuthViewModelFactory(AuthDatabase.getInstance(requireContext()).authDao
            )
        ).get(AuthViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            val username = binding.loginUsername.text.toString()
            val password = binding.loginPassword.text.toString()

            if (username.isBlank() || password.isBlank()) {
                val fillMessage = "Please enter your username and password"
                val fillSnackbar = Snackbar.make(requireView(), fillMessage, Snackbar.LENGTH_LONG)
                fillSnackbar.show()
                return@setOnClickListener
            }

            viewModel.getAuth(username).observe(viewLifecycleOwner) { auth ->
                if (auth == null) {
                    val notFoundMessage = "User does not exists"
                    val notFoundSnackbar = Snackbar.make(requireView(), notFoundMessage, Snackbar.LENGTH_LONG)
                    notFoundSnackbar.show()

                } else if (auth.password != password) {
                    val incPWMessage = "Incorrent password."
                    val inPWSnackbar = Snackbar.make(requireView(), incPWMessage, Snackbar.LENGTH_LONG)
                    inPWSnackbar.show()
                } else {
                    val welcomeMessage = "Welcome $username !"
                    val welcomeSnackbar = Snackbar.make(requireView(), welcomeMessage, Snackbar.LENGTH_LONG)
                    welcomeSnackbar.show()
                    val intent = Intent(requireContext(), HomeActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        return binding.root
    }
}

//class Login : Fragment(R.layout.fragment_login) {
//
//    private var _binding: FragmentLoginBinding? = null
//    private val binding get() = _binding!!
//    private val viewModel: AuthViewModel by activityViewModels {
//        AuthViewModelFactory(
//            (activity?.application as HappyTreeApplication).database.AuthDao()
//        )
//    }
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        _binding = FragmentLoginBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.btnSignUpText.setOnClickListener { view: View ->
//            view.findNavController().navigate(R.id.action_login_to_registration)
//        }
//
//        val username = binding.loginUsername.text.toString()
//        val password = binding.loginPassword.text.toString()
//
//        binding.btnLogin.setOnClickListener {
//            val user = viewModel.findUser(username.toString(), password.toString())
//            if (username.isEmpty() || password.isEmpty()) {
//                val fillMessage = "Please fill in all fields"
//                val fillSnackbar = Snackbar.make(requireView(), fillMessage, Snackbar.LENGTH_LONG)
//                fillSnackbar.show()
//            } else {
//                if (user != null) {
//                    val intent = Intent(requireContext(), HomeActivity::class.java)
//                    val successMessage = "Welcome ${user.username}"
//                    val successSnackbar =
//                        Snackbar.make(requireView(), successMessage, Snackbar.LENGTH_LONG)
//                    successSnackbar.show()
//                    startActivity(intent)
//                    requireActivity().finish()
//                } else {
//                    val passwordMessage = "Username or Password is incorrect"
//                    val passwordSnackbar =
//                        Snackbar.make(requireView(), passwordMessage, Snackbar.LENGTH_LONG)
//                    passwordSnackbar.show()
//                }
//            }
//        }
//    }
//}


//        binding.btnLogin.setOnClickListener {
//
//            val user = viewModel.findUser(username, password)
//
//            if (username.isEmpty() || password.isEmpty()) {
//                val fillMessage = "Please fill in all the fields"
//                val fillSnackbar = Snackbar.make(requireView(),fillMessage, Snackbar.LENGTH_LONG)
//                fillSnackbar.show()
//            } else {
//                if (user != null){
//                    val intent = Intent(requireContext(), HomeActivity::class.java)
//                    startActivity(intent)
//                } else {
//                    val passwordMessage = "Password does not match!"
//                    val passwordSnackbar = Snackbar.make(requireView(), passwordMessage, Snackbar.LENGTH_LONG)
//                    passwordSnackbar.show()
//                }
//            }