package br.com.dbc.application.api.client

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientAdapter {
    val apiClient : ApiClient = Retrofit.Builder()
        .baseUrl("http://5f5a8f24d44d640016169133.mockapi.io")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiClient::class.java)
}