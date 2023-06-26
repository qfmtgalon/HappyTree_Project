package com.example.happytree

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.happytree.database.FarmDatabase.Farm
import com.example.happytree.database.FarmDatabase.FarmViewModel
import com.example.happytree.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {
    private val args: UpdateFragmentArgs by navArgs()
    private lateinit var farmViewModel: FarmViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        val binding=FragmentUpdateBinding.bind(view)
        farmViewModel=ViewModelProvider(this)[FarmViewModel::class.java]
        binding.Update.setText(args.farmdetails.dateTime)
        binding.upDisease.setText(args.farmdetails.disease)
        binding.upNumtree.setText(args.farmdetails.numberOfTrees)
        binding.btnUp.setOnClickListener{
            val item= Farm(args.farmdetails.id,binding.upDisease.text.toString(),binding.upNumtree.text.toString(),binding.Update.text.toString())
            farmViewModel.updateItem(item)
            findNavController().navigate(R.id.viewFragment)
        }
        binding.btnDel.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setPositiveButton("Yes"){_,_->
                val item=Farm(args.farmdetails.id,binding.upDisease.text.toString(),binding.upNumtree.text.toString(),binding.Update.text.toString())
                farmViewModel.deleteItem(item)
                findNavController().navigate(R.id.viewFragment)
            }
            builder.setNegativeButton("No"){_,_-> }
            builder.setTitle("Delete ${args.farmdetails.disease}?")
            builder.setMessage("Do you want to delete this item?")
            builder.create().show()
        }

        return binding.root
    }

}