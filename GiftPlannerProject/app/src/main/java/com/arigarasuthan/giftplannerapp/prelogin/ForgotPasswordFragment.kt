package com.arigarasuthan.giftplannerapp.prelogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arigarasuthan.giftplannerapp.BaseFragment
import com.arigarasuthan.giftplannerapp.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : BaseFragment() {
    private lateinit var passwordBinding: FragmentForgotPasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        passwordBinding = FragmentForgotPasswordBinding.inflate(inflater,container,false)
        return passwordBinding.root
    }
}