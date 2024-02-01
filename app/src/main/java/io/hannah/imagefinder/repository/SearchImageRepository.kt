package io.hannah.imagefinder.repository

import io.hannah.imagefinder.model.FlickrResponse

interface SearchImageRepository {
    suspend fun searchImageByTags(tags:List<String>): FlickrResponse
}