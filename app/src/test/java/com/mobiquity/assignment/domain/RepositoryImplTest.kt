package com.mobiquity.assignment.domain

import com.mobiquity.assignment.network.APIService
import com.mobiquity.assignment.network.data.Category
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import net.bytebuddy.implementation.bytecode.Throw
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.HttpException

class RepositoryImplTest {
    private lateinit var subject: RepositoryImpl
    private val apiService: APIService = Mockito.mock(APIService::class.java)

    @Before
    fun setUp() {
        subject = RepositoryImpl(apiService)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun successInGetCategories() = runBlockingTest{

        val result : List<Category>? = null
        val actualState: State<List<Category>> = subject.getCategories()

        Mockito.`when`(apiService.getCategories()).thenReturn(result)
        Mockito.verify(apiService).getCategories()
        Mockito.verifyNoMoreInteractions(apiService)

        assertEquals(State.Success(result), actualState)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun failureInGetAllAvailabilities() = runBlockingTest{

        val exception = Mockito.mock(HttpException::class.java)
        val message = "error"

        Mockito.`when`(apiService.getCategories()).thenThrow(exception)
        Mockito.`when`(exception.message).thenReturn(message)

        val actualState: State<List<Category>> = subject.getCategories()

        Mockito.verify(apiService).getCategories()
        Mockito.verifyNoMoreInteractions(apiService)

        assertEquals(State.Failure<List<Category>>(message), actualState)
    }

}