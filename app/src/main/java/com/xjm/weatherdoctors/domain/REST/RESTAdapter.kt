package com.xjm.weatherdoctors.domain.REST

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *   @author xavijimenez
 *   @since 05/03/2020
 *   @version 1.0.0
 */
/**
 * Object that provides method to create new services
 */
object RESTAdapter {

// =====================================================================================================================
// RESTAdapter methods
// =====================================================================================================================

    /**
     * Method to create new service from given service class.
     * @param clazz service class.
     */
    fun <T> createService(clazz: Class<T>): T {

        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.darksky.net")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(clazz)
    }
}