package com.example.nycschools.API

import com.example.nycschools.model.NYCSchools
import com.example.nycschools.model.NYCSchoolsWithSAT
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://data.cityofnewyork.us/resource/"

/**
 * Building a Moshi object with a Kotlin adapter factory that retrofit will be using
 */

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Building the retrofit object with the moshi converter
 */

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A Public interface that is used to get the Json Data for our Models
 *  NYCSchools
 *  NYCSchoolsWithSAT
 */

interface NYCSchoolsApiService {
    @GET("s3k6-pzi2")
    suspend fun getNYCSchools(): List<NYCSchools>

    @GET("f9bf-2cp4")
    suspend fun getNYCSchoolsWithSAT(): List<NYCSchoolsWithSAT>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 * Basically a Singleton object (only gets called once)
 */

object NYCSchoolsAPI {
    val retrofitService: NYCSchoolsApiService by lazy { retrofit.create(NYCSchoolsApiService::class.java) }
}