package br.com.dbc.application.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import br.com.dbc.application.R
import br.com.dbc.application.model.Checkin
import kotlinx.android.synthetic.main.cheking_dialog.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckingDialog(pEventId:Long) : DialogFragment() {
    private val localEventId = pEventId
    private val viewModel: CheckingViewModel by viewModel()
    init {
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cheking_dialog,container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDoCheckInListener()
        initObserverForDoCheckIn()
        initObserverForProgressBar()
    }

    private fun setDoCheckInListener() {
        btDoChecking.setOnClickListener {
            if (validate()) {
                viewModel.doCheckIn(formFieldsToCheckIn())
            }
        }
    }

    private fun initObserverForProgressBar() {
        viewModel.progress.observe(viewLifecycleOwner, Observer { showProgress ->
            if (showProgress) {
                pbDoCheckIn.visibility = View.VISIBLE
            } else {
                pbDoCheckIn.visibility = View.GONE
            }
        })
    }

    private fun initObserverForDoCheckIn() {
        viewModel.responseOkLiveData.observe(viewLifecycleOwner, Observer { sucess ->
            if (sucess) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.do_check_in_sucess),
                    Toast.LENGTH_LONG
                ).show()
                dismiss()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.do_check_in_failed),
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun formFieldsToCheckIn() : Checkin{
        return Checkin().apply {
            eventId = localEventId
            name    = tvCheckInName.text.toString()
            email   = tvCheckInEmail.text.toString()
        }
    }

    private fun validate() =
        when {
            tvCheckInName.text.isNullOrEmpty() -> {
                tvCheckInName.error = getString(R.string.please_set_the_name)
                false
            }
            tvCheckInEmail.text.isNullOrEmpty() -> {
                tvCheckInEmail.error = getString(R.string.please_set_the_email)
                false
            }
            else -> {
                true
            }
        }


}