package com.example.anywherers_codeexercise.view.adapter

import android.widget.Filter
import com.example.anywherers_codeexercise.`interface`.IFilterableRVAdapter
import com.example.anywherers_codeexercise.`interface`.IRVFilterableObject
import java.util.Locale

/**
 * Provides filter functionality.
 */
class RVAdapterFilter(
    var adapter: IFilterableRVAdapter,
    private val objectsToFilter: List<IRVFilterableObject>
) : Filter() {
    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val results = FilterResults()

        if (!constraint.isNullOrEmpty()) {
            val filteredResults: ArrayList<IRVFilterableObject> = ArrayList()

            for (objectToFilter: IRVFilterableObject in objectsToFilter) {
                for (objectConstraint: String in objectToFilter.getFilterConstraints()) {
                    if (objectConstraint.uppercase(Locale.getDefault())
                            .contains((constraint as String).uppercase(Locale.getDefault()))
                    ) {
                        filteredResults.add(objectToFilter)

                        break
                    }
                }
            }

            results.count = filteredResults.size
            results.values = filteredResults
        } else {
            results.count = objectsToFilter.size
            results.values = objectsToFilter
        }

        return results
    }

    @Suppress("UNCHECKED_CAST")
    override fun publishResults(constraint: CharSequence?, filteredResults: FilterResults?) {
        adapter.updateDataSourceWithFilterResults(filteredResults?.values as List<IRVFilterableObject>)
    }
}