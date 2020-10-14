package com.example.estaterentalapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.estaterentalapp.Network
import com.example.estaterentalapp.R
import com.example.estaterentalapp.data.property
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
class homeFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val recyclerView = inflater.inflate(R.layout.fragment_property_list, null, false) as
                RecyclerView
        val swipeLayout = SwipeRefreshLayout(requireContext())
        swipeLayout.addView(recyclerView)
        swipeLayout.setOnRefreshListener {
            swipeLayout.isRefreshing = true
            reloadData(recyclerView)
            swipeLayout.isRefreshing = false
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        reloadData(recyclerView)
        return swipeLayout

    }

    private fun reloadData(recyclerView: RecyclerView) {

        val NEWS_URL = "https://morning-plains-00409.herokuapp.com/property/json"
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val json = Network.getTextFromNetwork(NEWS_URL)
                val property = Gson().fromJson<List<property>>(json,object :
                        TypeToken<List<property>>() {}.type)
                CoroutineScope(Dispatchers.Main).launch {
                    recyclerView.adapter = propertyRecyclerViewAdapter(property)
                }
            } catch (e: Exception) {
                Log.d("HomeFragment", "Error in loading data")
                val property = listOf(property("Cannot fetch", "Cannot fetch", "","Cannot fetch",
                    "Cannot fetch","Cannot fetch","Cannot fetch","Cannot fetch",
                    "Cannot fetch",false))
                CoroutineScope(Dispatchers.Main).launch {
                    recyclerView.adapter = propertyRecyclerViewAdapter(property)
                }
            }
        }
    }


    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            homeFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}