package com.lucasbytes.yelpit.model


import com.google.gson.annotations.SerializedName

data class Stream(
    @SerializedName("data")
    val `data`: List<DataX>,
    @SerializedName("pagination")
    val pagination: PaginationX
)