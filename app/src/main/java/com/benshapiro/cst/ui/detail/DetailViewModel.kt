package com.benshapiro.cst.ui.detail

import androidx.lifecycle.*
import com.benshapiro.cst.domain.models.CreditScore
import com.benshapiro.cst.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val creditScore = savedStateHandle.get<CreditScore>("CreditScore")

    private var _creditScoreLiveData = MutableLiveData<CreditScore?>()
    val creditScoreLiveData: LiveData<CreditScore?> = _creditScoreLiveData

    init {
        viewModelScope.launch {
            val creditScoreCheck = repository.getCreditScore()
            _creditScoreLiveData.postValue(creditScoreCheck)
        }
    }

}