package com.example.emailcloneapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emailcloneapp.R
import com.example.emailcloneapp.ui.home.data.EmailData

class HomeFragment : Fragment(), EmailsAdapter.EmailItemListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recyclerViewEmails: RecyclerView
    private lateinit var adapter: EmailsAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerViewEmails = rootView.findViewById(R.id.recyclerview_emails)
        recyclerViewEmails.layoutManager = LinearLayoutManager(requireContext())

        homeViewModel.emailData.observe(viewLifecycleOwner, Observer {
            adapter = EmailsAdapter(requireContext(), it, this)
            recyclerViewEmails.adapter = adapter
        })
        return rootView
    }

    override fun onEmailItemClick(email: EmailData) {
        Toast.makeText(requireContext(),email.subtitle, Toast.LENGTH_SHORT).show()
    }
}