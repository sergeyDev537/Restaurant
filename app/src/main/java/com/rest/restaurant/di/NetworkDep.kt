package com.rest.restaurant.di

import android.content.Context
import com.rest.restaurant.data.impl.RestaurantRepoImpl
import com.rest.restaurant.data.managers.StorageLocale
import com.rest.restaurant.data.mapper.RestaurantsMapper
import com.rest.restaurant.data.network.RestaurantApi
import com.rest.restaurant.domain.repos.RestaurantRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkDep {

    @Provides
    fun provideBaseUrl(): String = "https://api.mycyprus.app/api/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): RestaurantApi = retrofit.create(RestaurantApi::class.java)

    @Provides
    @Singleton
    fun provideRestaurantRepo(api: RestaurantApi, mapper: RestaurantsMapper, storage: StorageLocale): RestaurantRepo = RestaurantRepoImpl(
        restaurantApi = api,
        mapper = mapper,
        storage = storage,
    )

    @Provides
    @Singleton
    fun provideStorageLocale(@ApplicationContext context: Context): StorageLocale = StorageLocale(
        context.getSharedPreferences("RestaurantLocaleStorage", Context.MODE_PRIVATE)
    )

}