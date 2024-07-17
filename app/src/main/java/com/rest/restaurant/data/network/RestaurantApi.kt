package com.rest.restaurant.data.network

import com.rest.restaurant.data.network.pdo.ResponseLikePdo
import com.rest.restaurant.data.network.pdo.RestaurantPdo
import com.rest.restaurant.data.network.pdo.RestaurantsPdo
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RestaurantApi {

    @GET("internship/organizations/category/1/organizations/")
    suspend fun getRestaurants(): Response<RestaurantsPdo>

    @GET("internship/organization/{restaurantId}")
    suspend fun getRestaurant(@Path("restaurantId") restaurantId: Int): Response<RestaurantPdo>

    @POST("internship/organization/{restaurantId}/favorite/")
    suspend fun setFavourite(@Path("restaurantId") restaurantId: Int): Response<ResponseLikePdo>

    @DELETE("internship/organization/{restaurantId}/favorite/")
    suspend fun removeFavourite(@Path("restaurantId") restaurantId: Int): Response<Any>

}