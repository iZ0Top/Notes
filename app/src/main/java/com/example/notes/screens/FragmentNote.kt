package com.example.notes.screens

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.notes.R
import com.example.notes.databinding.FragmentNoteBinding
import com.example.notes.model.AppNote
import com.example.notes.utils.APP_ACTIVITY
import com.example.notes.utils.showToast

class FragmentNote : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var fragNoteViewModel: FragmentNoteViewModel
    private lateinit var currentNote: AppNote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)

        currentNote = arguments?.getSerializable("note") as AppNote

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        fragNoteViewModel = ViewModelProvider(this).get(FragmentNoteViewModel::class.java)

        setHasOptionsMenu(true)
        binding.noteName.text = currentNote.name
        binding.noteName.text = currentNote.text

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu_toolbar, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_delete_note -> {
                fragNoteViewModel.deleteNote(currentNote){
                    APP_ACTIVITY.navController.navigate(R.id.action_fragmentNote2_to_fragmentMain)
                    showToast("Notes deleted successful!")
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}