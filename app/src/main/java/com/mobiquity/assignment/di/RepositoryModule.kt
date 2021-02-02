package com.mobiquity.assignment.di

import com.mobiquity.assignment.domain.Repository
import com.mobiquity.assignment.domain.RepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> { RepositoryImpl(get()) }
}