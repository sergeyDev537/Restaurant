package com.rest.restaurant.data.network

import com.rest.restaurant.data.network.NetworkConstants.AUTH_TOKEN
import com.rest.restaurant.data.network.pdo.ResponseLikePdo
import com.rest.restaurant.data.network.pdo.RestaurantPdo
import com.rest.restaurant.data.network.pdo.RestaurantsPdo
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface RestaurantApi {

    @Headers("Authorization: $AUTH_TOKEN")
    @GET("internship/organizations/category/1/organizations/")
    suspend fun getRestaurants(): Response<RestaurantsPdo>

    @Headers("Authorization: $AUTH_TOKEN")
    @GET("internship/organization/{restaurantId}")
    suspend fun getRestaurant(@Path("restaurantId") restaurantId: Int): Response<RestaurantPdo>

    @Headers("Authorization: $AUTH_TOKEN")
    @POST("internship/organization/{restaurantId}/favorite/")
    suspend fun setFavourite(@Path("restaurantId") restaurantId: Int): Response<ResponseLikePdo>

    @Headers("Authorization: $AUTH_TOKEN")
    @DELETE("internship/organization/{restaurantId}/favorite/")
    suspend fun removeFavourite(@Path("restaurantId") restaurantId: Int): Response<Any>

}