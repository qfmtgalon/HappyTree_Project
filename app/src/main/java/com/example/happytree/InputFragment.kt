package com.example.happytree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.happytree.database.FarmDatabase.FarmViewModel
import com.example.happytree.databinding.FragmentInputBinding


class InputFragment : Fragment() {

    private lateinit var farmViewModel: FarmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_input, container, false)
            farmViewModel = ViewModelProvider(this)[FarmViewModel::class.java]

        val binding = FragmentInputBinding.bind(view)
        binding.BtnInputReg.setOnClickListener{
            val disease = binding.InputDisease.text.toString()
            val numTree = binding.numTree.text.toString()
            val dateTime = binding.dateTime.text.toString()
            val item = com.example.happytree.database.FarmDatabase.Farm(0, disease, numTree, dateTime)
            farmViewModel.insertItem(item)
        }


        return binding.root
    }


}