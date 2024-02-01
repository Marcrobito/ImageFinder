package io.hannah.imagefinder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.hannah.imagefinder.model.NetworkRequest
import io.hannah.imagefinder.mvi.FlickrGallerySearchEvent
import io.hannah.imagefinder.mvi.FlickrGallerySearchState
import io.hannah.imagefinder.mvi.stateReducerFlow
import io.hannah.imagefinder.repository.SearchImageRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlickrGallerySearchViewModel @Inject constructor(private val repository: SearchImageRepository) :
    ViewModel() {

    val state = stateReducerFlow(
        FlickrGallerySearchState(),
        ::reducer
    )

    private fun reducer(
        initialState: FlickrGallerySearchState,
        event: FlickrGallerySearchEvent
    ): FlickrGallerySearchState {
        return when (event) {
            is FlickrGallerySearchEvent.SearchByTag -> {
                searchImagesByTags(event.tags)
                initialState.copy(
                    isLoading = true,
                    error = null
                )
            }

            is FlickrGallerySearchEvent.ImagesFetched -> initialState.copy(
                isLoading = false,
                data = event.data,
                error = null
            )

            is FlickrGallerySearchEvent.RequestFailed -> initialState.copy(
                isLoading = false,
                error = event.error
            )
        }
    }

    private fun searchImagesByTags(tags: String) {
        val tagList = tags.split(" ")
        viewModelScope.launch {

            when (val result = repository.searchImageByTags(tagList)) {
                is NetworkRequest.Success -> {
                    if (result.data.isNotEmpty())
                        state.handleEvent(FlickrGallerySearchEvent.ImagesFetched(result.data))
                }


                is NetworkRequest.Error -> state.handleEvent(
                    FlickrGallerySearchEvent.RequestFailed(result.error.message)
                )
            }
        }
    }

}
