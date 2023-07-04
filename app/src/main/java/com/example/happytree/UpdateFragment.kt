package com.example.happytree

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.happytree.database.FarmDatabase.Farm
import com.example.happytree.database.FarmDatabase.FarmViewModel
import com.example.happytree.databinding.FragmentUpdateBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UpdateFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private val args: UpdateFragmentArgs by navArgs()
    private lateinit var farmViewModel: FarmViewModel
    private var selectedDate: LocalDate? = null
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var currentItem: Farm

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        farmViewModel = ViewModelProvider(this)[FarmViewModel::class.java]

        binding.Update.keyListener = null
        binding.Update.setOnClickListener {
            showDatePickerDialog()
        }
        updateTimeField()

        // Retrieve the item details from the arguments
        currentItem = args.farmdetails

        // Set the item details to the views
        binding.upDisease.setText(currentItem.disease)
        binding.upNumtree.setText(currentItem.numberOfTrees)
        binding.upVariant.setText(currentItem.variant)
        binding.Update.setText(currentItem.dateTime)

        binding.btnUp.setOnClickListener {
            // Update the item with the new values
            val updatedItem = Farm(
                currentItem.id,
                binding.upDisease.text.toString(),
                binding.upNumtree.text.toString(),
                binding.Update.text.toString(),
                binding.upVariant.text.toString()
            )
            farmViewModel.updateItem(updatedItem)
            findNavController().navigate(R.id.viewFragment)
        }

        binding.btnDel.setOnClickListener {
            // Show the delete confirmation dialog
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                // Delete the item
                farmViewModel.deleteItem(currentItem)
                findNavController().navigate(R.id.viewFragment)
            }
            builder.setNegativeButton("No", null)
            builder.setTitle("Delete ${currentItem.disease}?")
            builder.setMessage("Do you want to delete this item?")
            builder.create().show()
        }

        return binding.root
    }

    private fun showDatePickerDialog() {
        val currentDate = selectedDate ?: LocalDate.now()
        val year = currentDate.year
        val month = currentDate.monthValue - 1
        val day = currentDate.dayOfMonth

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            this,
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
        showTimePickerDialog()
    }

    private fun showTimePickerDialog() {
        val currentTime = LocalDateTime.now()
        val hour = currentTime.hour
        val minute = currentTime.minute

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            this,
            hour,
            minute,
            true
        )
        timePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        updateTimeField()
    }

    private fun updateTimeField() {
        val date = selectedDate ?: LocalDate.now()
        val time = LocalDateTime.now().toLocalTime()
        val dateTime = LocalDateTime.of(date, time)

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val formattedDateTime = dateTime.format(formatter)

        binding.Update.setText(formattedDateTime)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigate(R.id.action_updateFragment_to_viewFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
