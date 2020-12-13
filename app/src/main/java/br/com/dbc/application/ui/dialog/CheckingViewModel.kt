package br.com.dbc.application.ui.dialog

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dbc.application.model.Checkin
import br.com.dbc.application.service.ListEventService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
const val TAG_DO_CHECKIN = "CHECK_IN"
class CheckingViewModel (private val service: ListEventService) : ViewModel(){
    val responseOkLiveData  = MutableLiveData<Boolean>()
    val error               = MutableLiveData<String>()
    val progress            = MutableLiveData<Boolean>()

    fun doCheckIn(checkin: Checkin){
        progress.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val response    = service.doCheckIn(checkin)
            Log.d(TAG_DO_CHECKIN,"Http code : ${response.code()}")
            if(response.isSuccessful){
                progress.value              = false
                responseOkLiveData.value    = true
            }else{
                progress.value              = false
                responseOkLiveData.value    = false
                error.value                 = "Não foi possível realizar o checkin nesse Evento!"
            }
        }
    }

}