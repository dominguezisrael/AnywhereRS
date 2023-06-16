package com.example.anywherers_codeexercise.presenter

import com.example.anywherers_codeexercise.BuildConfig
import com.example.anywherers_codeexercise.`interface`.ICharactersModel
import com.example.anywherers_codeexercise.`interface`.ICharactersPresenter
import com.example.anywherers_codeexercise.`interface`.ICharactersView
import com.example.anywherers_codeexercise.model.CharacterListModel
import com.example.anywherers_codeexercise.model.CharacterModel

/**
 * Characters presenter implementation.
 */
class CharactersPresenterImpl(
    private val charactersView: ICharactersView, private val charactersModel: ICharactersModel
) : ICharactersPresenter {
    /**
     * Handle the retrieval of characters info (using model) and the update of the UI (using view).
     */
    override fun getCharacters() {
        val program: String =
            if ("simpsonsFlavor" == BuildConfig.FLAVOR) {
                "simpsons characters"
            } else {
                "the wire characters"
            }

        val callback = object : ICharactersModel.IGetCharactersResult {
            override fun onSuccessful(characters: CharacterListModel) {
                charactersView.displayCharacterNames(characters)
            }

            override fun onError(errorMessage: String) {
                charactersView.displayError(errorMessage)
            }
        }

        charactersModel.getCharacters(program, callback)
    }
}