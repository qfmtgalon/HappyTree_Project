package com.example.happytree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.happytree.API.DiseaseResponse
import com.example.happytree.API.diseaseSignal
import com.example.happytree.databinding.FragmentAnthracnoseBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Anthracnose : Fragment() {

    private lateinit var binding: FragmentAnthracnoseBinding

    private var anthracnoseData: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnthracnoseBinding.inflate(layoutInflater)
        return binding.root
    }override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        anthracnoseData = binding.subheadForAnthracnose
        binding.btnApIAnthracnose.setOnClickListener { getCurrentData() }
    }
    private fun getCurrentData() {
        GlobalScope.launch {
            val call = diseaseSignal.retrofitService.getRedData()
            call.enqueue(object : Callback<DiseaseResponse> {
                override fun onResponse(
                    call: Call<DiseaseResponse>,
                    response: Response<DiseaseResponse>
                ) {

                    if (response.code() == 200) {
                        val diseaseResponse = response.body()!!

                        val stringBuilder = "DESCRIPTION: " +
                                diseaseResponse.description + "\n" +
                                "\n" +
                                "LETHALITY: " +
                                diseaseResponse.lethality + "\n" +
                                "\n" +
                                "HUMIDITY: " +
                                diseaseResponse.humidity

                        anthracnoseData!!.text = stringBuilder
                    }
                }

                override fun onFailure(call: Call<DiseaseResponse>, t: Throwable) {
                    anthracnoseData!!.text = t.message
                }


            })
        }
    }
}