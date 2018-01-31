package com.example.iqbal.sunshine

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by ilham on 1/27/18.
 */

interface Api {
    @GET("forecast?id=1621177&appid=b7ad75adf945f5e7f8e37793e9100ffd")
    fun getForcast(): Call<ForcastResponse>
}