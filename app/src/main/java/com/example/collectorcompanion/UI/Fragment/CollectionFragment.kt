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
import com.example.collectorcompanion.Model.Entity.Collection
import com.example.collectorcompanion.R
import com.example.collectorcompanion.UI.Adapter.CollectionAdapter
import com.example.collectorcompanion.ViewModel.CollectionViewModel
import com.example.collectorcompanion.ViewModel.DatabaseViewModel
import com.example.collectorcompanion.ViewModel.DatabaseViewModelFactory
import com.example.collectorcompanion.ViewModel.DataSharedViewModel

class CollectionFragment : Fragment(),CollectionAdapter.CollectionListAdapterClickListener {
    lateinit var listFragmentView: View
    //lateinit var applicationViewModel: ApplicationViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var collectionAdapter: CollectionAdapter
    private lateinit var collectionViewModel: CollectionViewModel
    private val viewModel: DataSharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databaseViewModel : DatabaseViewModel by viewModels {
            DatabaseViewModelFactory(( requireActivity().application as GlobalApplication).repository)
        }

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_collection)
        val collectionListGridLayoutManager = GridLayoutManager(this.context,3
        )
        recyclerView.layoutManager = collectionListGridLayoutManager
        collectionAdapter = CollectionAdapter(this,requireContext(),parentFragmentManager,databaseViewModel)
        recyclerView.adapter = collectionAdapter
        //gridView.layout= LinearLayoutManager(requireContext())

        if(viewModel.IsANewCollection){
            databaseViewModel.insert(viewModel.newCollection!!)
            viewModel.SetIsANewCollection(false)
        }
        if(viewModel.IsAModifyCollection){
            databaseViewModel.updateCollection(viewModel.modifyCollection!!)
            viewModel.SetIsAModifyCollection(false)
        }

        databaseViewModel.allCollections.observe(viewLifecycleOwner) { collections ->
            // Update the cached copy of the words in the adapter.
            collectionAdapter.setData(collections)
        }
    }

    override fun onClick(dataPosition: Int, event: Collection) {
        //collectionViewModel. = event
        //findNavController().navigate(R.id.action_nav_eventlist_to_event_detail)
    }
}