package com.example.anywherers_codeexercise.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface to implement service to get characters information.
 */
interface CharactersService {
    @GET("&format=json")
    fun getCharacters(@Query("q") query: String): Call<CharacterListModel>
}