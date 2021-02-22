package com.example.collectorcompanion.UI.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.collectorcompanion.Model.Entity.Objet
import com.example.collectorcompanion.R
import com.example.collectorcompanion.UI.DialogCustom
import com.example.collectorcompanion.UI.Fragment.AddObjetFragment
import com.example.collectorcompanion.UI.Fragment.ObjetDetailFragment
import com.example.collectorcompanion.UI.Fragment.ObjetFragment
import com.example.collectorcompanion.ViewModel.DataSharedViewModel
import com.example.collectorcompanion.ViewModel.DatabaseViewModel

class ObjetAdapter (private val clickListener : ObjetListAdapterClickListener, val context : Context, val fragmentManager: FragmentManager, val databaseViewModel: DatabaseViewModel) : RecyclerView.Adapter<ObjetAdapter.ViewHolder>() {

    var adapterObjetList = emptyList<Objet>()
    private val VIEW_TYPE_DEFAULT = 0
    private val VIEW_TYPE_IMAGE = 1

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item

        if (viewType == VIEW_TYPE_IMAGE) {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.objet_item, viewGroup, false)

            return ViewHolder(view)
            //Create viewholder for your images
        }
        else {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.objet_item, viewGroup, false)

            return ViewHolder(view)
            //Create viewholder for default view
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if(position < adapterObjetList.size){
            val myObjet = adapterObjetList[position]
            viewHolder.itemView.setOnClickListener {
                clickListener.onClick(position,myObjet)
            }
            Glide.with(context)
                .load(myObjet.objetPhoto).placeholder(R.drawable.placeholderimage_24).override(200)
                .into(viewHolder.itemView.findViewById<ImageView>(R.id.image_objet))
            viewHolder.itemView.findViewById<TextView>(R.id.text_objet).text = "${myObjet.objetName}"

            viewHolder.itemView.setOnClickListener(object :View.OnClickListener{
                override fun onClick(v: View?) {
                    fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit,R.anim.fragment_fade_enter,R.anim.fragment_fade_exit)
                        .replace(R.id.fragment_container, ObjetDetailFragment.newInstance(myObjet.idObjet))
                        .commit()
                }
            })

            viewHolder.itemView.setOnLongClickListener(object:View.OnLongClickListener{
                override fun onLongClick(v: View?): Boolean {
                    var dialog = DialogCustom()
                    dialog.newFragmentManager = fragmentManager
                    dialog.newContext = context
                    dialog.idObjet = myObjet.idObjet
                    dialog.databaseViewModel = databaseViewModel
                    dialog.show(fragmentManager,"")
                    return true
                }
            })
        }
        else{
            Glide.with(context).load(R.drawable.ic_baseline_add_24).override(200).into(viewHolder.itemView.findViewById(
                R.id.image_objet))
            viewHolder.itemView.findViewById<TextView>(R.id.text_objet).text = "Ajouter"
            viewHolder.itemView.setOnClickListener(object: View.OnClickListener{
                override fun onClick(v: View?) {
                    fragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.fragment_fade_enter,
                            R.anim.fragment_fade_exit,
                            R.anim.fragment_fade_enter,
                            R.anim.fragment_fade_exit)
                        .replace(R.id.fragment_container, AddObjetFragment())
                        .commit()
                }
            })
        }
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

    }

    fun setData(eventList: List<Objet>) {
        this.adapterObjetList = eventList
        notifyDataSetChanged()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = adapterObjetList.size+1

    interface ObjetListAdapterClickListener {
        fun onClick(dataPosition: Int, event: Objet)
    }

    override fun getItemViewType(position: Int) : Int {
        if(adapterObjetList.size == position){
            return VIEW_TYPE_DEFAULT
        }else{
            return VIEW_TYPE_IMAGE
        }
    }


}