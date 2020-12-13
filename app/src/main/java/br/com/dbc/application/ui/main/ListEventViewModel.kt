package br.com.dbc.application.ui.main

import android.util.Log
import androidx.lifecycle.*
import br.com.dbc.application.model.Event
import br.com.dbc.application.service.ListEventService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListEventViewModel(private val service: ListEventService) : ViewModel() {

    init {
        //---------------------------------------------------
        //Just to check if we have only one instance
        //---------------------------------------------------
        Log.i("viewmodel","init view model $this")
        //---------------------------------------------------
    }

    val eventListLiveData   = MutableLiveData<List<Event>>()
    val error               = MutableLiveData<String>()
    val progress            = MutableLiveData<Boolean>()

    /**
     * Uso de Coroutine aqui também. Dispatchers.Main significa que será
     * mostrado o resultado na main thread, assim não presisamos usar o
     * postValue do ViewModel mas somente value
     */
    fun getEventList(){
        viewModelScope.launch(Dispatchers.Main) {
            val response = service.getEventList()
            if(response.isSuccessful){
                progress.value = false
                response.body().let {events ->
                    eventListLiveData.value = events
                }
            }else{
                progress.value = false
                error.value = "Não foi possível obter a lista de eventos!"
            }
        }
    }


}