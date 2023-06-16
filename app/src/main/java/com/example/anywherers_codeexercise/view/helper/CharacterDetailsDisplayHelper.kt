package com.example.anywherers_codeexercise.view.helper

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.anywherers_codeexercise.R
import com.example.anywherers_codeexercise.model.CharacterModel

/**
 * Character details display helper.
 */
class CharacterDetailsDisplayHelper {
    companion object {
        /**
         * Displays character details.
         *
         * @param view view to get required view references from.
         * @param character character to display details for.
         */
        fun displayCharacterDetails(view: View, character: CharacterModel?) {
            val characterTitleView = view.findViewById<TextView>(R.id.character_title_view)
            val characterImageView =
                view.findViewById<AppCompatImageView>(R.id.character_image_view)
            val characterDescriptionView =
                view.findViewById<TextView>(R.id.character_description_view)

            character?.let {
                characterTitleView.text = character.getName()
                characterDescriptionView.text = character.title

                val glideReqOptions = RequestOptions()
                    .placeholder(
                        ContextCompat.getDrawable(
                            view.context,
                            R.drawable.no_available_image_for_character_image
                        )
                    )
                    .diskCacheStrategy(DiskCacheStrategy.ALL)

                Glide.with(view.context)
                    .asBitmap()
                    .load(character.iconUrl.getUrl())
                    .apply(glideReqOptions)
                    .into(characterImageView)
            }
        }
    }
}