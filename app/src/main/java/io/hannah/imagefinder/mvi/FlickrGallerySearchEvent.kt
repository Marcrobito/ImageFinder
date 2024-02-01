package io.hannah.imagefinder.mvi

import io.hannah.imagefinder.model.FlickrImage

sealed class FlickrGallerySearchEvent {
    data class SearchByTag(val tags: String) : FlickrGallerySearchEvent()
    data class ImagesFetched(val data:List<FlickrImage>) : FlickrGallerySearchEvent()
    data class RequestFailed(val error:String) : FlickrGallerySearchEvent()
}
