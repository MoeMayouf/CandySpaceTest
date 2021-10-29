package com.mayouf.candyspace.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayouf.candyspace.R
import com.mayouf.candyspace.databinding.FragmentStackExchangeBinding
import com.mayouf.presentation.model.UiItems
import com.mayouf.presentation.utils.observeEvent
import com.mayouf.presentation.viewmodel.StackExchangeUsersViewModel
import com.mitteloupe.solid.recyclerview.SolidAdapter
import kotlinx.android.synthetic.main.fragment_stack_exchange.*
import javax.inject.Inject

interface ClickListener {
    fun onItemClicked(userDetailItems: UiItems)
}

class StackExchangeFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_stack_exchange) {
    private val viewModel by viewModels<StackExchangeUsersViewModel> { viewModelFactory }

    private lateinit var binding: FragmentStackExchangeBinding
    private var usersAdapter: SolidAdapter<UsersViewHolder, UiItems>? = null

    private val clickListener = object : ClickListener {
        override fun onItemClicked(userDetailItems: UiItems) {
            val bundle = Bundle()
            bundle.putParcelable("userData", userDetailItems)
            Navigation.findNavController(requireView()).navigate(R.id.userDetail, bundle)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStackExchangeBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)!!.supportActionBar?.title = getString(R.string.app_name)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        setUsersViewsVisibility(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setViewModelObservers()
        binding.btnSearch.setOnClickListener {
            val searchString = binding.etSearchBox.text.toString()
            viewModel.stackExchangeUsers(searchString)
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            requireActivity().currentFocus!!.windowToken,
            0
        )
    }

    private fun setupRecyclerView() {
        usersAdapter = SolidAdapter(
            LaunchViewProvider(layoutInflater),
            { view, _ -> UsersViewHolder(view) },
            LaunchViewBinder(context = requireContext(), clickListener = clickListener)
        )
        binding.usersRecyclerView.apply {
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun bindUsers(launchesUiModel: List<UiItems>?) {
        launchesUiModel?.let { launches ->
            usersAdapter?.setItems(launches)
            setUsersErrorViewsVisibility(false)
            setUsersViewsVisibility(true)
        }
    }

    private fun setViewModelObservers() {
        viewModel.stackExchangeUsers.observe(viewLifecycleOwner, { launches ->
            bindUsers(launches.items)
            setUsersErrorViewsVisibility(false)
        })
        viewModel.loadingStackExchangeUsers.observe(viewLifecycleOwner, { show ->
            setUsersErrorViewsVisibility(false)
            progressBar.isVisible = show
        })
        viewModel.errorStackExchangeUsers.observeEvent(this) {
            setUsersErrorViewsVisibility(true)
        }
    }

    private fun setUsersErrorViewsVisibility(show: Boolean) {
        binding.usersErrorDescription.isVisible = show
    }

    private fun setUsersViewsVisibility(show: Boolean) {
        binding.usersRecyclerView.isVisible = show
    }

}