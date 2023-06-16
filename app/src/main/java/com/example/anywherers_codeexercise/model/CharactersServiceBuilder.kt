package com.example.anywherers_codeexercise.model

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Characters retrofit service builder.
 */
object CharactersServiceBuilder {
    private val httpClient = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.duckduckgo.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    /**
     * Builds retrofit service.
     *
     * @param service service definition.
     */
    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}