package com.example.collectorcompanion.UI.Adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.collectorcompanion.Model.Application.GlobalApplication
import com.example.collectorcompanion.R
import com.example.collectorcompanion.Model.Entity.Collection
import com.example.collectorcompanion.UI.DialogCustom
import com.example.collectorcompanion.UI.Fragment.AddCollectionFragment
import com.example.collectorcompanion.UI.Fragment.AddObjetFragment
import com.example.collectorcompanion.UI.Fragment.ObjetFragment
import com.example.collectorcompanion.ViewModel.DatabaseViewModel
import com.example.collectorcompanion.ViewModel.DatabaseViewModelFactory

class CollectionAdapter(private val clickListener : CollectionListAdapterClickListener, val context : Context, val fragmentManager: FragmentManager, val databaseViewModel: DatabaseViewModel) : RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    var adapterCollectionList = emptyList<Collection>()
    private val VIEW_TYPE_DEFAULT = 0
    private val VIEW_TYPE_IMAGE = 1

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item

        if (viewType == VIEW_TYPE_IMAGE) {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.collection_item, viewGroup, false)

            return ViewHolder(view)
            //Create viewholder for your images
        }
        else {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.collection_item, viewGroup, false)

            return ViewHolder(view)
            //Create viewholder for default view
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if(position < adapterCollectionList.size){
            val myCollection = adapterCollectionList[position]
            viewHolder.itemView.setOnClickListener {
                clickListener.onClick(position,myCollection)
            }
            Glide.with(context)
                .load(myCollection.collectionImage).placeholder(R.drawable.placeholderimage_24)
                .into(viewHolder.itemView.findViewById<ImageView>(R.id.image_collection));
            viewHolder.itemView.findViewById<TextView>(R.id.text_collection).text = "${myCollection.collectionName}"

            viewHolder.itemView.setOnClickListener(object:View.OnClickListener{
                override fun onClick(v: View?) {
                    fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit,R.anim.fragment_fade_enter,R.anim.fragment_fade_exit)
                        .replace(R.id.fragment_container, ObjetFragment.newInstance(myCollection.idCollection))
                        .commit()
                }
            })
            viewHolder.itemView.setOnLongClickListener(object:View.OnLongClickListener{
                override fun onLongClick(v: View?): Boolean {
                    var dialog = DialogCustom()
                    dialog.newFragmentManager = fragmentManager
                    dialog.newContext = context
                    dialog.idCollection = myCollection.idCollection
                    dialog.databaseViewModel = databaseViewModel
                    dialog.show(fragmentManager,"")
                    return true
                }
            })
        }
        else{
            Glide.with(context).load(R.drawable.ic_baseline_add_24).override(200).into(viewHolder.itemView.findViewById(R.id.image_collection))
            viewHolder.itemView.findViewById<TextView>(R.id.text_collection).text = "Ajouter"
            viewHolder.itemView.setOnClickListener(object:View.OnClickListener{
                override fun onClick(v: View?) {
                    fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit,R.anim.fragment_fade_enter,R.anim.fragment_fade_exit)
                        .replace(R.id.fragment_container, AddCollectionFragment())
                        .commit()
                }
            })
        }
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
    }

    fun setData(eventList: List<Collection>) {
        this.adapterCollectionList = eventList
        notifyDataSetChanged()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = adapterCollectionList.size+1

    interface CollectionListAdapterClickListener {
        fun onClick(dataPosition: Int, event: Collection)
    }

    override fun getItemViewType(position: Int) : Int {
        if(adapterCollectionList.size == position){
            return VIEW_TYPE_DEFAULT
        }else{
            return VIEW_TYPE_IMAGE
        }
    }

}