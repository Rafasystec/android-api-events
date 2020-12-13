package br.com.dbc.application.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import br.com.dbc.application.R
import br.com.dbc.application.extention.defaultRecycleView
import br.com.dbc.application.extention.setupToolbar
import br.com.dbc.application.extention.toast
import br.com.dbc.application.model.Event
import br.com.dbc.application.util.Constants
import kotlinx.android.synthetic.main.layout_no_result_found.*
import kotlinx.android.synthetic.main.main_fragment.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListEventFragment : Fragment() {

    companion object {
        fun newInstance() = ListEventFragment()
    }

    var rcEventList: RecyclerView?=null
    private val viewModel: ListEventViewModel by viewModel()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(R.id.toolbar,getString(R.string.events))
        rcEventList = defaultRecycleView(requireActivity(),R.id.rcEventList)
        rcEventList?.adapter= EventAdapter(listOf()) {
        }
        pbEventList.visibility = View.VISIBLE
        viewModel.eventListLiveData.observe(viewLifecycleOwner, Observer {events ->
           loadEventList(events)
        })
        viewModel.progress.observe(viewLifecycleOwner, Observer {showProgress ->
            if(showProgress){
                pbEventList.visibility = View.VISIBLE
            }else{
                pbEventList.visibility = View.GONE
            }
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity,it,Toast.LENGTH_LONG).show()
        })
        viewModel.getEventList()

    }

    private fun loadEventList(events:List<Event>){
        if(events.isEmpty()){
            clNoResult.visibility = View.VISIBLE
        }else{
            clNoResult.visibility = View.GONE
            rcEventList?.adapter = EventAdapter(events) { event ->
                eventDetail(event)
            }
        }
    }

    private fun eventDetail(event: Event){
        val id = event.id
        requireActivity().startActivity<EventDetailActivity>(Constants.EVENT_ID to id)
    }

}