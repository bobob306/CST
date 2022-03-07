package com.benshapiro.cst.ui.graph

import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
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
            // if there is an error passing the credit score data then navigate back to login
            findNavController().navigateUp()
        } else {
            viewModel.scoreLiveData.observe(this.viewLifecycleOwner) { score ->
                binding.scoreTV.text = "$score"
                binding.progressBar.max = 700
                ObjectAnimator.ofInt(
                    binding.progressBar,
                    "progress",
                    score ?: 0
                )
                    // same duration as Snackbar.Length_long
                    .setDuration(2750)
                    .start()
            }
        }

        binding.detailsBtn.setOnClickListener{
            val action = GraphFragmentDirections.actionGraphFragmentToDetailFragment2()
            action.creditScore = viewModel.creditScore
            findNavController().navigate(action)
        }
        // Kind of here for fun, bit of a surprise if you load the app up on your phone
        binding.logoIV.setOnClickListener {
            val uri = Uri.parse("https://www.clearscore.com/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}