package org.d3if3002.miniproject2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3002.miniproject2.model.Pemesanan

@Dao
interface PemesananDao {

    @Insert
    suspend fun insert(pemesanan: Pemesanan)

    @Update
    suspend fun update(pemesanan: Pemesanan)

    @Query("SELECT * FROM pemesanan ORDER BY tanggal DESC")
    fun getPemesanan(): Flow<List<Pemesanan>>

    @Query("SELECT * FROM pemesanan WHERE id = :id")
    suspend fun getPemesananById(id: Long): Pemesanan?

    @Query("DELETE FROM pemesanan WHERE id = :id")
    suspend fun deleteById(id: Long)

}