package com.example.happytree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.happytree.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*


class Home : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textDay = view.findViewById<TextView>(R.id.textDay)
        val textDate = view.findViewById<TextView>(R.id.textDate)

        val dateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val date = Date()
        val dayOfWeek = dateFormat.format(date)
        textDay.text = dayOfWeek

        val dateFormatMonth = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val formattedDate = dateFormatMonth.format(date)
        textDate.text = formattedDate
    }
}
