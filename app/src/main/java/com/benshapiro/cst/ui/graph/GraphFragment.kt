package com.benshapiro.cst.ui.graph

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.benshapiro.cst.databinding.FragmentGraphBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GraphFragment: Fragment(){

    private var _binding: FragmentGraphBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GraphViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGraphBinding.inflate(inflater, container, false)

        if (viewModel.creditScore == null) {
            findNavController().navigateUp()
        } else {

            binding.scoreTV.text = "${viewModel.creditScore!!.creditReportInfo!!.score}"

            binding.progressBar.max = 700
            ObjectAnimator.ofInt(
                binding.progressBar,
                "progress",
                viewModel.creditScore!!.creditReportInfo!!.score!!
            )
                .setDuration(2750)
                .start()
        }

        binding.detailsBtn.setOnClickListener{
            val action = GraphFragmentDirections.actionGraphFragmentToDetailFragment2()
            action.creditScore = viewModel.creditScore
            findNavController().navigate(action)
        }

        return binding.root
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}