package com.example.anywherers_codeexercise.`interface`

/**
 * Interface to be implemented by any element to get filtered results.
 */
interface IFilterableRVAdapter {
    /**
     * Inform filter results to implementer.
     *
     * @param filteredResults List of elements that match the filter constraint.
     */
    fun updateDataSourceWithFilterResults(filteredResults: List<IRVFilterableObject>)
}