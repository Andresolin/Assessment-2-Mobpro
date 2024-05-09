package org.d3if3002.miniproject2.model

data class Pemesanan(
    val id: Long,
    val nama: String,
    val pesanan: String,
    val jumlah: String,
    val pembayaran: String,
    val total: String,
    val tanggal: String
)
