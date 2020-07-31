package com.lucasbytes.yelpit.model


import com.google.gson.annotations.SerializedName

data class PaginationX(
    @SerializedName("cursor")
    val cursor: String
)