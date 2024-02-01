package io.hannah.imagefinder.remote

import com.google.gson.annotations.SerializedName
import io.hannah.imagefinder.model.FlickrImage

data class FlickrImageDTO (
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("media") val media: Media,
    @SerializedName("date_taken") val dateTaken: String,
    @SerializedName("description") val description: String,
    @SerializedName("published") val published: String,
    @SerializedName("author") val author: String,
    @SerializedName("author_id") val authorId: String,
    @SerializedName("tags") val tags: String
)

data class Media(
    @SerializedName("m") val m: String
)
fun FlickrImageDTO.mapToFlickrImage() = FlickrImage(
    title = this.title,
    link = this.link,
    media = this.media.m,
    dateTaken = this.dateTaken,
    description = this.description,
    published = this.published,
    author = this.author,
    authorId = this.authorId,
    tags = this.tags
)

fun List<FlickrImageDTO>.mapToFlickrImage() = this.map { it.mapToFlickrImage() }