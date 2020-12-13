package br.com.dbc.application.api.client

import br.com.dbc.application.model.Checkin
import br.com.dbc.application.model.Event
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiClient {
    @GET("/api/events")
    suspend fun getEventList():Response<List<Event>>
    @GET("api/events/{id}")
    suspend fun getEvent(@Path("id") id:Long):Response<Event>
    @POST("/api/checkin")
    suspend fun doCheckInOnEvent(@Body checkin: Checkin):Response<Any>
}