package com.example.anywherers_codeexercise.`interface`

import com.example.anywherers_codeexercise.model.CharacterListModel

/**
 * Interface to define model interactions.
 */
interface ICharactersModel {
    /**
     * Interface to inform model results.
     */
    interface IGetCharactersResult {
        /**
         * Reports when characters information was retrieved successfully.
         * @param characters List of characters retrieved.
         */
        fun onSuccessful(characters: CharacterListModel)

        /**
         * Reports when an error occurs while retrieving characters information.
         * @param errorMessage Error message.
         */
        fun onError(errorMessage: String)
    }

    /**
     * Requests to retrieve characters information.
     * @param charactersProgram Name of program to get characters for.
     * @param getCharactersResultInstance Callback to report results.
     */
    fun getCharacters(charactersProgram: String, getCharactersResultInstance: IGetCharactersResult)
}