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


//class Registration : Fragment() {
//
//
//    private var _binding: FragmentRegistrationBinding? = null
//    private val binding get() = _binding!!
//
//
//    private val viewModel: AuthViewModel by activityViewModels {
//        AuthViewModelFactory(
//            (activity?.application as HappyTreeApplication).database.authDao()
//        )
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.btnLoginText.setOnClickListener { view: View ->
//            view.findNavController().navigate(R.id.action_registration_to_login)
//        }
//        // Set up the button click listener
//        binding.btnSignup.setOnClickListener {
//            val username = binding.usernameEditText.text.toString()
//            val password = binding.passwordEditText.text.toString()
//            val password2 = binding.password2EditText.text.toString()
//
//            val auth = Auth(username, password)
//            viewModel.findUser
//
//
//            // Check for missing fields
//            if (username.isBlank() || password.isBlank() || password2.isBlank()) {
//                val fillMessage = "Please fill in all the fields"
//                val fillSnackbar = Snackbar.make(requireView(), fillMessage, Snackbar.LENGTH_LONG)
//                fillSnackbar.show()
//                return@setOnClickListener
//            }
//
//            // Check for password mismatch
//            if (password != password2) {
//                val passwordMessage = "Password does not match!"
//                val passwordSnackbar =
//                    Snackbar.make(requireView(), passwordMessage, Snackbar.LENGTH_LONG)
//                passwordSnackbar.show()
//                return@setOnClickListener
//            }
//
//            // Check for user already exists
//            viewModel.findUser(username = String).observe(viewLifecycleOwner, { existingUser ->
//                if (existingUser != null) {
//                    val errorMessage = "User already exists!"
//                    val errorSnackbar =
//                        Snackbar.make(requireView(), errorMessage, Snackbar.LENGTH_LONG)
//                    errorSnackbar.show()
//                } else {
//                    insertDataToDatabase(username, password)
//                }
//            })
//
//            }
//        }
//
//    private fun insertDataToDatabase(username: String, password: String) {
//        val auth = Auth(username, password)
//        viewModel.addUser(auth)
//        val successfulMessage = "Account Successfully Created!"
//        val successfullSnackbar = Snackbar.make(requireView(), successfulMessage, Snackbar.LENGTH_LONG)
//        successfullSnackbar.show()
//        view?.findNavController()?.navigate(R.id.action_registration_to_login)
//
//    }
//}






//            val username = binding.usernameEditText.text.toString()
//            val password = binding.passwordEditText.text.toString()
//            val password2 = binding.password2EditText.text.toString()

//            if (username.isNotEmpty() || password.isNotEmpty() || password2.isNotEmpty()) {
//                if (password == password2){
//                    val auth = Auth(username, password)
//                    viewModel.findUser(username)
//
//                    if (auth !=null){
////                        Toast.makeText(context, "User already exist!", Toast.LENGTH_SHORT).show()
//                        val errorMessage = "User already exist!"
//                        val errorSnackbar = Snackbar.make(requireView(), errorMessage, Snackbar.LENGTH_LONG)
//                        errorSnackbar.show()
//                    }
//                    else {
//                        val auth = Auth(username, password)
//                        viewModel.addUser(auth)
//                        Toast.makeText(context, "Account Successfully Created!", Toast.LENGTH_SHORT).show()
//                        val successfulMessage = "Account Successfully Created!"
//                        val successfullSnackbar = Snackbar.make(requireView(), successfulMessage, Snackbar.LENGTH_LONG)
//                        successfullSnackbar.show()
////                        view?.findNavController()?.navigate(R.id.action_registration_to_login)
//                    }
//                }
//                else {
//                    Toast.makeText(context, "Password does not match!", Toast.LENGTH_SHORT).show()
//                    val passwordMessage = "Password does not match!"
//                    val passwordSnackbar = Snackbar.make(requireView(), passwordMessage, Snackbar.LENGTH_LONG)
//                    passwordSnackbar.show()
//                }
//            }
//            if (username.isNotEmpty() || password.isNotEmpty() || password2.isNotEmpty()) {
//
//                if (password == password2){
//                    val auth = viewModel.findUser(username)
//
//                    if (auth != null){
//                        val errorMessage = "User already exist!"
//                        val errorSnackbar = Snackbar.make(requireView(), errorMessage, Snackbar.LENGTH_LONG)
//                        errorSnackbar.show()
//                    } else {
//                        val auth = Auth(username, password)
//                        viewModel.addUser(auth)
////                        Toast.makeText(context, "Account Successfully Created!", Toast.LENGTH_SHORT).show()
//                        val successfulMessage = "Account Successfully Created!"
//                        val successfullSnackbar = Snackbar.make(requireView(), successfulMessage, Snackbar.LENGTH_LONG)
//                        successfullSnackbar.show()
//                        view?.findNavController()?.navigate(R.id.action_registration_to_login)
//                    }
//
//                } else {
//                    val passwordMessage = "Password does not match!"
//                    val passwordSnackbar = Snackbar.make(requireView(), passwordMessage, Snackbar.LENGTH_LONG)
//                    passwordSnackbar.show()
//                }
//            }
//            else{
////                Toast.makeText(context, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
//                val fillMessage = "Please fill in all the fields"
//                val fillSnackbar = Snackbar.make(requireView(),fillMessage, Snackbar.LENGTH_LONG)
//                fillSnackbar.show()
//            }
//        }
//        binding.returnBtn.setOnClickListener { view: View ->
//            view.findNavController().navigate(R.id.action_registration_to_welcome)
//        }
//    }


// TRY CODE
//    private fun insertDataToDatabase(){
//        var pass = 0
//
//        val username = binding.usernameEditText.text.toString()
//        val password = binding.passwordEditText.text.toString()
//        val password2  = binding.password2EditText.text.toString()
//
//        if (binding.usernameEditText.text.toString() == "" || binding.passwordEditText.text.toString() == "" ||
//                binding.password2EditText.text.toString() == "") {
//            val fillMessage = "Please fill in all the fields"
//            val fillSnackbar = Snackbar.make(requireView(),fillMessage, Snackbar.LENGTH_LONG)
//            fillSnackbar.show()
//        } else {pass += 1}
//
//        if (password == password2){
//            val auth = viewModel.findUser(username, password)
//            val passwordMessage = "Password does not match!"
//            val passwordSnackbar = Snackbar.make(requireView(), passwordMessage, Snackbar.LENGTH_LONG)
//            passwordSnackbar.show()
//        } else { pass += 1 }
//
//        if (pass == 2) {
//            val auth = Auth(username, password)
//                    viewModel.addUser(auth)
//                    Log.d(TAG, "Account created")
//                    val successfulMessage = "Account Successfully Created!"
//                    val successfullSnackbar = Snackbar.make(requireView(), successfulMessage, Snackbar.LENGTH_LONG)
//                    successfullSnackbar.show()
//                    view?.findNavController()?.navigate(R.id.action_registration_to_login)
//
//        }



// ORIGINAL CODE
//    private fun insertDataToDatabase() {
//        val username = binding.usernameEditText.text.toString()
//        val password = binding.passwordEditText.text.toString()
//        val password2 = binding.password2EditText.text.toString()
//
//        if (username.isEmpty() || password.isEmpty() || password2.isEmpty()) {
//
//            if (password == password2){
//                val auth = viewModel.findUser(username, password)
//
//                if (auth != null){
//                    val errorMessage = "User already exist!"
//                    val errorSnackbar = Snackbar.make(requireView(), errorMessage, Snackbar.LENGTH_LONG)
//                    errorSnackbar.show()
//                } else {
//                    val auth = Auth(username, password)
//                    viewModel.addUser(auth)
//                    Log.d(TAG, "Account created")
//                    val successfulMessage = "Account Successfully Created!"
//                    val successfullSnackbar = Snackbar.make(requireView(), successfulMessage, Snackbar.LENGTH_LONG)
//                    successfullSnackbar.show()
//                    view?.findNavController()?.navigate(R.id.action_registration_to_login)
//                }
//
//            } else {
//                val passwordMessage = "Password does not match!"
//                val passwordSnackbar = Snackbar.make(requireView(), passwordMessage, Snackbar.LENGTH_LONG)
//                passwordSnackbar.show()
//            }
//        }
//        else{
//            val fillMessage = "Please fill in all the fields"
//            val fillSnackbar = Snackbar.make(requireView(),fillMessage, Snackbar.LENGTH_LONG)
//            fillSnackbar.show()
//
//        }
//    }


//    private fun checkInput(username: String, password: String, password2: String): Boolean {
//        if (username.isEmpty() || password.isEmpty() || password2.isEmpty()) {
//            Toast.makeText(context, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
//            return false
//        }
//        return true
//    }
//
//
//    private fun confirmPassword(password: String, password2: String): Boolean {
//        return password == password2
//    }

//    private fun userExists(username: String): Boolean {
//        return db.AuthDao().countUsers(username) > 0
//    }


//    private fun insertDataToDatabase() {
//        val username = binding.usernameEditText.text.toString()
//        val password = binding.passwordEditText.text.toString()
//        val password2 = binding.password2EditText.text.toString()
//
//        if (checkInput(username, password, password2)) {
//            if (confirmPassword(password, password2)) {
//                val auth = Auth(username, password)
//                viewModel.addUser(auth)
//                Toast.makeText(context, "Account Successfully Created!", Toast.LENGTH_SHORT).show()
//                view?.findNavController()?.navigate(R.id.action_registration_to_login)
//            } else {
//                Toast.makeText(context, "Password does not match!", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}

