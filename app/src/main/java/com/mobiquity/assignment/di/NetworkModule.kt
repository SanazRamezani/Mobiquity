package com.mobiquity.assignment.di

import com.mobiquity.assignment.network.APIService
import com.mobiquity.assignment.network.RequestInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import org.koin.dsl.module

val networkModule = module {
    single { RequestInterceptor() }
    single {
        createRetrofit(
            "http://mobcategories.s3-website-eu-west-1.amazonaws.com/",
            provideMoshi(),
            get()
        )
    }
    single { createService(get()) }
}

fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

fun createRetrofit(
    baseUrl: String,
    moshi: Moshi,
    requestInterceptor: RequestInterceptor
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(OkHttpClient.Builder().addInterceptor(requestInterceptor).build())
        .build()
}

fun createService(retrofit: Retrofit): APIService {
    return retrofit.create(APIService::class.java)
}