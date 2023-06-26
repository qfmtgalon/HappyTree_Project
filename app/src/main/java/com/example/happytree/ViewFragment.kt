package com.example.happytree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.happytree.database.FarmDatabase.Farm
import com.example.happytree.database.FarmDatabase.FarmAdapter
import com.example.happytree.database.FarmDatabase.FarmViewModel
import com.example.happytree.databinding.FragmentViewBinding

class ViewFragment : Fragment() {

    private lateinit var farmViewModel: FarmViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_view, container, false)

        val binding = FragmentViewBinding.bind(view)
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

        farmViewModel = ViewModelProvider(this)[FarmViewModel::class.java]
        farmViewModel.readItem()?.observe(viewLifecycleOwner, Observer {
            farmAdapter.setData(it)
        })

        return binding.root
    }



}