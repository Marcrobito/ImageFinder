package io.hannah.imagefinder.remote

import io.hannah.imagefinder.model.NetworkRequest
import io.hannah.imagefinder.network.FlickrApi
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
class SearchImageRepositoryImplTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private lateinit var repository: SearchImageRepositoryImpl
    private lateinit var flickrApi: FlickrApi


    private val media = Media("Mocked Media URL")
    private val flickrImageDTOMock = FlickrImageDTO(
        title = "Mocked Title 1",
        link = "Mocked Link 1",
        media = media,
        dateTaken = "Mocked Date Taken 1",
        description = "Mocked Description 1",
        published = "Mocked Published 1",
        author = "Mocked Author 1",
        authorId = "Mocked Author ID 1",
        tags = "Mocked Tags 1"
    )

    private val flickrImageDTO2Mock = FlickrImageDTO(
        title = "Mocked Title 2",
        link = "Mocked Link 2",
        media = media,
        dateTaken = "Mocked Date Taken 2",
        description = "Mocked Description 2",
        published = "Mocked Published 2",
        author = "Mocked Author 2",
        authorId = "Mocked Author ID 2",
        tags = "Mocked Tags 2"
    )

    private val flickrResponseDTOMock = FlickrResponseDTO(
        title = "Mocked Title",
        link = "Mocked Link",
        description = "Mocked Description",
        modified = "Mocked Modified",
        generator = "Mocked Generator",
        items = listOf(
            flickrImageDTOMock,
            flickrImageDTO2Mock
        )
    )
    private val tags = listOf("colors", "sun")

    @Before
    fun setUp() {
        flickrApi = mock(FlickrApi::class.java)
        repository = SearchImageRepositoryImpl(flickrApi)
    }

    @Test
    fun `searchImageByTags should return success when API call is successful`(): Unit = runTest {
        `when`(flickrApi.getImagesByTag(tags = tags.joinToString(","))).thenReturn(flickrResponseDTOMock)
        val result = repository.searchImageByTags(tags)
        assertTrue(result is NetworkRequest.Success)
    }

    @Test
    fun `searchImageByTags should return error when API call fails`(): Unit = runTest {
        val errorMessage = "API Error Message"
        `when`(flickrApi.getImagesByTag(tags = tags.joinToString(","))).thenThrow(RuntimeException(errorMessage))

        val result = repository.searchImageByTags(tags)

        assertTrue(result is NetworkRequest.Error)
        assertEquals(errorMessage, (result as NetworkRequest.Error).error.message)
    }

}