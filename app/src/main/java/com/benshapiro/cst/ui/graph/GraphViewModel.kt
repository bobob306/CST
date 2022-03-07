package com.benshapiro.cst.ui.graph

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.benshapiro.cst.domain.models.CreditScore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GraphViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val creditScore = savedStateHandle.get<CreditScore>("CreditScore")

}