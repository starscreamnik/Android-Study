package com.example.lab4

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.lab4.dummy.WeatherContent
import kotlinx.android.synthetic.main.fragment_item_list.view.*

class ItemFragment : Fragment() {
    private val TAG ="ItemFragment"

    private var listener: OnListFragmentInteractionListener? = null
    var refresh : OnRefreshListener? = null
    lateinit var swipeContainer: SwipeRefreshLayout
    private lateinit var recycle : RecyclerView
    private lateinit var myAdapter : MyItemRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        if(view is SwipeRefreshLayout){
            swipeContainer = view
            swipeContainer.setOnRefreshListener(refresh)
            Log.d(TAG, "It is")
            recycle = view.listRecycle
            recycle.layoutManager = LinearLayoutManager(context)
            myAdapter = MyItemRecyclerViewAdapter(ArrayList(), listener)
            recycle.adapter = myAdapter
            recycle.addItemDecoration(DividerItemDecoration(context, Configuration.ORIENTATION_PORTRAIT))
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            Log.d(TAG,"ListFragmentListener has been set")
            listener = context
        }
        if (context is OnRefreshListener){
            refresh = context
            Log.d(TAG, "RefreshListener been set")
        }
        else {
                throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
            }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        refresh = null
    }

    fun setList(newList: List<Pair<String, WeatherContent.DayWeatherItem>>) {
        myAdapter.clear()
        myAdapter.addAll(newList)
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(position: Int)
    }

    interface OnRefreshListener : SwipeRefreshLayout.OnRefreshListener {
        override fun onRefresh()
    }

}
