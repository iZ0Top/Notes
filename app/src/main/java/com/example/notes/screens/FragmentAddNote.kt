package com.example.notes.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.databinding.FragmentAddNewNoteBinding
import com.example.notes.model.AppNote
import com.example.notes.utils.APP_ACTIVITY
import com.example.notes.utils.showToast

class FragmentAddNote : Fragment() {

    private var _binding: FragmentAddNewNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var fragAddNewViewModel: FragmentAddNoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onStart() {
        super.onStart()
        init()
    }


    private fun init(){
        fragAddNewViewModel = ViewModelProvider(this).get(FragmentAddNoteViewModel::class.java)
        binding.buttonAddNote.setOnClickListener {
            val name = binding.etAddNewNoteTitle.text.toString()
            val text = binding.etAddNewNoteText.text.toString()

            if (name.isEmpty()){
                showToast("Title is empty !!!")
            } else {
                showToast("Title added !")
                fragAddNewViewModel.insertToDatabase(AppNote(name = name, text = text)){
                     showToast("Note added !")
                     APP_ACTIVITY.navController.navigate(R.id.action_fragmentAddNewNote_to_fragmentMain)
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}