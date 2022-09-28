package pe.joshluq.birrapp.util.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.joshluq.birrapp.util.eventhandler.SingleEvent
import pe.joshluq.birrapp.util.eventhandler.toSingleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pe.joshluq.birrapp.util.EMPTY_STRING

abstract class LoaderViewModel : ViewModel() {

    private val _isLoading = MutableLiveData(false.toSingleEvent())
    val isLoading: LiveData<SingleEvent<Boolean>> get() = _isLoading

    private val _errorMessage = MutableLiveData(EMPTY_STRING.toSingleEvent())
    val errorMessage: LiveData<SingleEvent<String>> get() = _errorMessage

    protected fun showErrorMessage(message: String) {
        _errorMessage.value = message.toSingleEvent()
    }

    private fun showLoader() {
        _isLoading.value = true.toSingleEvent()
    }

    private fun hideLoader() {
        _isLoading.value = false.toSingleEvent()
    }

    protected fun CoroutineScope.loaderLaunch(
        isContinuous: Boolean = false,
        block: suspend CoroutineScope.() -> Unit,
    ) = launch {
        showLoader()
        block()
        if (isContinuous.not()) hideLoader()
    }

}