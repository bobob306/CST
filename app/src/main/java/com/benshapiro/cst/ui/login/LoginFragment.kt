package com.benshapiro.cst.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.benshapiro.cst.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collect { event ->
                when (event) {
                    is LoginViewModel.Event.Navigate -> {
                        Snackbar.make(
                            binding.root, event.message,
                            Snackbar.LENGTH_LONG
                        ).show()
                        navigateToGraph()
                    }
                    is LoginViewModel.Event.NoData -> {
                        Snackbar.make(
                            binding.root, event.message,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            loginBtn.setOnClickListener {
                viewModel.clientRefEntered()
            }
        }


    }

    private fun navigateToGraph() {
        viewModel.creditScoreLiveData.observe(this.viewLifecycleOwner) { creditScore ->
            val action = LoginFragmentDirections.actionLoginFragmentToGraphFragment()
            action.creditScore = creditScore
            this.findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}