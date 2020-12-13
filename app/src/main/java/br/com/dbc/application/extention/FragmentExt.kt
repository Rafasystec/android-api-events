package br.com.dbc.application.extention

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.dbc.application.R
import br.com.dbc.application.model.Event
import br.com.dbc.application.ui.main.EventAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.okButton


/**
 * Created by Rafael Rocha on 07/08/2018.
 */
fun Fragment.defaultRecycleView(view: FragmentActivity, resId:Int): RecyclerView {
    val recycleView = view.findViewById<RecyclerView>(resId)
    recycleView?.layoutManager = LinearLayoutManager(activity)
    recycleView?.itemAnimator = DefaultItemAnimator()
    recycleView?.setHasFixedSize(true)
    return recycleView
}

fun Fragment.defaultRecycleView(view: View, resId:Int): RecyclerView{
    val recycleView = view.findViewById<RecyclerView>(resId)
    recycleView?.layoutManager = LinearLayoutManager(activity)
    recycleView?.itemAnimator = DefaultItemAnimator()
    recycleView?.setHasFixedSize(true)
    return recycleView
}

fun Fragment.setupToolbar(@IdRes id: Int, title:String?= null, upNavigation: Boolean = false) : ActionBar {

    val activityCompat = activity as AppCompatActivity
    val toolbar = activity?.findViewById<Toolbar>(id)
    activityCompat.setSupportActionBar(toolbar)
    if(title != null){
        activityCompat.supportActionBar?.title = title
    }
    activityCompat.supportActionBar?.setHomeButtonEnabled(true)
    return activityCompat.supportActionBar!!
}

fun Fragment.toast(message: CharSequence, length: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(context,message,length).show()

fun Fragment.showAlertError(message:String){
    activity?.alert(message,activity?.getString(R.string.error)!!){
        okButton{ it.dismiss() }
    }?.show()
}

fun Fragment.showAlertError(@StringRes idResource: Int){
    showAlertError(activity?.getString(idResource)!!)
}

fun Fragment.showAlertFailure(message:String){
    activity?.alert(message,activity?.getString(R.string.advice)!!){
        okButton{ it.dismiss() }
    }?.show()
}

fun Fragment.showAlertFailure(@StringRes idResource: Int){
    showAlertFailure(activity?.getString(idResource)!!)
}


fun Fragment.loadImage(imageUrl: String, progressBar: ProgressBar, imageView: ImageView){
    if(imageUrl.isEmpty()){
        imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_no_image))
    }else {
        progressBar.visibility  = View.VISIBLE
        imageView.visibility    = View.INVISIBLE
        Picasso.with(requireContext())
            .load(imageUrl)
            .error(R.drawable.ic_no_image)
            .fit()
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility  = View.GONE
                    imageView.visibility    = View.VISIBLE
                }
                override fun onError() {
                    progressBar.visibility  = View.GONE
                    imageView.visibility    = View.VISIBLE
                }
            })
    }
}