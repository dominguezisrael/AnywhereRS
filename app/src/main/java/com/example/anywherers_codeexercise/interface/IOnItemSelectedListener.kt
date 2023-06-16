package com.example.anywherers_codeexercise.`interface`

/**
 * Listener to use for when an item is selected.
 */
interface IOnItemSelectedListener<T> {
    /**
     * Informs implementer when an item is selected.
     *
     * @param item Selected item.
     */
    fun onItemSelected(item: T)
}