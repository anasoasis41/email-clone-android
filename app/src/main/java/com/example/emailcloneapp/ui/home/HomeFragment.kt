package com.example.emailcloneapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.emailcloneapp.R
import com.example.emailcloneapp.ui.home.adapters.EmailsAdapter
import com.example.emailcloneapp.ui.home.adapters.UsersAdapter
import com.example.emailcloneapp.ui.home.data.EmailData

class HomeFragment : Fragment(), HomeItemListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recyclerViewEmails: RecyclerView
    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var iconUserToolbar: ImageView
    private lateinit var adapterEmails: EmailsAdapter
    private lateinit var adapterUsers: UsersAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        iconUserToolbar = rootView.findViewById(R.id.userImageToolbar)
        recyclerViewEmails = rootView.findViewById(R.id.recyclerview_emails)
        recyclerViewUsers = rootView.findViewById(R.id.recyclerView_users)

        recyclerViewEmails.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewUsers.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.HORIZONTAL,
            false)

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
        })
        return rootView
    }

    override fun onEmailItemClick(email: EmailData) {
        Toast.makeText(requireContext(),email.subtitle, Toast.LENGTH_SHORT).show()
    }

    override fun onUserItemClick(user: EmailData) {
        Toast.makeText(requireContext(),user.username, Toast.LENGTH_SHORT).show()
    }
}