package com.mobiquity.assignment.extensions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobiquity.assignment.domain.State
import com.mobiquity.assignment.domain.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.Mockito.*

class ViewModelExtensionsKtTest {

    private val viewModel: ViewModel = ViewModelHelper()
    private val useCase: UseCase<Any?, Any?> = mock(UseCase::class.java) as UseCase<Any?, Any?>
    private val mutableLiveData: MutableLiveData<State<Any?>> =
        mock(MutableLiveData::class.java) as MutableLiveData<State<Any?>>

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun startUseCaseTest() = runBlockingTest {

        val params = mock(Any::class.java)
        val result: State<Any?> = State.Success(null)

        `when`(useCase.execute(params)).thenReturn(result)

        viewModel.startUseCase(useCase, mutableLiveData, params)

        val argumentCaptor =
            ArgumentCaptor.forClass(State::class.java) as ArgumentCaptor<State<Any?>>

        verify(mutableLiveData, Mockito.times(2)).postValue(argumentCaptor.capture())
        verify(useCase).execute(params)
        verifyNoMoreInteractions(mutableLiveData, useCase)

        assertTrue(argumentCaptor.allValues[0] is State.Loading<Any?>)
        assertTrue(argumentCaptor.allValues[1] is State.Success<Any?>)
    }

    inner class ViewModelHelper : ViewModel()
}