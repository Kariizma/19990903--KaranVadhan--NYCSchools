package com.example.nycschools.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.nycschools.databinding.FragmentSchoolListBinding
import com.example.nycschools.databinding.FragmentSchoolSatScoreBinding
import com.example.nycschools.model.NYCSchoolsWithSAT
import com.example.nycschools.viewmodels.NYCSchoolViewModel

class SchoolInfoFragment: Fragment() {

    private val viewModel: NYCSchoolViewModel by activityViewModels()
    private var binding: FragmentSchoolSatScoreBinding? = null
    private lateinit var databaseName: String


    /**
     * if i had more time i would probably figure out a better way to do this
     * 1. im passing the databaseName from the SchoolListAdapter to SchoolInfoFragment
     * 2. im retrieving the Listdata from the viewmodel
     * 3. turning the NYCSchoolsWithSAT List into a MAP, and using the dbname as a lookup
     * 4. calling the findSchoolInfo func, which does all the lookup when the view is created
     * 5. databinding the variables into the textviews
     */


    private lateinit var SATScores: List<NYCSchoolsWithSAT>
    private lateinit var map: Map<String,NYCSchoolsWithSAT>
    var dbName: String = ""
    var numOfSATTakers: String = "Number of SAT Takers: N/A"
    var readingScore: String = "Average Reading Score: N/A"
    var mathScore: String = "Average Math Score: N/A"
    var writingScore: String = "Average Writing Score: N/A"


    /**
     * Creates the View, and inflates it on to the MainActivity
     */

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSchoolSatScoreBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    /**
     * Called after the onCreateView to make sure the Fragments root is non-null
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.schoolInfoFragment = this
        SATScores = viewModel.nycSchoolsWithSAT.value!!
        map = SATScores?.map { it.id to it }?.toMap()!!
        databaseName = requireArguments().getString("databaseName").toString()
        findSchoolInfo(databaseName)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun findSchoolInfo(databasename: String)
    {
        if(map!!.contains(databasename))
        {
            val SATScoreForSchool: NYCSchoolsWithSAT? = map!!.get(databasename)
            dbName = "Database Name: " + databasename
            numOfSATTakers = "Number of SAT Takers: " + SATScoreForSchool!!.numOfSATTakers
            readingScore = "Average Reading Score: " + SATScoreForSchool.readingSATScore
            mathScore = "Average Math Score: " + SATScoreForSchool.mathSATScore
            writingScore = "Average Writing Score: " + SATScoreForSchool.writingSATScore
        }
        else
        {
            dbName = "Database Name: " + databasename
            Log.e("findSchoolInfo","databaseName not found or function is not working")
        }
    }
}