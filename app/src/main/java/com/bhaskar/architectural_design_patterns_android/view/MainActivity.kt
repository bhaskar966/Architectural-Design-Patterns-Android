package com.bhaskar.architectural_design_patterns_android.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bhaskar.architectural_design_patterns_android.R
import com.bhaskar.architectural_design_patterns_android.controller.CounterControllerImpl
import com.bhaskar.architectural_design_patterns_android.model.CounterModel
import com.bhaskar.architectural_design_patterns_android.model.CounterModelImpl
import com.bhaskar.architectural_design_patterns_android.ui.theme.ArchitecturalDesignPatternsAndroidTheme

class MainActivity : ComponentActivity(), CounterView {

    // Model
    private val model: CounterModel by lazy { CounterModelImpl() }

    // Controller
    private val controller by lazy {
        CounterControllerImpl(
            model,
            this
        )
    }

    // View
    private val text by lazy { findViewById<TextView>(R.id.counter) }
    private val incrementBtn by lazy { findViewById<TextView>(R.id.increment_btn) }
    private val decrementBtn by lazy { findViewById<TextView>(R.id.decrement_btn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        incrementBtn.setOnClickListener { controller.onIncrementClick() }
        decrementBtn.setOnClickListener { controller.onDecrementClick() }

    }

    override fun showText(count: Int) {
        text.text = count.toString()
    }
}