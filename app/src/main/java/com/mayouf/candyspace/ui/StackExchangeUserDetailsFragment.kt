package com.mayouf.candyspace.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.mayouf.candyspace.R
import com.mayouf.candyspace.databinding.FragmentStackExchangeDetailBinding
import com.mayouf.presentation.model.UiItems
import com.mayouf.presentation.viewmodel.StackExchangeUsersViewModel
import javax.inject.Inject

class StackExchangeUserDetailsFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_stack_exchange_detail) {
    private val viewModel by viewModels<StackExchangeUsersViewModel> { viewModelFactory }

    private lateinit var binding: FragmentStackExchangeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStackExchangeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val blah = arguments?.getParcelable<UiItems>("userData")
        Log.i("this", "UserData is " + blah?.displayName)
    }
}