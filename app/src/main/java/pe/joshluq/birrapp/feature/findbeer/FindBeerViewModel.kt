package pe.joshluq.birrapp.feature.findbeer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import pe.joshluq.birrapp.R
import pe.joshluq.birrapp.domain.ValidationResult
import pe.joshluq.birrapp.domain.model.Beer
import pe.joshluq.birrapp.domain.usecase.UseCase
import pe.joshluq.birrapp.util.eventhandler.SingleEvent
import pe.joshluq.birrapp.util.eventhandler.toSingleEvent
import pe.joshluq.birrapp.util.resource.ResourceProvider
import pe.joshluq.birrapp.util.viewmodel.LoaderViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class FindBeerViewModel @Inject constructor(
    @Named("findBeerByNameUseCase") private val findBeerByNameUseCase: UseCase<String, List<Beer>>,
    private val resourceProvider: ResourceProvider
) : LoaderViewModel() {

    private val _beerList = MutableLiveData(listOf<Beer>().toSingleEvent())
    val beerList: LiveData<SingleEvent<List<Beer>>> get() = _beerList

    private val _isSearchFieldEmpty = MutableLiveData(true.toSingleEvent())
    val isSearchFieldEmpty: LiveData<SingleEvent<Boolean>> get() = _isSearchFieldEmpty

    fun findBeerByName(name: String) = viewModelScope.loaderLaunch {
        findBeerByNameUseCase(name)
            .onSuccess(::handleBeerListSuccessful)
            .onFailure(::handleBeerListFailed)
    }

    private fun handleBeerListSuccessful(list: List<Beer>) {
        _beerList.value = list.toSingleEvent()
    }

    private fun handleBeerListFailed(error: Throwable) = when (error) {
        is ValidationResult.EmptyField -> {
            _isSearchFieldEmpty.value = true.toSingleEvent()
        }
        else -> {
            showErrorMessage(resourceProvider.getString(R.string.error_message))
        }
    }
}