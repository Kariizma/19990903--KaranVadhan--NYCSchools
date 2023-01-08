package com.example.nycschools.model

import com.squareup.moshi.Json

/**
 * Data class for NYC Schools
 * @property id is the database name of the school
 * @property schoolName is the school name
 *
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */

data class NYCSchools (
    @Json(name = "dbn") val id: String,
    @Json(name = "school_name") val schoolName: String)