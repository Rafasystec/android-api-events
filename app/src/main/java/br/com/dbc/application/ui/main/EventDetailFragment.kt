package br.com.dbc.application.ui.main

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.dbc.application.R
import br.com.dbc.application.extention.loadImage
import br.com.dbc.application.extention.setupToolbar
import br.com.dbc.application.model.Event
import br.com.dbc.application.ui.dialog.CheckingDialog
import br.com.dbc.application.util.Constants
import br.com.dbc.application.util.DateTimeUtil
import kotlinx.android.synthetic.main.event_detail_fragment.*
import kotlinx.android.synthetic.main.event_list_adapter.*
import kotlinx.android.synthetic.main.layout_no_result_found.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat

class EventDetailFragment : Fragment() {

    companion object {
        fun newInstance() = EventDetailFragment()
    }

    private val viewModel: EventDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.event_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(R.id.toolbar,getString(R.string.event))
        val idEvent = requireActivity().intent.extras?.getLong(Constants.EVENT_ID)
        btCheckingEvent.setOnClickListener {
            CheckingDialog(idEvent!!).show(parentFragmentManager,"")
        }
        pbEventDetail.visibility = View.VISIBLE
        initObserverForEventDetail()
        initObserverForProgressBar()
        initObserverForError()
        viewModel.getEvent(idEvent!!)
    }

    private fun initObserverForError() {
        viewModel.error.observe(viewLifecycleOwner, Observer {error ->
            if(error.isNullOrEmpty()){
                showLayoutError(false,"")
            }else{
                showLayoutError(true,error)
            }

        })
    }

    private fun showLayoutError(showError:Boolean,message: String) {
        if(showError){
            tvErrorNoResult.text        = message;
            scrollView2.visibility      = View.GONE
            include.visibility          = View.GONE
            btCheckingEvent.visibility  = View.GONE
            clNoResult.visibility       = View.VISIBLE
        }else {
            scrollView2.visibility      = View.VISIBLE
            include.visibility          = View.VISIBLE
            btCheckingEvent.visibility  = View.VISIBLE
            clNoResult.visibility       = View.GONE
        }
    }

    private fun initObserverForProgressBar() {
        viewModel.progress.observe(viewLifecycleOwner, Observer { showProgress ->
            if (showProgress) {
                pbEventDetail.visibility = View.VISIBLE
            } else {
                pbEventDetail.visibility = View.GONE
            }
        })
    }

    private fun initObserverForEventDetail() {
        viewModel.eventLiveData.observe(viewLifecycleOwner, Observer { event ->
            setupFields(event)
        })
    }

    private fun setupFields(event: Event){
        tvEventDescription.text             = event.description
        tvEventTitle.text                   = event.title
        tvEventDescription.movementMethod   = ScrollingMovementMethod()
        tvEventDate.text                    = DateTimeUtil.formatDate(event.date!!,"dd/MM/yyyy HH:mm")
        tvEventPrice.text                   = NumberFormat.getCurrencyInstance().format(event.price)
        loadImage(event.image!!,pbEventDetail,ivEventImage)
    }

}