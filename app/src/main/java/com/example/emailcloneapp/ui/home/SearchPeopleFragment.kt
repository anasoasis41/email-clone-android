package com.example.emailcloneapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emailcloneapp.MainActivity
import com.example.emailcloneapp.R
import com.example.emailcloneapp.databinding.FragmentSearchPeopleBinding
import com.example.emailcloneapp.ui.home.adapters.SearchAdapter
import com.example.emailcloneapp.ui.home.data.EmailData
import com.example.emailcloneapp.utils.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class SearchPeopleFragment : Fragment() {

    private lateinit var binding: FragmentSearchPeopleBinding
    private lateinit var navController: NavController
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterSearch: SearchAdapter
    var listOfUsers: MutableList<EmailData> = mutableListOf()
    val displayList = ArrayList<EmailData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideKeyboard()
        (activity as MainActivity).nav_view.visibility = View.INVISIBLE
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::binding.isInitialized) {
            binding = FragmentSearchPeopleBinding.inflate(inflater, container, false)
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            searchView = binding.searchViewPeople
            recyclerView = binding.recyclerviewSearchPeople

            // button back
            binding.backSearchImageView.setOnClickListener {
                hideKeyboard()
                navController.popBackStack()
            }

            listOfUsers = arguments?.getParcelableArrayList("userList")!!
            setupRecyclerView(listOfUsers)

            searchView.onActionViewExpanded()
            searchView.isIconified = false
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()) {
                        displayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        listOfUsers.forEach {
                            if (it.username.toLowerCase(Locale.getDefault()).contains(search)) {
                                displayList.add(it)
                            }
                        }
                        recyclerView.adapter!!.notifyDataSetChanged()
                    } else {
                        displayList.clear()
                        displayList.addAll(listOfUsers)
                        recyclerView.adapter = adapterSearch
                    }
                    return false
                }
            })

        }
        return binding.root
    }

    private fun setupRecyclerView(listOfUsers: MutableList<EmailData>?) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        if (!listOfUsers.isNullOrEmpty()) {
            displayList.addAll(listOfUsers)
            adapterSearch = SearchAdapter(requireContext(), displayList)
            recyclerView.adapter = adapterSearch
        }

    }
}