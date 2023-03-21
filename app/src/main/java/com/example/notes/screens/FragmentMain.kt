package com.example.notes.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.FragmentMainBinding
import com.example.notes.model.AppNote
import com.example.notes.utils.APP_ACTIVITY

class FragmentMain : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var fragMainViewModel: FragmentMainViewModel

    private lateinit var myAdapter: Adapter
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var myObserver: Observer<List<AppNote>>

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

        myAdapter = Adapter()
        myRecyclerView = binding.recyclerView
        myRecyclerView.adapter = myAdapter

        myObserver = Observer {
            val list = it.reversed()
            myAdapter.setList(list)
        }

        fragMainViewModel.allNotes.observe(this, myObserver)


        binding.buttonFabAddNote.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_fragmentMain_to_fragmentAddNewNote)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        fragMainViewModel.allNotes.removeObserver(myObserver)
        myRecyclerView.adapter = null
    }


    companion object {
        fun clic(note: AppNote){

            val bundle = Bundle()
            bundle.putSerializable("note", note)
            APP_ACTIVITY.navController.navigate(R.id.action_fragmentMain_to_fragmentNote2, bundle)
        }
    }

}
