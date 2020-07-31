package com.lucasbytes.yelpit.api

import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TwitchApi {

    @GET("https://api.twitch.tv/helix/top")
    @Headers("Authorization: Bearer ijbcun0fwi6zz3hsx8xkf3wzo8ax10")
    fun getGameResult(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0
    ) : Call<GetDataResponse>
}