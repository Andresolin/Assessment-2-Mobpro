package org.d3if3002.miniproject2.ui.Screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3002.miniproject2.R
import org.d3if3002.miniproject2.database.PemesananDb
import org.d3if3002.miniproject2.util.ViewModelFactory


const val KEY_ID_PEMESANAN = "idPemesanan"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null) {


    val context = LocalContext.current
    val db = PemesananDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    var nama by remember { mutableStateOf("") }
    var pesanan by remember { mutableStateOf("") }
    var jumlah by remember { mutableStateOf("") }
    var total by remember { mutableStateOf("") }

    // Tambahkan variabel pembayaran ke dalam mutableStateOf
    var pembayaran by remember { mutableStateOf("") }
    val radioOptions = listOf(
        stringResource(id = R.string.cash),
        stringResource(id = R.string.qris)
    )

    LaunchedEffect(true) {
        if (id == null ) return@LaunchedEffect
        val data = viewModel.getPemesanan(id) ?: return@LaunchedEffect
        nama = data.nama
        pesanan = data.pesanan
        jumlah = data.jumlah
        pembayaran = data.pembayaran
        total = data.total
    }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {


                        navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if (id == null)
                        Text(text = stringResource(id = R.string.tambah_pemesanan))
                    else
                        Text(text = stringResource(id = R.string.edit_pemesanan))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        if (nama == "" || pesanan == ""|| jumlah == ""|| pembayaran == ""|| total == "") {
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_SHORT).show()
                            return@IconButton
                        }

                        if (id == null) {
                            viewModel.insert(nama, pesanan, jumlah, pembayaran, total)
                        } else {
                            viewModel.update(id, nama, pesanan, jumlah, pembayaran, total)
                        }

                        navController.popBackStack()  }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(id = R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (id != null) {
                        DeleteAction {
                            viewModel.delete(id)
                            navController.popBackStack()
                        }
                    }
                }
            )
        },
    ) { padding ->
        // Memanggil FormCatatan dengan parameter yang diperlukan
        FormPemesanan(
            nama = nama,
            onNamaChange = { nama = it },
            pesanan = pesanan,
            onPesananChange = { pesanan = it },
            jumlah = jumlah,
            onJumlahChange = { jumlah = it },
            pembayaran = pembayaran, // Gunakan variabel pembayaran di sini
            onPembayaranChange = { pembayaran = it },
            radioOptions = radioOptions,// Ubah value variabel pembayaran
            total = total,
            onTotalChange = { total = it },
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormPemesanan(
    nama: String,
    onNamaChange: (String) -> Unit,
    pesanan: String,
    onPesananChange: (String) -> Unit,
    jumlah: String,
    onJumlahChange: (String) -> Unit,
    pembayaran: String,
    onPembayaranChange: (String) -> Unit,
    radioOptions: List<String>,
    total: String,
    onTotalChange: (String) -> Unit,
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

        // Field untuk pesanan
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

        // Field untuk jumlah
        OutlinedTextField(
            value = jumlah,
            onValueChange = { onJumlahChange(it) },
            label = { Text(text = stringResource(R.string.jumlah)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier.fillMaxWidth()
        )

        // Radio Button Options untuk pembayaran
        Row(
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            radioOptions.forEach { text ->
                PaymentOption(
                    label = text,
                    isSelected = pembayaran == text,
                    onOptionSelected = { onPembayaranChange(text) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }

        // Field untuk total
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

@Composable
fun PaymentOption(
    label: String,
    isSelected: Boolean,
    onOptionSelected: () -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier.clickable(onClick = onOptionSelected),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null // RadioButton di-handle oleh onClick di Row
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}


@Composable
fun DeleteAction(delete: () -> Unit) {
    var expanded by remember { mutableStateOf(false)}

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(id = R.string.lainya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(text =
            {
                Text(text = stringResource(id = R.string.hapus))
            },
                onClick = {
                    expanded = false
                    delete()
                })
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    MaterialTheme {
        DetailScreen(rememberNavController())
    }
}