package com.xcheko51x.leer_codigo_barras_zxing_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.xcheko51x.leer_codigo_barras_zxing_compose.ui.theme.Leer_Codigo_barras_zxing_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var resultadoEscaner by remember { mutableStateOf("") }
            val scanLauncher = rememberLauncherForActivityResult(
                contract = ScanContract(),
                onResult = { result ->
                    resultadoEscaner = result.contents?: "No hay resultado"
                }
            )

            Leer_Codigo_barras_zxing_ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Resultado: ${resultadoEscaner}")
                        Button(
                            onClick = {
                                val scanOptions = ScanOptions()
                                scanOptions.setBeepEnabled(true)
                                scanOptions.setCaptureActivity(CaptureActivityPortrait::class.java)
                                scanOptions.setOrientationLocked(false)
                                scanLauncher.launch(scanOptions)
                            }
                        ) {
                            Text(text = "Escanear")
                        }
                    }
                }
            }
        }
    }
}

