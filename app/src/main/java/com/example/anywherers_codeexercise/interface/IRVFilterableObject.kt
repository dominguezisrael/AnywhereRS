package com.example.anywherers_codeexercise.`interface`

/**
 * Interface to be implemented by objects that can be filtered.
 */
interface IRVFilterableObject {
    /**
     * Defines the list of constraints to filter by.
     *
     * @return List of constraints to filter by.
     */
    fun getFilterConstraints(): List<String>
}