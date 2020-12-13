package br.com.dbc.application.application

import androidx.annotation.StringRes
import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level

class Application : MultiDexApplication() {

    var koinApplication: KoinApplication? = null
    override fun onCreate() {
        super.onCreate()
        app = this
        initKoin()
    }

    companion object {
        private var app : Application?=null
        fun getInstance(): Application{
            if(app == null){
                throw IllegalStateException("Configure the Application class on Manifest xml.")
            }
            return app!!
        }
        fun getString(@StringRes resId:Int ) : String{
            return app?.getString(resId)!!
        }
    }

    private fun initKoin(){
        stopKoin()
        koinApplication?.close()
        koinApplication = null

        koinApplication = startKoin {
            androidContext(this@Application)
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    repositories,
                    services,
                    viewModels
                )
            )
        }

    }
}