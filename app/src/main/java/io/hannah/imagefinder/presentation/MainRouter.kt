package io.hannah.imagefinder.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.hannah.imagefinder.model.FlickrImage

private const val GALLERY_ROUTE = "gallery"
private const val DETAIL_ROUTE = "detail"

@Composable
fun MainRouter() {
    val navController = rememberNavController()
    val flickrImage = remember {
        mutableStateOf<FlickrImage?>(null)
    }
    NavHost(
        navController = navController,
        startDestination = GALLERY_ROUTE
    ) {
        composable(GALLERY_ROUTE) {
            FlickrGallerySearchView(
                onImageSelected = {
                    flickrImage.value = it
                    navController.navigate(DETAIL_ROUTE)
                }
            )
        }
        composable(DETAIL_ROUTE) {
            flickrImage.value?.let { image -> DetailScreen(flickrImage = image) }
        }
    }
}