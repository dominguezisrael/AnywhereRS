package com.example.anywherers_codeexercise.model

import com.example.anywherers_codeexercise.`interface`.ICharactersModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Collections

/**
 * Character model implementation
 */
class CharactersModelImpl : ICharactersModel {
    override fun getCharacters(
        charactersProgram: String,
        getCharactersResultInstance: ICharactersModel.IGetCharactersResult
    ) {
        val defaultErrorMessage = "Unknown error"
        val requestCaller = CharactersServiceBuilder.buildService(CharactersService::class.java)

        val requestCall = requestCaller.getCharacters(charactersProgram)
        requestCall.enqueue(object : Callback<CharacterListModel> {
            override fun onResponse(
                call: Call<CharacterListModel>, response: Response<CharacterListModel>
            ) {
                if (response.isSuccessful) {
                    getCharactersResultInstance.onSuccessful(
                        response.body() ?: CharacterListModel(Collections.emptyList())
                    )
                } else {
                    getCharactersResultInstance.onError(defaultErrorMessage)
                }
            }

            override fun onFailure(call: Call<CharacterListModel>, t: Throwable) {
                getCharactersResultInstance.onError(t.message ?: defaultErrorMessage)
            }
        })
    }
}