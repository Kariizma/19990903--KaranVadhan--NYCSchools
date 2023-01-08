package com.example.nycschools.model

import com.squareup.moshi.Json

/**
 * Data class for Schools with their Average SAT scores
 * @property id is the database name of the school
 * @property schoolName is the school name
 * @property numOfSATTakers is the number of SAT takers at that school
 * @property readingSATScore is the average score of the reading assessment on the SAT for that school
 * @property mathSATScore is the average score of the math assessment on the SAT for that school
 * @property writingSATScore is the average score of the writing assessment on the SAT for that school
 *
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */

data class NYCSchoolsWithSAT (
    @Json(name = "dbn") val id: String,
    @Json(name = "school_name")val schoolName: String,
    @Json(name = "num_of_sat_test_takers")val numOfSATTakers: String,
    @Json(name = "sat_critical_reading_avg_score")val readingSATScore: String,
    @Json(name = "sat_math_avg_score")val mathSATScore: String,
    @Json(name = "sat_writing_avg_score")val writingSATScore: String)
