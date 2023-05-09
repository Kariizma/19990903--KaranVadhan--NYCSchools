package com.example.nycschools.viewmodels

import android.os.Build.VERSION_CODES.N
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.nycschools.API.NYCSchoolsAPI
import com.example.nycschools.model.NYCSchools
import com.example.nycschools.model.NYCSchoolsWithSAT
import kotlinx.coroutines.launch


class NYCSchoolViewModel : ViewModel() {


    //the internal mutable data
    private val _nycSchool = MutableLiveData<List<NYCSchools>>()
    private val _nycSchoolWithSAT = MutableLiveData<List<NYCSchoolsWithSAT>>()
    private val _dbName = MutableLiveData<String>("N/A")
    private val _numOfSATTakers = MutableLiveData<String>("Number of SAT Takers: N/A")
    private val _readingScore = MutableLiveData<String>("Average Reading Score: N/A")
    private val _mathScore= MutableLiveData<String>("Average Math Score: N/A")
    private val _writingScore= MutableLiveData<String>("Average Writing Score: N/A")
    private var map: Map<String,NYCSchoolsWithSAT> = mapOf()

    //the external immutable data
    val nycSchools: LiveData<List<NYCSchools>> = _nycSchool
    val nycSchoolsWithSAT: LiveData<List<NYCSchoolsWithSAT>> = _nycSchoolWithSAT
    val dbName: LiveData<String> = _dbName
    val numOfSATTakers: LiveData<String> = _numOfSATTakers
    val readingScore: LiveData<String> = _readingScore
    val mathScore: LiveData<String> = _mathScore
    val writingScore: LiveData<String> = _writingScore

    init{
        getNYCSchools()
    }

    /**
     * Gets [NYCSchools] and [NYCSchoolsWithSAT] information from the [NYCSchoolsAPIService] using Retrofit and Moshi Json Converter
     */
    private fun getNYCSchools()
    {
        viewModelScope.launch {
            Log.v("getNYCSchools()", "Loading...")
            try {
                _nycSchool.value = NYCSchoolsAPI.retrofitService.getNYCSchools()
                _nycSchoolWithSAT.value = NYCSchoolsAPI.retrofitService.getNYCSchoolsWithSAT()
                Log.v("getNYCSchools()", "Success!, the data has been retrieved from the API")

            } catch (e: Exception) {
                Log.e("getNYCSchools()","Failure: ${e.message}")
                _nycSchool.value = listOf()
                _nycSchoolWithSAT.value = listOf()

            }
        }
    }

    private fun populateMap(){
        map = nycSchoolsWithSAT.value!!.map { it.id to it }.toMap()
    }

    fun setDBname(db: String)
    {
        _dbName.value = db
        findSchoolInfo()
    }

    fun findSchoolInfo() {
         populateMap()
         if(map.contains(dbName.value))
         {
             val satScoreForSchool: NYCSchoolsWithSAT? = map.get(dbName.value)
             _dbName.value = "Database Name: ${satScoreForSchool!!.id}"
             _numOfSATTakers.value = "Number of SAT Takers: ${satScoreForSchool.numOfSATTakers}"
             _readingScore.value = "Average Reading Score: ${satScoreForSchool.readingSATScore}"
             _mathScore.value = "Average Math Score: ${satScoreForSchool.mathSATScore}"
             _writingScore.value = "Average Writing Score: ${satScoreForSchool.writingSATScore}"
         }
        else
         {
             Log.e("findSchoolInfo","databaseName not found or function is not working")
         }
    }
    
}