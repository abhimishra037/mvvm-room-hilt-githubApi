package c.com.githubrepolist.ui

import android.widget.MultiAutoCompleteTextView
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import c.com.githubrepolist.model.GithubRepo
import c.com.githubrepolist.repository.MainRepository
import c.com.githubrepolist.utility.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class MainViewModel
@ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<GithubRepo>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<GithubRepo>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetGitHubEvents -> {
                    mainRepository.getGitRepo().onEach { dataState ->
                        _dataState.value = dataState
                    }.launchIn(viewModelScope)
                }
                is MainStateEvent.None -> {
                }
            }
            delay(3000)

        }
    }
}

sealed class MainStateEvent {
    object GetGitHubEvents : MainStateEvent()
    object None : MainStateEvent()
}


