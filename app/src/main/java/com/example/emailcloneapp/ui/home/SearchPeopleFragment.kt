package com.example.emailcloneapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.emailcloneapp.R
import com.example.emailcloneapp.databinding.FragmentSearchPeopleBinding

class SearchPeopleFragment : Fragment() {

    private lateinit var binding: FragmentSearchPeopleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::binding.isInitialized) {
            binding = FragmentSearchPeopleBinding.inflate(inflater, container, false)
        }
        return binding.root
    }
}