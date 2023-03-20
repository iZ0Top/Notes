package com.example.notes.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.databinding.FragmentStartBinding
import com.example.notes.utils.APP_ACTIVITY
import com.example.notes.utils.TYPE_ROOM

class FragmentStart : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private lateinit var fragStartViewModel: FragmentStartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        init()



    }


    private fun init(){
        fragStartViewModel = ViewModelProvider(this).get(FragmentStartViewModel::class.java)

        binding.btnRoom.setOnClickListener {
            fragStartViewModel.initDataBase(TYPE_ROOM){
                APP_ACTIVITY.mNavController.navigate(R.id.action_fragmentStart_to_fragmentMain)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}