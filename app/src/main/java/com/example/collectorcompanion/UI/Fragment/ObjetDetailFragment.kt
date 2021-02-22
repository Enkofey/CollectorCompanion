package com.example.collectorcompanion.UI.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.example.collectorcompanion.Model.Application.GlobalApplication
import com.example.collectorcompanion.Model.Entity.Objet
import com.example.collectorcompanion.R
import com.example.collectorcompanion.ViewModel.DatabaseViewModel
import com.example.collectorcompanion.ViewModel.DatabaseViewModelFactory

class ObjetDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_objet_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databaseViewModel : DatabaseViewModel by viewModels {
            DatabaseViewModelFactory(( requireActivity().application as GlobalApplication).repository)
        }

        var textName : TextView = view.findViewById(R.id.textName_objet_detail)
        var textBrand : TextView = view.findViewById(R.id.textBrand_objet_detail)
        var textYear : TextView = view.findViewById(R.id.textYear_objet_detail)
        var textDescription : TextView = view.findViewById(R.id.textDescription_objet_detail)
        var PhotoImage : ImageView = view.findViewById(R.id.photo_objet_detail)

        if(arguments?.getInt("objet_id") != null){
            databaseViewModel.getObjetWithID(arguments?.getInt("objet_id")!!)
            databaseViewModel.objetWithId!!.observe(viewLifecycleOwner){objet->
                textName.text = objet.objetName
                textYear.text = objet.objetYear
                textDescription.text = objet.objetDescription
                Glide.with(requireContext())
                    .load(objet.objetPhoto).override(500)
                    .into(view.findViewById(R.id.photo_objet_detail))
            }
        }
    }

    companion object{

        const val OBJET_ID = "objet_id"

        fun newInstance(id : Int): ObjetDetailFragment {
            val args = Bundle()
            args.putInt(OBJET_ID, id)
            val fragment = ObjetDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}