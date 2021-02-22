package com.example.collectorcompanion.UI.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.collectorcompanion.Model.Application.GlobalApplication
import com.example.collectorcompanion.Model.Entity.Collection
import com.example.collectorcompanion.Model.Entity.Objet
import com.example.collectorcompanion.R
import com.example.collectorcompanion.ViewModel.DatabaseViewModel
import com.example.collectorcompanion.ViewModel.DatabaseViewModelFactory
import com.example.collectorcompanion.ViewModel.SearchDetailViewModel


class AddSearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databaseViewModel : DatabaseViewModel by viewModels {
            DatabaseViewModelFactory(( requireActivity().application as GlobalApplication).repository)
        }

        var objet = SearchDetailViewModel.objet!!
        var idChoose = 0
        var spinner : Spinner = view.findViewById(R.id.spinner_search)
        var listCollection : List<Collection>? = null
        databaseViewModel.allCollections.observe(viewLifecycleOwner){ it ->
            listCollection = it
            var spinnerAdapter : ArrayAdapter<String> = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1)
            for (collection in listCollection!!){
                spinnerAdapter.add(collection.collectionName)
            }
            spinner.adapter = spinnerAdapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    idChoose = listCollection!![position].idCollection
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
            var button : Button = view.findViewById(R.id.button_save_search_confirm)
            button.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    spinner.selectedItem.toString()
                    databaseViewModel.insert(Objet(objet.objetName,objet.objetPhoto,objet.objetYear,objet.objetDescription,objet.idBrand,idChoose,objet.idType))
                    parentFragmentManager.popBackStack()
                    parentFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit,R.anim.fragment_fade_enter,R.anim.fragment_fade_exit)
                        .replace(R.id.fragment_container, CollectionFragment())
                        .commit()
                }
            })
        }
    }
}