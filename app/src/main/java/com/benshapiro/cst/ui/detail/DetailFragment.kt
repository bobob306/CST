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

        if (viewModel.creditScore == null) {
            // if there is an error passing the credit score data then navigate up one fragment
            findNavController().navigateUp()
        } else {
            viewModel.creditScoreLiveData.observe(this.viewLifecycleOwner) { creditScore ->
                binding.apply {
                    if (creditScore != null) {
                        detScoreTV.text =
                            "Your credit score is: ${creditScore.creditReportInfo?.score ?: "Error"}"
                        scoreBandTV.text =
                            "Your credit score is: ${creditScore.creditReportInfo?.scoreBand ?: "Error"}"
                        scoreBandDescTV.text =
                            "Your credit score is: ${creditScore.creditReportInfo?.equifaxScoreBandDescription ?: "Error"}"
                        val activeTD = creditScore.coachingSummary?.numberOfTodoItems ?: -1
                        toDoTV.text = when (activeTD) {
                            // Here I wanted the grammar to be correct and to catch the error
                            0 -> {
                                "You have completed all tasks on your account."
                            }
                            -1 -> {
                                "Error"
                            }
                            1 -> {
                                "You have $activeTD task remaining."
                            }
                            else -> {
                                "You have $activeTD tasks remaining."
                            }
                        }
                    }
                }
            }
            binding.returnToGraphBtn.setOnClickListener {
                findNavController().navigateUp()
            }
            // Also not strictly necessary but actually a useful article!
            binding.improveBtn.setOnClickListener {
                val uri =
                    Uri.parse("https://www.clearscore.com/learn/credit-score-and-report/how-to-improve-your-credit-score-in-10-easy-steps/")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            binding.logoIV.setOnClickListener {
                val uri = Uri.parse("https://www.clearscore.com/")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}