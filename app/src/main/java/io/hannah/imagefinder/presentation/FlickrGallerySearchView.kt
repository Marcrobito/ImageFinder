package io.hannah.imagefinder.presentation

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import io.hannah.imagefinder.R
import io.hannah.imagefinder.model.FlickrImage
import io.hannah.imagefinder.mvi.FlickrGallerySearchEvent.SearchByTag
import io.hannah.imagefinder.ui.component.CustomTextField

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FlickrGallerySearchView(
    flickrGallerySearchViewModel: FlickrGallerySearchViewModel = hiltViewModel(),
    onImageSelected: (FlickrImage) -> Unit = {}
) {

    val tags = remember { mutableStateOf("") }

    val state = flickrGallerySearchViewModel.state
    val isLoading = state.collectAsState().value.isLoading
    val data = state.collectAsState().value.data

    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT


    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            CustomTextField(
                value = tags.value,
                isLoading = isLoading,
                vectorIcon = Icons.Outlined.Search,
                onValueChange = {
                    tags.value = it
                    state.handleEvent(SearchByTag(tags.value))
                },
                hint = stringResource(id = R.string.enter_search_criteria),
            )
        }
        Box {

            if (data.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.enter_key_words_to_search_images))
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(if (isPortrait) 3 else 6),
                contentPadding = PaddingValues(0.dp),
            ) {
                items(data) { image ->
                    GlideImage(
                        model = image.media,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .aspectRatio(1f)
                            .clickable {
                                onImageSelected(image)
                            },
                        contentScale = ContentScale.Crop
                    )
                }
            }

            if (isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

        }
    }

}


@Composable
@Preview
fun FlickrGallerySearchViewPreview() {
    FlickrGallerySearchView()
}