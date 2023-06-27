package com.example.happytree


import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
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
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class UpdateFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private val args: UpdateFragmentArgs by navArgs()
    private lateinit var farmViewModel: FarmViewModel
    private var selectedDate: LocalDate? = null
    private var selectedTime: LocalTime? = null
    private lateinit var binding: FragmentUpdateBinding // Declare binding as a class-level property

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        farmViewModel = ViewModelProvider(this)[FarmViewModel::class.java]

        binding.Update.setText(args.farmdetails.dateTime)
        binding.upDisease.setText(args.farmdetails.disease)
        binding.upNumtree.setText(args.farmdetails.numberOfTrees)

        binding.Update.keyListener = null // Make the field non-editable
        binding.Update.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnUp.setOnClickListener {
            val item = Farm(
                args.farmdetails.id,
                binding.upDisease.text.toString(),
                binding.upNumtree.text.toString(),
                binding.Update.text.toString()
            )
            farmViewModel.updateItem(item)
            findNavController().navigate(R.id.viewFragment)
        }

        binding.btnDel.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setPositiveButton("Yes") { _, _ ->
                val item = Farm(
                    args.farmdetails.id,
                    binding.upDisease.text.toString(),
                    binding.upNumtree.text.toString(),
                    binding.Update.text.toString()
                )
                farmViewModel.deleteItem(item)
                findNavController().navigate(R.id.viewFragment)
            }
            builder.setNegativeButton("No", null)
            builder.setTitle("Delete ${args.farmdetails.disease}?")
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
        val currentTime = selectedTime ?: LocalTime.now()
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
        selectedTime = LocalTime.of(hourOfDay, minute)

        val dateTime = LocalDateTime.of(selectedDate, selectedTime)
        val formattedDateTime = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

        binding.Update.setText(formattedDateTime)
    }
}
