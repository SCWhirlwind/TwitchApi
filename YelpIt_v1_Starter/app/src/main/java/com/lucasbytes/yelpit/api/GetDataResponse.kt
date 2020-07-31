package com.lucasbytes.yelpit.api


import com.google.gson.annotations.SerializedName
import com.lucasbytes.yelpit.model.Data
import com.lucasbytes.yelpit.model.DataX
import com.lucasbytes.yelpit.model.Pagination

data class GetDataResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("dataX")
    val `dataX`: List<DataX>,
    @SerializedName("pagination")
    val pagination: Pagination
)