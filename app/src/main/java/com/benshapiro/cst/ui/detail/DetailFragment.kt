package com.benshapiro.cst.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.benshapiro.cst.databinding.DetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModels()
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            detScoreTV.text =
                "Your credit score is: ${viewModel.creditScore?.creditReportInfo?.score ?: "Error"}"
            scoreBandTV.text =
                "Your credit score is: ${viewModel.creditScore?.creditReportInfo?.scoreBand ?: "Error"}"
            scoreBandDescTV.text =
                "Your credit score is: ${viewModel.creditScore?.creditReportInfo?.equifaxScoreBandDescription ?: "Error"}"
            val activeTD = viewModel.creditScore?.coachingSummary?.numberOfTodoItems ?: -1
            toDoTV.text = when {
                activeTD == 0 -> {
                    "You have completed all tasks on your account."
                }
                activeTD == -1 -> {
                    "Error"
                }
                activeTD == 1 -> {
                    "You have ${activeTD} task remaining."
                }
                else -> {
                    "You have ${activeTD} tasks remaining."
                }
            }
            returnToGraphBtn.setOnClickListener {
                findNavController().navigateUp()
            }

            logoutBtn.setOnClickListener {
                val action = DetailFragmentDirections.actionGlobalLoginFragment()
                findNavController().navigate(action)
            }
            logoIV.setOnClickListener {
                val uri = Uri.parse("https://www.clearscore.com/")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }

        }

    }


}