package com.mayouf.candyspace.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.mayouf.candyspace.R
import com.mayouf.candyspace.databinding.FragmentStackExchangeDetailBinding
import com.mayouf.candyspace.utils.loadImageFull
import com.mayouf.presentation.model.UiItems
import com.mayouf.presentation.viewmodel.StackExchangeUsersViewModel
import java.util.*
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
        (activity as AppCompatActivity?)!!.supportActionBar?.title = "User"
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userData = arguments?.getParcelable<UiItems>("userData")
        populateView(userData)
    }

    private fun populateView(userData: UiItems?) {
        binding.ivUserImage.loadImageFull(userData?.profileImage)
        binding.tvUserName.text = userData?.displayName ?: "Unavailable"
        binding.tvReputation.text = userData?.reputation.toString()
        binding.tvLocation.text = userData?.location ?: "Unavailable"
        binding.tvCreationDate.text = Date(userData?.creationDate?.toLong()!! * 1000L).toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            Navigation.findNavController(requireView()).navigate(R.id.users)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}