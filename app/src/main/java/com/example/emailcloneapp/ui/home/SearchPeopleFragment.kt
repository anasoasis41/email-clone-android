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
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.util.ArrayList

class SearchPeopleFragment : Fragment() {

    private lateinit var binding: FragmentSearchPeopleBinding
    private lateinit var navController: NavController
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterSearch: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                navController.popBackStack()
            }

            val listOfUsers = arguments?.getParcelableArrayList<EmailData>("userList")
            setupRecyclerView(listOfUsers)
            Timber.i(listOfUsers?.size.toString())
        }
        return binding.root
    }

    private fun setupRecyclerView(listOfUsers: ArrayList<EmailData>?) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        if (!listOfUsers.isNullOrEmpty()) {
            adapterSearch = SearchAdapter(requireContext(), listOfUsers)
            recyclerView.adapter = adapterSearch
        }

    }
}