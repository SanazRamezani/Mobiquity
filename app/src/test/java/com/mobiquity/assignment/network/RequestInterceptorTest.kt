package com.mobiquity.assignment.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class RequestInterceptorTest {

    private lateinit var subject: RequestInterceptor

    @Before
    fun setUp() {
        subject = RequestInterceptor()
    }

    @Test
    fun intercept() {
        val request = mock(Request::class.java)
        val chain = mock(Interceptor.Chain::class.java)
        val builder = mock(Request.Builder::class.java)
        val url = mock(HttpUrl::class.java)
        val authorizedRequest = mock(Request::class.java)
        val response = mock(Response::class.java)

        `when`(chain.request()).thenReturn(request)
        `when`(request.newBuilder()).thenReturn(builder)
        `when`(request.url).thenReturn(url)
        `when`(url.toString()).thenReturn("url")
        `when`(builder.url("url")).thenReturn(builder)
        `when`(builder.build()).thenReturn(authorizedRequest)
        `when`(chain.proceed(authorizedRequest)).thenReturn(response)

        val result = subject.intercept(chain)
        assertEquals(response, result)

        verify(chain).request()
        verify(request).newBuilder()
        verify(request).url
        verify(builder).url("url")
        verify(builder).build()
        verify(chain).proceed(authorizedRequest)

        verifyNoMoreInteractions(chain, url, request, builder)

    }
}