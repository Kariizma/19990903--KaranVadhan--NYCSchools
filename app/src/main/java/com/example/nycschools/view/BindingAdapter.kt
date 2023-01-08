package com.example.nycschools.view

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.model.NYCSchools


/**
 * Updates the data shown in the [RecyclerView]
 */

@BindingAdapter("schoolListData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<NYCSchools>?) {
    val adapter = recyclerView.adapter as SchoolListAdapter
    adapter.submitList(data)
}