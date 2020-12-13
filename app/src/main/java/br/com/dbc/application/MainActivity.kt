package br.com.dbc.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.dbc.application.ui.main.ListEventFragment
import br.com.dbc.application.util.ConnectivityUtil
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if(!ConnectivityUtil.isConnected(this)){
            showAlertNoNetworkAndCloseApplication()
        }else {
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.navHostFragment, ListEventFragment.newInstance())
                    .commitNow()
            }
        }
    }

    /**
     * Para exibir o alert utilizamos a lib anko
     */
    private fun showAlertNoNetworkAndCloseApplication() {
        alert(getString(R.string.need_a_connection), title = getString(R.string.advice)) {
            okButton {
                it.dismiss()
                finish()
            }
            this.onCancelled {
                it.dismiss()
                finish()
            }
        }.show()
    }
}