package com.example.xlampax.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xlampax.R
import com.example.xlampax.adapters.RecyclerViewAdapter
import com.example.xlampax.viewmodels.ActivityViewModel
import kotlinx.android.synthetic.main.fragment_news.*

private const val ARG_PARAM_FILTER_TAB_NAME = "ARG_PARAM_FILTER_TAB_NAME"

class NewsFragment : Fragment() {

    private var tabFilterType: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(ARG_PARAM_FILTER_TAB_NAME)) {
                tabFilterType = it.getString(ARG_PARAM_FILTER_TAB_NAME)!!
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val recyclerViewAdapter = RecyclerViewAdapter(requireContext())
        val model = ViewModelProvider(requireActivity()).get(ActivityViewModel::class.java)
        model.simpleLiveData.observe(viewLifecycleOwner, Observer {
            if (it.data.isNullOrEmpty()) {
                tv_empty_content.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                tv_empty_content.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
            recyclerViewAdapter.setNewsListItems(it.data?.filter {it.type!!.contentEquals(tabFilterType)
            }?.sortedBy { !it.top!!.contentEquals("1") })
        })

        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance(param: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_FILTER_TAB_NAME, param)
                }
            }
    }
}