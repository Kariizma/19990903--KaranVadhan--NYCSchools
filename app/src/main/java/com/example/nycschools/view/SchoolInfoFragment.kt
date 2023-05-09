package com.example.nycschools.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.nycschools.databinding.FragmentSchoolSatScoreBinding
import com.example.nycschools.model.NYCSchoolsWithSAT
import com.example.nycschools.viewmodels.NYCSchoolViewModel

class SchoolInfoFragment: Fragment() {

    private val viewModel: NYCSchoolViewModel by activityViewModels()
    private var binding: FragmentSchoolSatScoreBinding? = null

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
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel
        var databaseName = requireArguments().getString("databaseName").toString()
        viewModel.setDBname(databaseName)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}