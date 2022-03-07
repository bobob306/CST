package com.benshapiro.cst.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benshapiro.cst.domain.models.CreditScore
import com.benshapiro.cst.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val repository: Repository
    ) : ViewModel(){

    private val _creditScoreLiveData = MutableLiveData<CreditScore?>()
    val creditScoreLiveData: LiveData<CreditScore?> = _creditScoreLiveData

    fun clientRefEntered() = viewModelScope.launch {
        val creditScore = repository.getCreditScore()
        _creditScoreLiveData.postValue(creditScore)
        if (creditScore == null) {
            triggerNoData()
        } else {
            triggerNavigate()
        }
    }

    sealed class Event {
        data class Navigate(val message: String): Event()
        data class NoData(val message: String): Event()
    }

    private val eventChannel = Channel<Event>()
    val eventFlow = eventChannel.receiveAsFlow()

    private fun triggerNavigate() = viewModelScope.launch {
        eventChannel.send(Event.Navigate("Loading credit information..."))
    }
    private fun triggerNoData() = viewModelScope.launch {
        eventChannel.send(Event.NoData("Unsuccessful login attempt, please check data connection."))
    }

}