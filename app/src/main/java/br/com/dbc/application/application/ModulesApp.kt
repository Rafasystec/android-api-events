package br.com.dbc.application.application

import br.com.dbc.application.repository.ListEventRepository
import br.com.dbc.application.service.ListEventService
import br.com.dbc.application.ui.dialog.CheckingViewModel
import br.com.dbc.application.ui.main.EventDetailViewModel
import br.com.dbc.application.ui.main.ListEventViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

    /*
    --------------------------------------------------------------------
    Precisamos desse arquivo para o Koin conseguir injetar as depedências
    de forma correta.
    --------------------------------------------------------------------
     */

    val repositories = module {
        factory { ListEventRepository() }
    }

    val services = module {
        factory { ListEventService(get()) }
    }

    val viewModels = module {
        viewModel { ListEventViewModel  (get())}
        viewModel { EventDetailViewModel(get())}
        viewModel { CheckingViewModel   (get())}
    }
