package com.example.collectorcompanion.UI.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.example.collectorcompanion.Model.Application.GlobalApplication
import com.example.collectorcompanion.Model.Entity.Objet
import com.example.collectorcompanion.R
import com.example.collectorcompanion.ViewModel.DatabaseViewModel
import com.example.collectorcompanion.ViewModel.DatabaseViewModelFactory
import com.example.collectorcompanion.ViewModel.SearchDetailViewModel

class SearchDetailFragment : Fragment(){

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_search_detail, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            var textName : TextView = view.findViewById(R.id.textName_search_detail)
            var textBrand : TextView = view.findViewById(R.id.textBrand_search_detail)
            var textYear : TextView = view.findViewById(R.id.textYear_search_detail)
            var textDescription : TextView = view.findViewById(R.id.textDescription_search_detail)
            var PhotoImage : ImageView = view.findViewById(R.id.photo_search_detail)

            if(SearchDetailViewModel.objet != null){
                var objet = SearchDetailViewModel.objet!!
                    textName.text = objet.objetName
                    textYear.text = objet.objetYear
                    textDescription.text = objet.objetDescription
                    Glide.with(requireContext())
                        .load(objet.objetPhoto).override(500)
                        .into(view.findViewById(R.id.photo_search_detail))
                var button : Button = view.findViewById(R.id.button_save_search)
                button.setOnClickListener(object : View.OnClickListener{
                    override fun onClick(v: View?) {
                        parentFragmentManager.beginTransaction()
                            .setCustomAnimations(
                                R.anim.fragment_fade_enter,
                                R.anim.fragment_fade_exit,
                                R.anim.fragment_fade_enter,
                                R.anim.fragment_fade_exit)
                            .replace(R.id.fragment_container, AddSearchFragment())
                            .commit()
                    }
                })
            }
        }

        companion object{
            fun newInstance(objet : Objet): SearchDetailFragment {
                SearchDetailViewModel.objet = objet
                val fragment = SearchDetailFragment()
                return fragment
            }
        }
}