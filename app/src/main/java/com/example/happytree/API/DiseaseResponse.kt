package com.example.happytree.API

import com.google.gson.annotations.SerializedName

class DiseaseResponse {
    @SerializedName("DESCRIPTION")
    var description: String? = null
    @SerializedName("LETHALITY")
    var lethality: String? = null
    @SerializedName("HUMIDITY")
    var humidity: String? = null


}