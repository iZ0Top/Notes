package com.example.notes.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
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
        init()

    }


    private fun init(){
        fragStartViewModel = ViewModelProvider(this).get(FragmentStartViewModel::class.java)

        binding.btnRoom.setOnClickListener {
            fragStartViewModel.initDataBase(TYPE_ROOM){
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
                        showToast("Init OK")
                        APP_ACTIVITY.navController.navigate(R.id.action_fragmentStart_to_fragmentMain)
                    }

                } else {
                    showToast("Enter email and password")
                }
            }
        }
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