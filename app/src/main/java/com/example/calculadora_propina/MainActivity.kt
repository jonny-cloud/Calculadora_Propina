package com.example.calculadora_propina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorScreen()
        }
    }
}

@Composable
fun TipCalculatorScreen() {
    var monto by remember { mutableStateOf("") }
    var porcentajePersonalizado by remember { mutableStateOf("") }
    var porcentajeSeleccionado by remember { mutableStateOf(15) }
    var resultado by remember { mutableStateOf("") }

    val morado = Color(0xFF0A2342)
    val fondo = Color(0xFFFFF8FC)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(morado)
                .padding(24.dp)
        ) {
            Column {
                Text(
                    text = "Calculadora de Propinas",
                    color = Color.White,
                    fontSize = 28.sp
                )
                Text(
                    text = "Tip Calculator App",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = fondo)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("MONTO DE LA CUENTA", fontSize = 16.sp)

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = monto,
                        onValueChange = { monto = it },
                        placeholder = { Text("$00.00") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(26.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = fondo)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("PORCENTAJE DE PROPINA", fontSize = 16.sp)

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        listOf(10, 15, 20).forEach { porcentaje ->
                            OutlinedButton(
                                onClick = {
                                    porcentajeSeleccionado = porcentaje
                                    porcentajePersonalizado = ""
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("$porcentaje%")
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = porcentajePersonalizado,
                            onValueChange = {
                                porcentajePersonalizado = it
                                porcentajeSeleccionado = 0
                            },
                            placeholder = { Text("%") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            singleLine = true,
                            modifier = Modifier.width(200.dp)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Porcentaje\npersonalizado",
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(26.dp))

            OutlinedButton(
                onClick = {
                    val valor = monto.toDoubleOrNull()
                    val porcentajeFinal =
                        porcentajePersonalizado.toIntOrNull() ?: porcentajeSeleccionado

                    resultado = if (valor != null && valor > 0 && porcentajeFinal > 0) {
                        val propina = valor * porcentajeFinal / 100
                        val total = valor + propina

                        "Propina: $${"%.2f".format(propina)}\nTotal a pagar: $${"%.2f".format(total)}"
                    } else {
                        "Ingrese valores válidos"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Calcular Propina", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = resultado,
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TipCalculatorScreenPreview() {
    TipCalculatorScreen()
}

// Fin de TipCalculatorScreen - Agregué TextFields y botón

// Lógica de cálculo implementada con mutableStateOf y remember