package br.com.dbc.application.service

import br.com.dbc.application.model.Checkin
import br.com.dbc.application.model.Event
import br.com.dbc.application.repository.ListEventRepository
import retrofit2.Response

class ListEventService(private val repository: ListEventRepository) {

    /*
     -------------------------------------------------------------
     Aqui também foi utilizado Coroutines para facilitar as chama-
     das assincronas da API
     -------------------------------------------------------------
     */
    suspend fun getEventList():Response<List<Event>> = repository.getEventList()
    suspend fun getEvent(id:Long)                    = repository.getEvent(id)
    suspend fun doCheckIn(checkin: Checkin)          = repository.doCheckIn(checkin)
}