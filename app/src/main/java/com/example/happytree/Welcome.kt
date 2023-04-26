package com.example.happytree

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.happytree.databinding.ActivityHomeBinding
import com.example.happytree.databinding.FragmentWelcomeBinding


class Welcome : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(layoutInflater)
        binding.btnLoginText.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_welcome_to_login)
        }

        binding.btnGetStarted.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_welcome_to_registration)
    }
        return binding.root
        }

}