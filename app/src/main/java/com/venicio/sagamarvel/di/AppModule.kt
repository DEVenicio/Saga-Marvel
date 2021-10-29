package com.venicio.sagamarvel.di

import com.venicio.sagamarvel.data.repository.SagaMarvelRepository
import com.venicio.sagamarvel.view.ui.DetailsFragmentArgs
import com.venicio.sagamarvel.viewmodel.SagaMarvelDetailsViewModel
import com.venicio.sagamarvel.viewmodel.SagaMarvelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

       viewModel { (rp: SagaMarvelRepository) -> SagaMarvelViewModel(rp) }
       viewModel { (arguments: DetailsFragmentArgs) -> SagaMarvelDetailsViewModel(arguments) }
}