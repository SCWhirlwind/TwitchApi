package com.lucasbytes.yelpit.model


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("cursor")
    val cursor: String
)