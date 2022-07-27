package com.example.bachelor_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bachelor_app.R
import com.example.bachelor_app.databinding.LoginFragmentBinding
import com.example.bachelor_app.ui.model.ILoginViewModel
import com.example.bachelor_app.ui.model.LoginViewModel

class LoginFragment : BaseFragment() {

    private val viewModel: ILoginViewModel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<LoginFragmentBinding>(inflater, R.layout.login_fragment, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}