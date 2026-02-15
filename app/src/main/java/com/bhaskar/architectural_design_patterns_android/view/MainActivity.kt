package com.bhaskar.architectural_design_patterns_android.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhaskar.architectural_design_patterns_android.intent.CounterIntent
import com.bhaskar.architectural_design_patterns_android.view.ui.theme.ArchitecturalDesignPatternsAndroidTheme

class MainActivity : ComponentActivity() {

    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArchitecturalDesignPatternsAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Text(text = "Counter: ${uiState.counter}")

                        Spacer(Modifier.height(12.dp))

                        Button(
                            onClick = {
                                viewModel.onIntent(CounterIntent.Increment)
                            }
                        ) {
                            Text("Increment")
                        }

                        Spacer(Modifier.height(12.dp))

                        Button(
                            onClick = {
                                viewModel.onIntent(CounterIntent.Decrement)
                            }
                        ) {
                            Text("Decrement")
                        }

                    }

                }
            }
        }
    }
}