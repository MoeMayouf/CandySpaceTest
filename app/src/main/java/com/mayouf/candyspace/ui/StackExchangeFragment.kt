package com.mayouf.candyspace.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayouf.candyspace.R
import com.mayouf.candyspace.databinding.FragmentStackExchangeBinding
import com.mayouf.presentation.model.UiItems
import com.mayouf.presentation.utils.observeEvent
import com.mayouf.presentation.viewmodel.StackExchangeUsersViewModel
import com.mitteloupe.solid.recyclerview.SolidAdapter
import timber.log.Timber
import javax.inject.Inject

class StackExchangeFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_stack_exchange) {
    private val viewModel by viewModels<StackExchangeUsersViewModel> { viewModelFactory }

    private lateinit var binding: FragmentStackExchangeBinding
    private var usersAdapter: SolidAdapter<UsersViewHolder, UiItems>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStackExchangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Timber.d("Resume")
        viewModel.stackExchangeUsers("desc", "stackoverflow", "mohammed")
    }

    override fun onPause() {
        super.onPause()
        setUsersViewsVisibility(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setViewModelObservers()
    }

    private fun setupRecyclerView() {
        usersAdapter = SolidAdapter(
            LaunchViewProvider(layoutInflater),
            { view, _ -> UsersViewHolder(view) },
            LaunchViewBinder(context = requireContext())
        )
        binding.usersRecyclerView.apply {
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun bindUsers(launchesUiModel: List<UiItems>?) {
        launchesUiModel?.let { launches ->
            usersAdapter?.setItems(launches)
            setLaunchesErrorViewsVisibility(false)
        }
    }

    private fun setLaunchesErrorViewsVisibility(show: Boolean) {
        binding.errorDescription.isVisible = show
    }

    private fun setViewModelObservers() {
        viewModel.stackExchangeUsers.observe(viewLifecycleOwner, { launches ->
            bindUsers(launches.items)
        })
        viewModel.loadingStackExchangeUsers.observe(viewLifecycleOwner, { show ->

        })
        viewModel.errorStackExchangeUsers.observeEvent(this) {
            setUsersErrorViewsVisibility(true)
            setUsersViewsVisibility(false)
        }
    }

    private fun setUsersErrorViewsVisibility(show: Boolean) {
        binding.usersErrorDescription.isVisible = show
    }

    private fun setUsersViewsVisibility(show: Boolean) {
        binding.usersRecyclerView.isVisible = show
    }

}