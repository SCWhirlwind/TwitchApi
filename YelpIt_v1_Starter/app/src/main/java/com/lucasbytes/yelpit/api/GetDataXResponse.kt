package com.lucasbytes.yelpit.api

import com.google.gson.annotations.SerializedName
import com.lucasbytes.yelpit.model.DataX
import com.lucasbytes.yelpit.model.Pagination

data class GetDataXResponse(
    @SerializedName("dataX")
    val `data`: List<DataX>,
    @SerializedName("pagination")
    val pagination: Pagination
)
