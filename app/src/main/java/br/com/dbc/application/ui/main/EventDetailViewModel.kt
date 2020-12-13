package br.com.dbc.application.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dbc.application.R
import br.com.dbc.application.application.Application
import br.com.dbc.application.model.Event
import br.com.dbc.application.service.ListEventService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A injeção da dependência é feita via Koin
 */
class EventDetailViewModel(private val service: ListEventService) : ViewModel() {

    init {
        //---------------------------------------------------
        //Just to check if we have only one instance
        //---------------------------------------------------
        Log.i("viewmodel","init view model $this")
        //---------------------------------------------------
    }

    val eventLiveData       = MutableLiveData<Event>()
    val error               = MutableLiveData<String>()
    val progress            = MutableLiveData<Boolean>()
    /**
     * Uso de Coroutine aqui também. Dispatchers.Main significa que será
     * mostrado o resultado na main thread, assim não presisamos usar o
     * postValue do ViewModel mas somente value.
     * É passado o ID do evento e retornamos os detalhes do mesmo.
     */
    fun getEvent(id:Long){
        viewModelScope.launch(Dispatchers.Main ) {
            val response = service.getEvent(id)
            if(response.isSuccessful){
                progress.value = false
                response.body().let {event ->
                    eventLiveData.value = event
                }
            }else{
                progress.value  = false
                error.value     = Application.getString(R.string.error_to_get_event_detail)
            }
        }
    }
}