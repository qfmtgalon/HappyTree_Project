package com.example.happytree

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
import com.example.happytree.database.FarmDatabase.Farm
import com.example.happytree.database.FarmDatabase.FarmViewModel
import com.example.happytree.databinding.FragmentInputBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InputFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var farmViewModel: FarmViewModel
    private var selectedDate: LocalDate? = null
    private lateinit var binding: FragmentInputBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInputBinding.inflate(inflater, container, false)
        farmViewModel = ViewModelProvider(this)[FarmViewModel::class.java]

        binding.BtnInputReg.setOnClickListener{
            val disease = binding.InputDisease.text.toString()
            val variant = binding.Variant.text.toString()
            val numTree = binding.numTree.text.toString()
            val dateTime = binding.dateTime.text.toString()
            val item = Farm(0, disease, numTree, dateTime, variant)
            farmViewModel.insertItem(item)

            val navController = findNavController()
            navController.navigate(R.id.action_inputFragment_to_viewFragment)
        }

        binding.dateTime.keyListener = null // Make the field non-editable
        binding.dateTime.setOnClickListener {
            showDatePickerDialog()
        }
        updateTimeField()

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

        binding.dateTime.setText(formattedDateTime)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigate(R.id.action_inputFragment_to_viewFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
