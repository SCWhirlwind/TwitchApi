package com.lucasbytes.yelpit.data

import android.util.Log
import com.lucasbytes.yelpit.api.GetDataResponse
import com.lucasbytes.yelpit.api.TwitchApi
import com.lucasbytes.yelpit.model.Data
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object DataRepository {
    private val twitchApi: TwitchApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.twitch.tv/helix/top/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        twitchApi = retrofit.create(TwitchApi::class.java)
    }

    fun getGameResults(
        limit: Int = 10,
        offset: Int = 0,
        onSuccess: (gameResults: List<Data>) -> Unit,
        onError: () -> Unit
    ) {
        twitchApi.getGameResult(limit = limit, offset = offset)
            .enqueue(object: Callback<GetDataResponse> {
                override fun onFailure(call: Call<GetDataResponse>, t: Throwable) {
                    Log.d("GameRepository", t.message.toString())
                    onError.invoke()
                }

                override fun onResponse(
                    call: Call<GetDataResponse>,
                    response: Response<GetDataResponse>
                ) {
                        if(response.isSuccessful)
                        {
                            val responseBody = response.body()

                            if (responseBody != null)
                            {
                                onSuccess.invoke(responseBody.data)
                            }
                            else
                            {
                                Log.d("GameRepository", "Response was successful, but no body")
                                onError.invoke()
                            }
                        }
                }
            })
    }
}