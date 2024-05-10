package org.d3if3002.miniproject2.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3002.miniproject2.database.PemesananDao
import org.d3if3002.miniproject2.ui.Screen.MainViewModel

class ViewModelFactory(
    private val dao: PemesananDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}