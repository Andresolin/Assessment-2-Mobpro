package org.d3if3002.miniproject2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pemesanan")
data class Pemesanan(
    @PrimaryKey(autoGenerate = true)
    val id :Long = 0L,
    val nama: String,
    val pesanan: String,
    val jumlah: String,
    val pembayaran: String,
    val total: String,
    val tanggal: String
)
