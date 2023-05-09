package com.example.nycschools.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.nycschools.R
import com.example.nycschools.databinding.FragmentStartBinding
import com.example.nycschools.viewmodels.NYCSchoolViewModel

/**
 * this is the First screen of the NYCSchools Application,
 */

class StartFragment: Fragment() {

    private var binding: FragmentStartBinding? = null
    private val viewModel: NYCSchoolViewModel by activityViewModels()

    /**
     * Creates the View, and inflates it on to the MainActivity
     */

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    /**
     * Called after the onCreateView to make sure the Fragments root is non-null
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this
    }

    /**
     * navigating from startFragment to the SchoolListFragment using Jetpack Navigation
     */
    fun onGenerateSchoolListClick()
    {
        findNavController().navigate(R.id.action_startFragment_to_schoolListFragment)
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