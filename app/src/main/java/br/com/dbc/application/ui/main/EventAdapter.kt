package br.com.dbc.application.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.dbc.application.R
import br.com.dbc.application.model.Event
import br.com.dbc.application.util.DateTimeUtil
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.event_list_adapter.view.*
import java.text.NumberFormat

class EventAdapter (
    val events:List<Event>,
    val onItemClick: (Event) -> Unit
): RecyclerView.Adapter<EventAdapter.EventHolder>() {


    class EventHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvTitle = view.tvEventTitle
        val tvPrice = view.tvEventPrice
        val tvDate  = view.tvEventDate
        val ivImage = view.ivEventImage
        val progress= view.pbEvent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_list_adapter,parent,false)
        return EventHolder(view)
    }

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        val event = events[position]
        holder.tvDate.text  = DateTimeUtil.formatDate(event.date!!,"dd/MM/yyyy HH:mm")
        holder.tvTitle.text = event.title
        holder.tvPrice.text = NumberFormat.getCurrencyInstance().format(event.price)
        holder.itemView.setOnClickListener{onItemClick(event)}
        loadImage(event = event,holder = holder)
    }

    private fun loadImage(event: Event,holder: EventHolder){
        if(event.image == null)event.image = ""
        if(event.image!!.isEmpty()){
            holder.ivImage.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_no_image))
        }else {
            holder.progress.visibility  = View.VISIBLE
            holder.ivImage.visibility   = View.INVISIBLE
            Picasso.with(holder.itemView.context)
                .load(event.image)
                .error(R.drawable.ic_no_image)
                .fit()
                .into(holder.ivImage, object : Callback {
                    override fun onSuccess() {
                        holder.progress.visibility = View.GONE
                        holder.ivImage.visibility = View.VISIBLE
                    }
                    override fun onError() {
                        holder.progress.visibility = View.GONE
                        holder.ivImage.visibility = View.VISIBLE
                    }

                })
        }
    }
}