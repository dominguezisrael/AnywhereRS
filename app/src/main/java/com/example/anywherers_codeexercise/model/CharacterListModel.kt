package com.example.anywherers_codeexercise.model

import com.google.gson.annotations.SerializedName

/**
 * Data model to parse characters information.
 */
data class CharacterListModel(
    @SerializedName("RelatedTopics") val characters: List<CharacterModel>
)