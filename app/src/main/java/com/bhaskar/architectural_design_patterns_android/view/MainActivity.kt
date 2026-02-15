package com.bhaskar.architectural_design_patterns_android.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.bhaskar.architectural_design_patterns_android.CounterContract
import com.bhaskar.architectural_design_patterns_android.R
import com.bhaskar.architectural_design_patterns_android.presenter.PresenterFactory

class MainActivity : ComponentActivity(), CounterContract.View {

    private val tvCounter: TextView by lazy { findViewById(R.id.tvCounter) }
    private val btnIncrement: Button by lazy { findViewById(R.id.btnIncrement) }
    private val btnDecrement: Button by lazy { findViewById(R.id.btnDecrement) }

    private lateinit var presenter: CounterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        presenter = PresenterFactory.provide()

        presenter.attach(this)

        btnIncrement.setOnClickListener { presenter.onIncrementCounter() }
        btnDecrement.setOnClickListener { presenter.onDecrementCounter() }

    }

    override fun showCounter(counter: Int) {
        tvCounter.text = counter.toString()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }
}