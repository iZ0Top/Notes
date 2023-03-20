package com.example.notes.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.notes.R
import com.example.notes.databinding.FragmentMainBinding
import com.example.notes.utils.APP_ACTIVITY

class FragmentMain : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var fragMainViewModel: FragmentMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        init()


    }

    fun init(){
        fragMainViewModel = ViewModelProvider(this).get(FragmentMainViewModel::class.java)
        binding.buttonFabAddNote.setOnClickListener {
            APP_ACTIVITY.mNavController.navigate(R.id.action_fragmentMain_to_fragmentAddNewNote)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}