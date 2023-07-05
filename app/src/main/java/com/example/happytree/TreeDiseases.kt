package com.example.happytree


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class TreeDiseases : Fragment() {

    private lateinit var rootView: View
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
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_tree_diseases, container, false)
        initializeViews(rootView)
        setupDiseases()
        return rootView
    }

    private fun initializeViews(view: View) {
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

    private fun setupDiseases() {
        // Retrieve the texts from string resources
        val anthracnoseName = resources.getString(R.string.anthracnose_name)
        val anthracnoseDescription = resources.getString(R.string.anthracnose_description)
        val anthracnoseLethality = resources.getString(R.string.anthracnose_lethality)
        val anthracnoseHumidity = resources.getString(R.string.anthracnose_humidity)

        val sootyMoldName = resources.getString(R.string.sooty_mold_name)
        val sootyMoldDescription = resources.getString(R.string.sooty_mold_description)
        val sootyMoldLethality = resources.getString(R.string.sooty_mold_lethality)
        val sootyMoldHumidity = resources.getString(R.string.sooty_mold_humidity)

        val redRustName = resources.getString(R.string.red_rust_name)
        val redRustDescription = resources.getString(R.string.red_rust_description)
        val redRustLethality = resources.getString(R.string.red_rust_lethality)
        val redRustHumidity = resources.getString(R.string.red_rust_humidity)

        val powderyMildewName = resources.getString(R.string.powdery_mildew_name)
        val powderyMildewDescription = resources.getString(R.string.powdery_mildew_description)
        val powderyMildewLethality = resources.getString(R.string.powdery_mildew_lethality)
        val powderyMildewHumidity = resources.getString(R.string.powdery_mildew_humidity)

        rootView.apply {
            // Set the text for each disease using the retrieved strings
            diseaseName1.text = anthracnoseName
            diseaseDescription1.text = anthracnoseDescription
            diseaseLethality1.text = anthracnoseLethality
            diseaseHumidity1.text = anthracnoseHumidity

            diseaseName2.text = sootyMoldName
            diseaseDescription2.text = sootyMoldDescription
            diseaseLethality2.text = sootyMoldLethality
            diseaseHumidity2.text = sootyMoldHumidity

            diseaseName3.text = redRustName
            diseaseDescription3.text = redRustDescription
            diseaseLethality3.text = redRustLethality
            diseaseHumidity3.text = redRustHumidity

            diseaseName4.text = powderyMildewName
            diseaseDescription4.text = powderyMildewDescription
            diseaseLethality4.text = powderyMildewLethality
            diseaseHumidity4.text = powderyMildewHumidity
        }
    }

}
