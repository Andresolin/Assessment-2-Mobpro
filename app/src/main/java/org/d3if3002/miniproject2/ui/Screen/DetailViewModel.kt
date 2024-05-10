package org.d3if3002.miniproject2.ui.Screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3002.miniproject2.database.PemesananDao
import org.d3if3002.miniproject2.model.Pemesanan
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModel(private val dao: PemesananDao) : ViewModel() {

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(nama: String, pesanan: String, jumlah: String, pembayaran: String, total: String) {

        val pemesanan = Pemesanan(
            tanggal = formatter.format(Date()),
            nama = nama,
            pesanan = pesanan,
            jumlah = jumlah,
            pembayaran = pembayaran,
            total = total
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(pemesanan)
        }
    }

    fun getPemesanan(id: Long): Pemesanan {
        return Pemesanan(
            id = id,
            nama = "Andre $id",
            pesanan = "mie ayam",
            jumlah = "1$id",
            pembayaran = "Qris",
            total = "Rp 1000$id",
            tanggal = "2024-05-$id 12:34:56"
        )
    }
}