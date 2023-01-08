package com.example.nycschools.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.R
import com.example.nycschools.databinding.SchoolItemBinding
import com.example.nycschools.model.NYCSchools

/**
 * this class implements a [RecyclerView] [ListAdapter] which uses DataBinding to present [List] of [NYCSchools] data
 */

class SchoolListAdapter : ListAdapter<NYCSchools, SchoolListAdapter.SchoolListViewHolder>(DiffCallBack) {

    /**
     * Creates new [RecyclerView] item views
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolListAdapter.SchoolListViewHolder {
        return SchoolListViewHolder(SchoolItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Allows the [RecyclerView] to determine which items have changed when the [List] of [NYCSchools] has updated
     */

    companion object DiffCallBack: DiffUtil.ItemCallback<NYCSchools>() {
        override fun areItemsTheSame(oldItem: NYCSchools, newItem: NYCSchools): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NYCSchools, newItem: NYCSchools): Boolean {
            return oldItem.schoolName == newItem.schoolName
        }
    }

    /**
     * Replaces the contents of a view
     */
    override fun onBindViewHolder(holder: SchoolListAdapter.SchoolListViewHolder, position: Int) {
        val nycSchools = getItem(position)
        holder.bind(nycSchools)
    }

    /**
     * This takes the binding variable from the associated [SchoolItemBinding], which gives it access to the full [NYCSchools] information
     * This also sends the databaseName to the schoolInfoFragment through Jetpack Navigation actions + arguments so the look up for the SAT Scores can be done
     */

    class SchoolListViewHolder(private val binding: SchoolItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(nycSchools: NYCSchools) {
                binding.buttonItem.text = nycSchools.schoolName
                binding.buttonItem.setOnClickListener {
                    binding.buttonItem.findNavController().navigate(R.id.action_schoolListFragment_to_schoolInfoFragment,
                        Bundle().apply {
                            putString("databaseName",nycSchools.id)
                        }
                    )
                }
                binding.executePendingBindings()
            }
    }
}