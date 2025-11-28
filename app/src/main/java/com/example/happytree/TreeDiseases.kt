package com.example.happytree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.happytree.API.DiseaseResponse
import com.example.happytree.API.diseaseSignal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TreeDiseases : Fragment() {

    private lateinit var rootView: View

    // Cards
    private lateinit var cardAnthracnose: CardView
    private lateinit var cardSootyMold: CardView
    private lateinit var cardRedRust: CardView
    private lateinit var cardPowderyMildew: CardView

    // TextViews
    private lateinit var diseaseName1: TextView
    private lateinit var diseaseDescription1: TextView
    private lateinit var diseaseLethality1: TextView
    private lateinit var diseaseHumidity1: TextView
    private lateinit var diseaseName2: TextView
    private lateinit var diseaseDescription2: TextView
    private lateinit var diseaseLethality2: TextView
    private lateinit var diseaseHumidity2: TextView
    private lateinit var diseaseName3: TextView
    private lateinit var diseaseDescription3: TextView
    private lateinit var diseaseLethality3: TextView
    private lateinit var diseaseHumidity3: TextView
    private lateinit var diseaseName4: TextView
    private lateinit var diseaseDescription4: TextView
    private lateinit var diseaseLethality4: TextView
    private lateinit var diseaseHumidity4: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_tree_diseases, container, false)
        initializeViews(rootView)
        loadAllDiseases()
        return rootView
    }

    private fun initializeViews(view: View) {
        cardAnthracnose = view.findViewById(R.id.cardAnthracnose)
        cardSootyMold = view.findViewById(R.id.cardSootyMold)
        cardRedRust = view.findViewById(R.id.cardRedRust)
        cardPowderyMildew = view.findViewById(R.id.cardPowderyMildew)

        diseaseName1 = view.findViewById(R.id.diseaseName1)
        diseaseDescription1 = view.findViewById(R.id.diseaseDescription1)
        diseaseLethality1 = view.findViewById(R.id.diseaseLethality1)
        diseaseHumidity1 = view.findViewById(R.id.diseaseHumidity1)

        diseaseName2 = view.findViewById(R.id.diseaseName2)
        diseaseDescription2 = view.findViewById(R.id.diseaseDescription2)
        diseaseLethality2 = view.findViewById(R.id.diseaseLethality2)
        diseaseHumidity2 = view.findViewById(R.id.diseaseHumidity2)

        diseaseName3 = view.findViewById(R.id.diseaseName3)
        diseaseDescription3 = view.findViewById(R.id.diseaseDescription3)
        diseaseLethality3 = view.findViewById(R.id.diseaseLethality3)
        diseaseHumidity3 = view.findViewById(R.id.diseaseHumidity3)

        diseaseName4 = view.findViewById(R.id.diseaseName4)
        diseaseDescription4 = view.findViewById(R.id.diseaseDescription4)
        diseaseLethality4 = view.findViewById(R.id.diseaseLethality4)
        diseaseHumidity4 = view.findViewById(R.id.diseaseHumidity4)
    }

    private fun loadAllDiseases() {
        val service = diseaseSignal.retrofitService

        fun loadDisease(
            call: Call<DiseaseResponse>,
            displayName: String,
            nameView: TextView,
            descView: TextView,
            lethView: TextView,
            humView: TextView
        ) {
            // Show loading placeholder
            nameView.text = displayName
            descView.text = "Loading..."
            lethView.text = "-"
            humView.text = "-"

            call.enqueue(object : Callback<DiseaseResponse> {
                override fun onResponse(
                    call: Call<DiseaseResponse>,
                    response: Response<DiseaseResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            descView.text = body.description ?: "No description available"
                            lethView.text = body.lethality ?: "-"
                            humView.text = body.humidity ?: "-"
                        } else {
                            descView.text = "No data available"
                        }
                    } else {
                        descView.text = "Failed to load data"
                    }
                }

                override fun onFailure(call: Call<DiseaseResponse>, t: Throwable) {
                    t.printStackTrace()
                    descView.text = "Error fetching data"
                    Toast.makeText(requireContext(), "Failed to load $displayName", Toast.LENGTH_SHORT).show()
                }
            })
        }

        // Load all diseases from API
        loadDisease(service.getAnthracnoseData(), "Anthracnose", diseaseName1, diseaseDescription1, diseaseLethality1, diseaseHumidity1)
        loadDisease(service.getSootyData(), "Sooty Mold", diseaseName2, diseaseDescription2, diseaseLethality2, diseaseHumidity2)
        loadDisease(service.getRedData(), "Red Rust", diseaseName3, diseaseDescription3, diseaseLethality3, diseaseHumidity3)
        loadDisease(service.getPowderyMildewData(), "Powdery Mildew", diseaseName4, diseaseDescription4, diseaseLethality4, diseaseHumidity4)
    }
}
