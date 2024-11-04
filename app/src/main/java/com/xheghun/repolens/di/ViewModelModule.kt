package com.xheghun.repolens.di

import com.xheghun.repolens.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewModelModule() = module {
    viewModel { SearchViewModel(get()) }
}