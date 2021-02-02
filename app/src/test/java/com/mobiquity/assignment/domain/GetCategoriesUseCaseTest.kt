package com.mobiquity.assignment.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class GetCategoriesUseCaseTest {
    private lateinit var subject: GetCategoriesUseCase
    private val repository: Repository = mock(Repository::class.java)

    @Before
    fun setUp() {
        subject = GetCategoriesUseCase(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun executeUseCase() = runBlockingTest{
        subject.execute(null)
        Mockito.verify(repository).getCategories()
        Mockito.verifyNoMoreInteractions(repository)
    }
}