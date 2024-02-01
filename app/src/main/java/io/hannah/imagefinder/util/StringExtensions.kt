package io.hannah.imagefinder.util

fun String.removeHtmlTags(): String {
    val regex = "<.*?>".toRegex()
    return this.replace(regex, "")
}
