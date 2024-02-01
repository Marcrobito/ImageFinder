package io.hannah.imagefinder.model

data class FlickrImage(
    val title: String,
    val link: String,
    val media: String,
    val dateTaken: String,
    val description: String,
    val published: String,
    val author: String,
    val authorId: String,
    val tags: String
)

