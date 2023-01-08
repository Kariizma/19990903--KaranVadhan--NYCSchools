package com.example.nycschools.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.R
import com.example.nycschools.databinding.FragmentSchoolListBinding
import com.example.nycschools.viewmodels.NYCSchoolViewModel

class SchoolListFragment: Fragment() {

    private val viewModel: NYCSchoolViewModel by activityViewModels()
    private var binding: FragmentSchoolListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSchoolListBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
        binding?.schoolListFragment = this
        binding?.lifecycleOwner = this
        binding?.schoolList?.adapter = SchoolListAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}