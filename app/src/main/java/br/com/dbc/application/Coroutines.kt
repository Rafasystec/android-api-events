package br.com.dbc.application

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {

    fun main(invoke: suspend (()-> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            invoke
        }
}