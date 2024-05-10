package org.d3if3002.miniproject2.ui.Screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.d3if3002.miniproject2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen() {
    var nama by remember { mutableStateOf("") }
    var pesanan by remember { mutableStateOf("") }
    var jumlah by remember { mutableStateOf("") }
    var pembayaran by remember { mutableStateOf("") }
    var total by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.tambah_pemesanan))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
    ) { padding ->
        FormCatatan(
            nama = nama,
            onNamaChange = { nama = it },
            pesanan = pesanan,
            onPesananChange = { pesanan = it },
            jumlah = jumlah,
            onJumlahChange = { jumlah = it },
            pembayaran = pembayaran,
            onPembayaranChange = { pembayaran = it },
            total = total,
            onTotalChange = { total = it },
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormCatatan(
    nama: String, onNamaChange: (String) -> Unit,
    pesanan: String, onPesananChange: (String) -> Unit,
    jumlah: String, onJumlahChange: (String) -> Unit,
    pembayaran: String, onPembayaranChange: (String) -> Unit,
    total: String, onTotalChange: (String) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = nama,
            onValueChange = { onNamaChange(it) },
            label = { Text(text = stringResource(R.string.nama)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Add more fields for other data items
        OutlinedTextField(
            value = pesanan,
            onValueChange = { onPesananChange(it) },
            label = { Text(text = stringResource(R.string.pesanan)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = jumlah,
            onValueChange = { onJumlahChange(it) },
            label = { Text(text = stringResource(R.string.jumlah)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = pembayaran,
            onValueChange = { onPembayaranChange(it) },
            label = { Text(text = stringResource(R.string.pembayaran)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = total,
            onValueChange = { onTotalChange(it) },
            label = { Text(text = stringResource(R.string.total)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    MaterialTheme {
        DetailScreen()
    }
}