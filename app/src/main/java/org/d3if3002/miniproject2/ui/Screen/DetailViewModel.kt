package org.d3if3002.miniproject2.ui.Screen

import androidx.lifecycle.ViewModel
import org.d3if3002.miniproject2.model.Pemesanan

class DetailViewModel: ViewModel() {

    fun getPemesanan(id: Long): Pemesanan {
        return Pemesanan(
            id,
            "Andre $id",
            "mie ayam",
            "1$id",
            "cash",
            "Rp 1000$id",
            "2024-05-$id 12:34:56"
        )
    }
}