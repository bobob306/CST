package com.benshapiro.cst.ui.graph

import androidx.lifecycle.*
import com.benshapiro.cst.domain.models.CreditScore
import com.benshapiro.cst.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GraphViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // This fragment is the most important so I've given it two different layouts so it
    // should be great either way

    val creditScore = savedStateHandle.get<CreditScore>("CreditScore")

    private val _scoreLiveData = MutableLiveData<Int?>()
    val scoreLiveData: LiveData<Int?> = _scoreLiveData

    // the idea of using this in init was that the graph wouldn't reanimate on orientation change
    // has not been successful
    init {
        viewModelScope.launch {
            val score = repository.getCreditScore()?.creditReportInfo?.score ?: 0
            _scoreLiveData.postValue(score)
        }
    }



}