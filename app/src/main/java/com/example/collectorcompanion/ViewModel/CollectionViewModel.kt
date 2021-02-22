package com.example.collectorcompanion.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CollectionViewModel: ViewModel() {
    companion object Factory: ViewModelProvider.Factory{

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CollectionViewModel() as T
        }

    }
}