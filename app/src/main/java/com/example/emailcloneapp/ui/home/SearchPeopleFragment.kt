package com.example.emailcloneapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.emailcloneapp.MainActivity
import com.example.emailcloneapp.R
import com.example.emailcloneapp.databinding.FragmentSearchPeopleBinding
import com.example.emailcloneapp.ui.home.data.EmailData
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class SearchPeopleFragment : Fragment() {

    private lateinit var binding: FragmentSearchPeopleBinding
    private lateinit var navController: NavController

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
            // button back
            binding.backSearchImageView.setOnClickListener {
                navController.popBackStack()
            }

            val listOfUsers = arguments?.getParcelableArrayList<EmailData>("userList")
            Timber.i(listOfUsers?.size.toString())
        }
        return binding.root
    }
}