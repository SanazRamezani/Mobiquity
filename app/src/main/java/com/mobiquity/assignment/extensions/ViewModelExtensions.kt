package com.mobiquity.assignment.extensions

import androidx.lifecycle.*
import com.mobiquity.assignment.domain.State
import com.mobiquity.assignment.domain.UseCase
import kotlinx.coroutines.launch

fun <Params, Type> ViewModel.startUseCase(
    useCase: UseCase<Params, Type>,
    result: MutableLiveData<State<Type>>,
    params: Params
) {
    result.postValue(State.Loading())
    viewModelScope.launch {
        result.postValue(useCase.execute(params))
    }
}
