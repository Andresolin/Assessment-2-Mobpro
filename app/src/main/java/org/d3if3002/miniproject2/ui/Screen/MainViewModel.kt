package org.d3if3002.miniproject2.ui.Screen

import androidx.lifecycle.ViewModel
import org.d3if3002.miniproject2.model.Pemesanan

class MainViewMode : ViewModel() {

    val data = getDataDummy()

    private fun getDataDummy(): List<Pemesanan> {
        val data = mutableListOf<Pemesanan>()
        for (i in 29 downTo 20){
            data.add(
                Pemesanan(
                    i.toLong(),
                    "Andre S$i",
                    "mie ayam",
                    "1$i",
                    "cash",
                    "2024-05-$i 12:34:56"
                )
            )
        }
        return data
    }

}