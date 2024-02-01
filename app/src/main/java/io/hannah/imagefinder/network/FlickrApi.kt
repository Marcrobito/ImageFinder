package io.hannah.imagefinder.network

import io.hannah.imagefinder.remote.FlickrResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
    @GET("photos_public.gne")
    suspend fun getImagesByTag(
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("tags") tags: String
    ): FlickrResponseDTO
}