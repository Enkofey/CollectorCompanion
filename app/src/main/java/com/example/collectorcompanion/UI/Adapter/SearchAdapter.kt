package com.example.collectorcompanion.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.collectorcompanion.Model.Entity.Objet
import com.example.collectorcompanion.R
import com.example.collectorcompanion.UI.DialogCustom
import com.example.collectorcompanion.UI.Fragment.AddObjetFragment
import com.example.collectorcompanion.UI.Fragment.ObjetDetailFragment
import com.example.collectorcompanion.UI.Fragment.SearchDetailFragment
import com.example.collectorcompanion.ViewModel.DatabaseViewModel

class SearchAdapter (private val clickListener : SearchAdapter.SearchListAdapterClickListener, val context : Context, val fragmentManager: FragmentManager, val databaseViewModel: DatabaseViewModel) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var adapterSearchList = emptyList<Objet>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.search_item, viewGroup, false)
            return ViewHolder(view)
            //Create viewholder for your images
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if(position < adapterSearchList.size){
            val mySearch = adapterSearchList[position]
            viewHolder.itemView.setOnClickListener {
                clickListener.onClick(position,mySearch)
            }
            if(mySearch.objetPhoto != null) {
                Glide.with(context)
                    .load(mySearch.objetPhoto).placeholder(R.drawable.placeholderimage_24).override(200)
                    .into(viewHolder.itemView.findViewById<ImageView>(R.id.image_search))
                viewHolder.itemView.findViewById<TextView>(R.id.text_search).text = "${mySearch.objetName}"
            }
            viewHolder.itemView.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    fragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.fragment_fade_enter,
                            R.anim.fragment_fade_exit,
                            R.anim.fragment_fade_enter,
                            R.anim.fragment_fade_exit)
                        .replace(R.id.fragment_container, SearchDetailFragment.newInstance(mySearch))
                        .commit()
                }
            })
        }
    }

    fun setData(eventList: List<Objet>) {
        this.adapterSearchList = eventList
        notifyDataSetChanged()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = adapterSearchList.size

    interface SearchListAdapterClickListener {
        fun onClick(dataPosition: Int, event: Objet)
    }
}