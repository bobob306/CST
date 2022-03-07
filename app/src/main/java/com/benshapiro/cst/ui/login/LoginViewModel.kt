package com.benshapiro.cst.ui.login

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

    fun clientRefEntered(clientRef: String) = viewModelScope.launch {
        val creditScore = repository.getCreditScore()
        _creditScoreLiveData.postValue(creditScore)
        // This is mocking checking the username and password are correct
        if (creditScore?.creditReportInfo?.clientRef != clientRef) {
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
        // this is timed to show for the same amount of time as the animation loads
        eventChannel.send(Event.Navigate("Loading credit information..."))
    }
    private fun triggerNoData() = viewModelScope.launch {
        // Username is given to you automatically so login fail is because of data connection
        eventChannel.send(Event.NoData("Unsuccessful login attempt, please check data connection."))
    }

}