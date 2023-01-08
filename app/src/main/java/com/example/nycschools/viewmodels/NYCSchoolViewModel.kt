package com.example.nycschools.viewmodels

import android.os.Build.VERSION_CODES.N
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.API.NYCSchoolsAPI
import com.example.nycschools.model.NYCSchools
import com.example.nycschools.model.NYCSchoolsWithSAT
import kotlinx.coroutines.launch


class NYCSchoolViewModel : ViewModel() {


    //the internal mutable data
    private val _nycSchool = MutableLiveData<List<NYCSchools>>()
    private val _nycSchoolWithSAT = MutableLiveData<List<NYCSchoolsWithSAT>>()


    //the external immutable data
    val nycSchools: LiveData<List<NYCSchools>> = _nycSchool
    val nycSchoolsWithSAT: LiveData<List<NYCSchoolsWithSAT>> = _nycSchoolWithSAT

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
    
}