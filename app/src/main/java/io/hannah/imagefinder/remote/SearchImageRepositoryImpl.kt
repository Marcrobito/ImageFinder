package io.hannah.imagefinder.remote

import io.hannah.imagefinder.model.FlickrResponse
import io.hannah.imagefinder.model.NetworkRequest
import io.hannah.imagefinder.model.RequestError
import io.hannah.imagefinder.network.FlickrApi
import io.hannah.imagefinder.repository.SearchImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchImageRepositoryImpl @Inject constructor(
    private val flickrApi: FlickrApi
) : SearchImageRepository {

    override suspend fun searchImageByTags(tags: List<String>): FlickrResponse {
        // Convert the list of tags into a comma-separated string
        val tagsCommaSeparatedString = tags.joinToString(",")

        return try {
            // Perform a network request within the IO context
            val result = withContext(Dispatchers.IO) {
                flickrApi.getImagesByTag(tags = tagsCommaSeparatedString)
            }

            // Map the network response to a success with a list of FlickrImage objects
            NetworkRequest.Success(result.items.mapToFlickrImage())

        } catch (e: Exception) {
            // Handle any exceptions and create an error object with the appropriate code and message
            NetworkRequest.Error(
                RequestError(
                    errorCode = 500,
                    message = e.message ?: "Something went wrong"
                )
            )
        }
    }
}

