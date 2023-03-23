package com.example.notes.screens

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.databinding.FragmentStartBinding
import com.example.notes.utils.*

class FragmentStart : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    private lateinit var fragStartViewModel: FragmentStartViewModel

    private var statusVisibility: Boolean = false

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

        setHasOptionsMenu(true)

        fragStartViewModel = ViewModelProvider(this).get(FragmentStartViewModel::class.java)

        Log.d(TAG, "Preferences: initUsers=${Preferences.getInitUser()}, typeDatabase=${Preferences.getTypeDatabase()}")

        if (Preferences.getInitUser()){
            Log.d(TAG, "Database initialisation start")
            fragStartViewModel.initDataBase(Preferences.getTypeDatabase()) {
                Log.d(TAG, "Database initialisation, callback - navigate")
                APP_ACTIVITY.navController.navigate(R.id.action_fragmentStart_to_fragmentMain)
            }
        } else {
            init()
        }
    }

    private fun init(){

        binding.btnRoom.setOnClickListener {
            fragStartViewModel.initDataBase(TYPE_ROOM){
                Preferences.setInitUser(true)
                Preferences.setTypeDatabase(TYPE_ROOM)
                TYPE_CURRENT_DATABASE = TYPE_ROOM
                APP_ACTIVITY.navController.navigate(R.id.action_fragmentStart_to_fragmentMain)
            }
        }

        binding.btnFirebase.setOnClickListener {
            if (!statusVisibility) changeVisibilityLogin(View.VISIBLE)
            else changeVisibilityLogin(View.INVISIBLE)

            binding.btnLogin.setOnClickListener {
                val inputEmail = binding.etEmail.text.toString()
                val inputPassword = binding.etPassword.text.toString()
                if (inputEmail.isNotEmpty() && inputPassword.isNotEmpty()){
                    EMAIL = inputEmail
                    PASSWORD = inputPassword
                    fragStartViewModel.initDataBase(TYPE_FIREBASE){
                        Preferences.setInitUser(true)
                        Preferences.setTypeDatabase(TYPE_FIREBASE)
                        TYPE_CURRENT_DATABASE = TYPE_FIREBASE
                        APP_ACTIVITY.navController.navigate(R.id.action_fragmentStart_to_fragmentMain)
                    }
                } else {
                    showToast("Enter email and password")
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_exit_program -> {
                APP_ACTIVITY.navController.navigateUp()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun changeVisibilityLogin(status: Int){

        statusVisibility = (status == View.VISIBLE)
        binding.etEmail.visibility = status
        binding.etPassword.visibility = status
        binding.btnLogin.visibility = status
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}