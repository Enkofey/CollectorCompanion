package com.example.collectorcompanion.UIModel

import androidx.lifecycle.*
import com.example.collectorcompanion.Model.Repository.ApplicationRepository
import com.example.collectorcompanion.Model.Entity.Collection
import kotlinx.coroutines.launch

class ApplicationViewModel(private val repository: ApplicationRepository) : ViewModel() {

        // Using LiveData and caching what allWords returns has several benefits:
        // - We can put an observer on the data (instead of polling for changes) and only update the
        //   the UI when the data actually changes.
        // - Repository is completely separated from the UI through the ViewModel.
        val allCollection: LiveData<List<Collection>> = repository.allCollections.asLiveData()
        /**
         * Launching a new coroutine to insert the data in a non-blocking way
         */
        fun insert(collection: Collection) = viewModelScope.launch {
            repository.insert(collection)
        }
}

class ApplicationViewModelFactory(private val repository: ApplicationRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApplicationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ApplicationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}