package io.hannah.imagefinder.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.hannah.imagefinder.network.FlickrApi
import io.hannah.imagefinder.network.NetworkService
import io.hannah.imagefinder.remote.SearchImageRepositoryImpl
import io.hannah.imagefinder.repository.SearchImageRepository

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun provideFlickrApi(): FlickrApi {
        return NetworkService.service
    }

    @Provides
    fun provideSearchImageRepository(api: FlickrApi): SearchImageRepository {
        return SearchImageRepositoryImpl(api)
    }

}