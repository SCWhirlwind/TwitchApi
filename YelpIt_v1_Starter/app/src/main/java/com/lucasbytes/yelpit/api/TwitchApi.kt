package com.lucasbytes.yelpit.api

import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TwitchApi {

    @GET("https://api.twitch.tv/helix/top")
    @Headers("Client-ID: 3j1uw3eb9e1y9z6m38sl27dlgy93hi",
            "Authorization: Bearer ijbcun0fwi6zz3hsx8xkf3wzo8ax10")
    fun getGameResult(
    ) : Call<GetDataResponse>
}