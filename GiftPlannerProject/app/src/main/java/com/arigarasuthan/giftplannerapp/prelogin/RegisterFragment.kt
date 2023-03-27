package com.arigarasuthan.giftplannerapp.prelogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arigarasuthan.giftplannerapp.BaseFragment
import com.arigarasuthan.giftplannerapp.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment() {
    private lateinit var registerBinding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registerBinding = FragmentRegisterBinding.inflate(inflater,container,false)
        return registerBinding.root
    }
}