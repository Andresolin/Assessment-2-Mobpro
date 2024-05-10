package org.d3if3002.miniproject2.ui.Screen

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3002.miniproject2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController) {
    var nama by remember { mutableStateOf("") }
    var pesanan by remember { mutableStateOf("") }
    var jumlah by remember { mutableStateOf("") }
    var pembayaran by remember { mutableStateOf("") }
    var total by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.tambah_pemesanan))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(id = R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary

                        )
                    }
                }
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

    val radioOption = listOf(
        stringResource(id = R.string.cash),
        stringResource(id = R.string.qris)
    )

    var pembayaran by remember { mutableStateOf(radioOption[0])}

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

        Row(
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            radioOption.forEach{ text ->
                PaymentOption(label = text,
                    isSelected = pembayaran == text,
                    modifier = Modifier
                        .selectable(
                            selected = pembayaran == text,
                            onClick = { pembayaran = text },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }

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
fun PaymentOption(label: String, isSelected: Boolean, modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null
        )
        Text(text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )

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