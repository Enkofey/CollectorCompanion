package com.example.collectorcompanion.UI.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.collectorcompanion.Model.Application.GlobalApplication
import com.example.collectorcompanion.Model.Entity.Objet
import com.example.collectorcompanion.R
import com.example.collectorcompanion.UI.Adapter.ObjetAdapter
import com.example.collectorcompanion.ViewModel.*

class ObjetFragment : Fragment(), ObjetAdapter.ObjetListAdapterClickListener {

    lateinit var listFragmentView: View
    lateinit var recyclerView: RecyclerView
    lateinit var objetAdapter: ObjetAdapter
    private lateinit var objetViewModel: ObjetViewModel
    private val viewModel: DataSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_objet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databaseViewModel : DatabaseViewModel by viewModels {
            DatabaseViewModelFactory(( requireActivity().application as GlobalApplication).repository)
        }

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_objet)
        val objetListGridLayoutManager = GridLayoutManager(this.context,3)
        recyclerView.layoutManager = objetListGridLayoutManager
        objetAdapter = ObjetAdapter(this,requireContext(),parentFragmentManager,databaseViewModel)
        recyclerView.adapter = objetAdapter
        //gridView.layout= LinearLayoutManager(requireContext())

        if(viewModel.IsANewObjet){
            databaseViewModel.insert(viewModel.newObjet!!)
            viewModel.SetIsANewObjet(false)
        }

        if(viewModel.IsAModifyObjet){
            databaseViewModel.updateObjet(viewModel.modifyObjet!!)
            viewModel.SetIsAModifyObjet(false)
        }

        if(arguments?.getInt("collection_id") != null){
            viewModel.idCollectionCurrent = arguments?.getInt("collection_id")!!
            databaseViewModel.getObjetWithCollectionID(arguments?.getInt("collection_id")!!)
            databaseViewModel.objetWithCollectionId!!.observe(viewLifecycleOwner){ objects ->
                objetAdapter.setData(objects)
            }
        }
        else{
            if(viewModel.idCollectionCurrent != null){
                databaseViewModel.getObjetWithCollectionID(arguments?.getInt("collection_id")!!)
                databaseViewModel.objetWithCollectionId!!.observe(viewLifecycleOwner){ objects ->
                    objetAdapter.setData(objects)
                }
            }
        }
    }

    companion object{

        const val COLLECTION_ID = "collection_id"

        fun newInstance(id : Int): ObjetFragment {
            val args = Bundle()
            args.putInt(COLLECTION_ID, id)
            val fragment = ObjetFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onClick(dataPosition: Int, event: Objet) {

    }
}