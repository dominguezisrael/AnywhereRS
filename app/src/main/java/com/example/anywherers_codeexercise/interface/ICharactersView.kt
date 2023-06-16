package com.example.anywherers_codeexercise.`interface`

import com.example.anywherers_codeexercise.model.CharacterListModel

/**
 * Interface to define view interactions.
 */
interface ICharactersView {
    /**
     * Requests to display character names.
     * @param characterList List with characters to display names for.
     */
    fun displayCharacterNames(characterList: CharacterListModel)

    /**
     * Requests to display an error message.
     * @param errorMessage Error message to display.
     */
    fun displayError(errorMessage: String)
}