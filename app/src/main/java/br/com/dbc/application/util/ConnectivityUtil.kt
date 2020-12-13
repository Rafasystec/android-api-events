package br.com.dbc.application.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object ConnectivityUtil {

    fun isConnected(context: Context):Boolean{
        val networkInfo = getNetworkInfo(context)
        return networkInfo?.isConnectedOrConnecting ?: false
    }

    private fun getNetworkInfo(context: Context) : NetworkInfo?{
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return manager.activeNetworkInfo
    }
}