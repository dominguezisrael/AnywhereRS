package com.example.anywherers_codeexercise.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.anywherers_codeexercise.R
import com.example.anywherers_codeexercise.`interface`.IFilterableRVAdapter
import com.example.anywherers_codeexercise.`interface`.IOnItemSelectedListener
import com.example.anywherers_codeexercise.`interface`.IRVFilterableObject
import com.example.anywherers_codeexercise.model.CharacterModel

/**
 * Adapter that displays characters information on a RV.
 */
class CharactersRVAdapter(
    private var characterList: List<IRVFilterableObject>,
    private val onItemClickListener: IOnItemSelectedListener<CharacterModel>?
) : RecyclerView.Adapter<CharactersRVAdapter.CharacterViewHolder>(), Filterable,
    IFilterableRVAdapter {
    private var adapterFilter: RVAdapterFilter? = null
    private val originalCharacterList = characterList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.character_view, parent,
            false
        )

        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characterList[position] as CharacterModel

        holder.characterNameView.text = character.getName()
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemSelected(character)
        }
    }

    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val characterNameView: TextView = view.findViewById(R.id.character_name_view)
    }

    override fun getFilter(): Filter {
        return adapterFilter ?: RVAdapterFilter(
            this@CharactersRVAdapter,
            originalCharacterList
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateDataSourceWithFilterResults(filteredResults: List<IRVFilterableObject>) {
        characterList = filteredResults

        notifyDataSetChanged()
    }
}