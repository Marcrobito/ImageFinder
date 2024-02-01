package io.hannah.imagefinder.mvi

import io.hannah.imagefinder.model.FlickrImage

data class FlickrGallerySearchState(
    val data: List<FlickrImage> = listOf(),
    val isLoading: Boolean = false,
    val error:String? = null
)