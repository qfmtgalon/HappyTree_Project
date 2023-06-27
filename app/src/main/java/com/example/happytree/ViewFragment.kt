package com.example.happytree

import FarmAdapter
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.happytree.database.FarmDatabase.Farm
import com.example.happytree.database.FarmDatabase.FarmViewModel
import com.example.happytree.databinding.FragmentViewBinding

class ViewFragment : Fragment() {

    private lateinit var farmViewModel: FarmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewBinding.inflate(inflater, container, false)
        val recyclerView = binding.recycleone
        val farmAdapter = FarmAdapter()

        recyclerView.adapter = farmAdapter
        recyclerView.layoutManager = GridLayoutManager(context, 1)

        farmAdapter.setOnItemClickListener(object : FarmAdapter.OnItemClickListener {
            override fun onItemClick(farm: Farm) {
                val action = ViewFragmentDirections.actionViewFragmentToUpdateFragment(farm)
                findNavController().navigate(action)
            }
        })

        farmViewModel = ViewModelProvider(this).get(FarmViewModel::class.java)
        farmViewModel.readItem()?.observe(viewLifecycleOwner, Observer {
            farmAdapter.setData(it)
        })

        binding.btnDeleteAll.setOnClickListener {
            deleteAllItems()
        }

        return binding.root
    }

    private fun deleteAllItems() {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete All Items")
            .setMessage("Are you sure you want to delete all items?")
            .setPositiveButton("Delete") { _, _ ->
                farmViewModel.deleteAllItems()
                Toast.makeText(requireContext(), "All items deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
