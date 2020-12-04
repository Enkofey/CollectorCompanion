package com.example.collectorcompanion.UI.Activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.collectorcompanion.Model.Application.GlobalApplication
import com.example.collectorcompanion.R
import com.example.collectorcompanion.UI.Adapter.CollectionAdapter
import com.example.collectorcompanion.UIModel.ApplicationViewModel
import com.example.collectorcompanion.UIModel.ApplicationViewModelFactory

class Main2Activity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private val collectionViewModel: ApplicationViewModel by viewModels {
        ApplicationViewModelFactory((application as GlobalApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview2)
        val adapter = CollectionAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        collectionViewModel.allCollection.observe(owner = this) { collections ->
            // Update the cached copy of the words in the adapter.
            collections.let { adapter.submitList(it) }
        }
    }
}