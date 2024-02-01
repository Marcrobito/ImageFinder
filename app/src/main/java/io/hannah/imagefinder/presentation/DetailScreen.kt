package io.hannah.imagefinder.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import io.hannah.imagefinder.model.FlickrImage
import io.hannah.imagefinder.util.removeHtmlTags

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    flickrImage: FlickrImage
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        GlideImage(
            model = flickrImage.media,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Author: ",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray.copy(alpha = 0.7f)
                )
                Text(
                    text = flickrImage.author,
                    fontSize = 12.sp,
                    color = Color.Gray.copy(alpha = 0.7f)
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = flickrImage.title,
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = flickrImage.description.removeHtmlTags().trim(),
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
            /*Text(
            text = "Width: ${flickrImage.width}, Height: ${flickrImage.height}",
            fontSize = 16.sp
            )*/
        }


    }
}