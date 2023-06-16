package com.example.anywherers_codeexercise.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anywherers_codeexercise.R
import com.example.anywherers_codeexercise.`interface`.ICharactersView
import com.example.anywherers_codeexercise.`interface`.IOnItemSelectedListener
import com.example.anywherers_codeexercise.model.CharacterListModel
import com.example.anywherers_codeexercise.model.CharacterModel
import com.example.anywherers_codeexercise.model.CharactersModelImpl
import com.example.anywherers_codeexercise.presenter.CharactersPresenterImpl
import com.example.anywherers_codeexercise.view.adapter.CharactersRVAdapter
import com.example.anywherers_codeexercise.view.helper.CharacterDetailsDisplayHelper

/**
 * Activity to display characters.
 */
class CharactersActivity : AppCompatActivity(), ICharactersView {
    private lateinit var charactersPresenter: CharactersPresenterImpl
    private lateinit var searchView: androidx.appcompat.widget.SearchView
    private lateinit var charactersRVAdapter: CharactersRVAdapter
    private var selectedCharacter: CharacterModel? = null

    companion object {
        private const val SELECTED_CHARACTER_INFO: String = "SELECTED_CHARACTER_INFO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.characters_activity_layout)

        charactersPresenter = CharactersPresenterImpl(this, CharactersModelImpl())
        charactersPresenter.getCharacters()

        charactersRVAdapter = CharactersRVAdapter(listOf(), null)

        searchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                charactersRVAdapter.filter.filter(newText)

                return true
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (selectedCharacter != null && resources.getBoolean(R.bool.is_tablet)) {
            outState.putParcelable(SELECTED_CHARACTER_INFO, selectedCharacter)
        }

        super.onSaveInstanceState(outState)
    }

    @Suppress("DEPRECATION")
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState.containsKey(SELECTED_CHARACTER_INFO) &&
            resources.getBoolean(R.bool.is_tablet)
        ) {
            selectedCharacter = if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState.getParcelable(
                    SELECTED_CHARACTER_INFO, CharacterModel::class.java
                )!!
            } else {
                savedInstanceState.getParcelable(SELECTED_CHARACTER_INFO)!!
            }

            displayDetailsForCharacter(selectedCharacter)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.characters_activity_menu, menu)

        if (searchView.visibility == View.VISIBLE) {
            menu?.findItem(R.id.action_search)?.isVisible = false
            menu?.findItem(R.id.action_close_search)?.isVisible = true
        } else {
            menu?.findItem(R.id.action_search)?.isVisible = true
            menu?.findItem(R.id.action_close_search)?.isVisible = false
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                searchView.visibility = View.VISIBLE

                invalidateOptionsMenu()
            }

            R.id.action_close_search -> {
                searchView.visibility = View.GONE

                invalidateOptionsMenu()

                searchView.setQuery(null, true)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun displayCharacterNames(characterList: CharacterListModel) {
        val callback = object : IOnItemSelectedListener<CharacterModel> {
            override fun onItemSelected(item: CharacterModel) {
                selectedCharacter = item

                displayDetailsForCharacter(item)
            }
        }

        charactersRVAdapter = CharactersRVAdapter(characterList.characters, callback)

        val rv = findViewById<RecyclerView>(R.id.rv_characters_list)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = charactersRVAdapter
    }

    override fun displayError(errorMessage: String) {
        AlertDialog.Builder(this).setMessage(errorMessage).create().show()
    }

    /**
     * Display character details.
     *
     * @param character character to display details for.
     */
    private fun displayDetailsForCharacter(character: CharacterModel?) {
        if (resources.getBoolean(R.bool.is_tablet)) {
            CharacterDetailsDisplayHelper.displayCharacterDetails(
                findViewById(R.id.character_details_container), character
            )
        } else {
            val characterDetailsIntent = Intent(
                this@CharactersActivity,
                CharacterDetailsActivity::class.java
            )
            characterDetailsIntent.putExtra(
                CharacterDetailsActivity.CHARACTER_INFO_EXTRA,
                character
            )

            startActivity(characterDetailsIntent)
        }
    }
}