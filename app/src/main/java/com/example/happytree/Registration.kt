package com.example.happytree

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.happytree.database.Auth
import com.example.happytree.database.AuthDatabase
import com.example.happytree.database.AuthViewModel
import com.example.happytree.database.AuthViewModelFactory
import com.example.happytree.databinding.FragmentRegistrationBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class Registration : Fragment() {


    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
//    private lateinit var db: AuthDatabase

    private val viewModel: AuthViewModel by activityViewModels {
        AuthViewModelFactory(
            (activity?.application as HappyTreeApplication).database.AuthDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        db = Room.databaseBuilder(
//            requireContext(),
//            AuthDatabase::class.java, "database"
//        ).build()
        binding.btnLoginText.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_registration_to_login)
        }
        binding.btnSignup.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun checkInput(username: String, password: String, password2: String): Boolean {
        if (username.isEmpty() || password.isEmpty() || password2.isEmpty()) {
            Toast.makeText(context, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }


    private fun confirmPassword(password: String, password2: String): Boolean {
        return password == password2
    }

//    private fun userExists(username: String): Boolean {
//        return db.AuthDao().countUsers(username) > 0
//    }



    private fun insertDataToDatabase() {
        val username = binding.usernameEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        val password2 = binding.password2EditText.text.toString()

        if (checkInput(username, password, password2)) {
            if (confirmPassword(password, password2)) {
                val auth = Auth(username, password)
                viewModel.addUser(auth)
                Toast.makeText(context, "Account Successfully Created!", Toast.LENGTH_SHORT).show()
                view?.findNavController()?.navigate(R.id.action_registration_to_login)
            }
            else {
                Toast.makeText(context, "Password does not match!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
