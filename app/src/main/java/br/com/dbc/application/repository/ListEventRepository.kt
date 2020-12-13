package br.com.dbc.application.repository

import br.com.dbc.application.api.client.ApiClientAdapter
import br.com.dbc.application.model.Checkin

class ListEventRepository {

    suspend fun getEventList()              =  ApiClientAdapter.apiClient.getEventList()
    suspend fun getEvent(id:Long)           = ApiClientAdapter.apiClient.getEvent(id)
    suspend fun doCheckIn(checkin: Checkin) = ApiClientAdapter.apiClient.doCheckInOnEvent(checkin)

}