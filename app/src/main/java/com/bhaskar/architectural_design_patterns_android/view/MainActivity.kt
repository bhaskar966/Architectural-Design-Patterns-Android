package com.bhaskar.architectural_design_patterns_android.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.bhaskar.architectural_design_patterns_android.view.ui.theme.ArchitecturalDesignPatternsAndroidTheme
import com.bhaskar.architectural_design_patterns_android.viewmodel.ImageViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: ImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArchitecturalDesignPatternsAndroidTheme {

                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                var query by rememberSaveable { mutableStateOf("") }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    topBar = {

                        TextField(
                            value = query,
                            onValueChange = {
                                query = it
                                viewModel.updateQuery(query)
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            )
                        )
                    }
                ) { innerPadding ->
                    if(uiState.isLoading) {
                        Box(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    if(uiState.error.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(uiState.error)
                        }
                    }

                    uiState.data?.let { data ->
                        LazyColumn(
                            modifier = Modifier
                                .padding(innerPadding)
                                .padding(horizontal = 16.dp)
                                .fillMaxSize()
                        ) {
                            items(data) { image ->
                                AsyncImage(
                                    model = image.largeImageURL,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(300.dp),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}