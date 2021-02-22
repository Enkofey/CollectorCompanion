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
import com.example.collectorcompanion.UI.Adapter.CollectionAdapter
import com.example.collectorcompanion.UI.Adapter.SearchAdapter
import com.example.collectorcompanion.ViewModel.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment(), SearchAdapter.SearchListAdapterClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    lateinit var listFragmentView: View
    //lateinit var applicationViewModel: ApplicationViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var searchAdapter: SearchAdapter
    private val viewModel: DataSharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val databaseViewModel : DatabaseViewModel by viewModels {
            DatabaseViewModelFactory(( requireActivity().application as GlobalApplication).repository)
        }

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_search)
        val searchListGridLayoutManager = GridLayoutManager(this.context,1)
        recyclerView.layoutManager = searchListGridLayoutManager
        searchAdapter = SearchAdapter(this,requireContext(),parentFragmentManager,databaseViewModel)
        recyclerView.adapter = searchAdapter
        //gridView.layout= LinearLayoutManager(requireContext())

        ApiViewModel.getObjets()
            ApiViewModel.objets.observe(viewLifecycleOwner) { objets ->
                searchAdapter.setData(objets)
            }
    }

    override fun onClick(dataPosition: Int, event: Objet) {

    }
}