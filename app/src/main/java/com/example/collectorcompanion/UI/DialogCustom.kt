package com.example.collectorcompanion.UI

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.collectorcompanion.R
import com.example.collectorcompanion.UI.Fragment.AddCollectionFragment
import com.example.collectorcompanion.UI.Fragment.AddObjetFragment
import com.example.collectorcompanion.ViewModel.DatabaseViewModel

class DialogCustom : DialogFragment() {
    var dialog = this
    lateinit var modify : LinearLayout
    lateinit var erase : LinearLayout
    lateinit var cancel : LinearLayout
    var idCollection : Int? = null
    var idObjet : Int? = null
    var newFragmentManager : FragmentManager? = null
    var newContext : Context? = null
    var databaseViewModel : DatabaseViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_alert_custom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        modify = view.findViewById(R.id.dialog_modify)
        erase = view.findViewById(R.id.dialog_erase)
        cancel = view.findViewById(R.id.dialog_cancel)
        if(idCollection != null){
            modify.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    newFragmentManager!!.beginTransaction()
                        .setCustomAnimations(
                            R.anim.fragment_fade_enter,
                            R.anim.fragment_fade_exit,
                            R.anim.fragment_fade_enter,
                            R.anim.fragment_fade_exit)
                        .replace(R.id.fragment_container, AddCollectionFragment.newInstance(idCollection))
                        .commit()
                    dialog.dismiss()
                }

            })
            erase.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    val builder2 = AlertDialog.Builder(context)
                    builder2.setTitle(newContext!!.getString(R.string.Warning))
                    builder2.setMessage(newContext!!.getString(R.string.ConfirmDeleteCollection))
                    builder2.setPositiveButton(newContext!!.getString(R.string.Yes)) { dialog, which ->
                        databaseViewModel!!.deleteCollection(idCollection!!)
                    }
                    builder2.setNegativeButton(newContext!!.getString(R.string.No)) { dialog, which ->
                    }
                    builder2.show()
                    dialog.dismiss()
                }
            })
        }
        if(idObjet != null){
            modify.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    newFragmentManager!!.beginTransaction()
                        .setCustomAnimations(
                            R.anim.fragment_fade_enter,
                            R.anim.fragment_fade_exit,
                            R.anim.fragment_fade_enter,
                            R.anim.fragment_fade_exit)
                        .replace(R.id.fragment_container, AddObjetFragment.newInstance(idObjet))
                        .commit()
                    dialog.dismiss()
                }

            })
            erase.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    val builder2 = AlertDialog.Builder(context)
                    builder2.setTitle(newContext!!.getString(R.string.Warning))
                    builder2.setMessage(newContext!!.getString(R.string.ConfirmDeleteObject))
                    builder2.setPositiveButton(newContext!!.getString(R.string.Yes)) { dialog, which ->
                        databaseViewModel!!.deleteObjet(idObjet!!)
                    }
                    builder2.setNegativeButton(newContext!!.getString(R.string.No)) { dialog, which ->
                    }
                    builder2.show()
                    dialog.dismiss()
                }
            })
        }

        cancel.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                dialog.dismiss()
            }
        })
    }
}