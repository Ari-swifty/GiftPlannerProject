package com.arigarasuthan.giftplannerapp

import androidx.fragment.app.Fragment
import com.arigarasuthan.giftplannerapp.utils.ProgressBarr

abstract class BaseFragment : Fragment() {
private val progressDialog by lazy { ProgressBarr(requireContext()) }

    protected fun showLoading(message:String)
    {
        progressDialog.start(message)
    }

    fun stopLoading()
    {
        progressDialog.stop()
    }
}