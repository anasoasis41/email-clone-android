package com.example.emailcloneapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.emailcloneapp.MainActivity
import com.example.emailcloneapp.R
import com.example.emailcloneapp.databinding.FragmentHomeBinding
import com.example.emailcloneapp.ui.home.adapters.EmailsAdapter
import com.example.emailcloneapp.ui.home.adapters.UsersAdapter
import com.example.emailcloneapp.ui.home.data.EmailData
import kotlinx.android.synthetic.main.activity_main.*

class HomeFragment : Fragment(), HomeItemListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recyclerViewEmails: RecyclerView
    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var iconUserToolbar: ImageView
    private lateinit var adapterEmails: EmailsAdapter
    private lateinit var adapterUsers: UsersAdapter
    private lateinit var searchView: ImageView
    private lateinit var navController: NavController

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        if (!::binding.isInitialized) {
            binding = FragmentHomeBinding.inflate(inflater, container, false)

            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

            iconUserToolbar = binding.userImageToolbar
            recyclerViewEmails = binding.contentHomeViews.recyclerviewEmails
            recyclerViewUsers = binding.contentHomeViews.recyclerViewUsers
            searchView = binding.iconSearchToolbar

            setupRecyclerView()
            observeHome()
        }

        return binding.root
    }

    private fun observeHome() {
        homeViewModel.emailData.observe(viewLifecycleOwner, Observer { dataList ->
            val listEmailData: List<EmailData> = dataList

            Glide.with(requireContext())
                .load(listEmailData[0].image)
                .circleCrop()
                .into(iconUserToolbar)

            adapterEmails = EmailsAdapter(requireContext(), listEmailData, this)
            adapterUsers = UsersAdapter(requireContext(), listEmailData, this)
            recyclerViewEmails.adapter = adapterEmails
            recyclerViewUsers.adapter = adapterUsers

            searchView.setOnClickListener {
                val bundle = bundleOf("userList" to listEmailData)
                navController.navigate(R.id.action_navigation_home_to_searchPeopleFragment, bundle)
            }

        })
    }

    private fun setupRecyclerView() {
        recyclerViewEmails.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewUsers.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.HORIZONTAL,
            false
        )
        recyclerViewEmails.isNestedScrollingEnabled = false
    }

    override fun onEmailItemClick(email: EmailData) {
    }

    override fun onUserItemClick(user: EmailData) {
        Toast.makeText(requireContext(),user.username, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).nav_view.visibility = View.VISIBLE
    }
}