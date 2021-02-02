package com.mobiquity.assignment.ui

import com.mobiquity.assignment.domain.GetCategoriesUseCase
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class MainViewModelTest {
    private lateinit var subject: MainViewModel
    private val getCategoriesUseCase: GetCategoriesUseCase = mock(GetCategoriesUseCase::class.java)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        subject = MainViewModel(getCategoriesUseCase)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getCategoriesTest() = runBlockingTest{

        subject.getCategories()

        verify(getCategoriesUseCase).execute(null)
        verifyNoMoreInteractions(getCategoriesUseCase)

    }
}