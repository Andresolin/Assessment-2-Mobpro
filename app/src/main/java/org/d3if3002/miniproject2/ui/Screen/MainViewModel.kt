package org.d3if3002.miniproject2.ui.Screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3002.miniproject2.database.PemesananDao
import org.d3if3002.miniproject2.model.Pemesanan


class MainViewModel(dao: PemesananDao) : ViewModel() {


    val data: StateFlow<List<Pemesanan>> = dao.getPemesanan().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

}