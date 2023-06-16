package com.example.anywherers_codeexercise.view

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.anywherers_codeexercise.R
import com.example.anywherers_codeexercise.model.CharacterModel
import com.example.anywherers_codeexercise.view.helper.CharacterDetailsDisplayHelper.Companion.displayCharacterDetails

/**
 * Activity to display character details.
 */
class CharacterDetailsActivity : AppCompatActivity() {
    companion object {
        const val CHARACTER_INFO_EXTRA: String = "CHARACTER_INFO_EXTRA"
    }

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.character_details_activity_layout)

        val character: CharacterModel? =
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU) {
                intent.extras?.getParcelable(CHARACTER_INFO_EXTRA, CharacterModel::class.java)
            } else {
                intent.extras?.getParcelable(CHARACTER_INFO_EXTRA)
            }

        displayCharacterDetails(findViewById(R.id.character_details_container), character)
    }
}