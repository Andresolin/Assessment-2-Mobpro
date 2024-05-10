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

    suspend fun getPemesanan(id: Long): Pemesanan? {
        return dao.getPemesananById(id)
    }

    fun update(id: Long,nama: String, pesanan: String, jumlah: String, pembayaran: String, total: String) {
        val pemesanan = Pemesanan(
            id = id,
            tanggal = formatter.format(Date()),
            nama = nama,
            pesanan = pesanan,
            jumlah = jumlah,
            pembayaran = pembayaran,
            total = total
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(pemesanan)
        }
    }
}