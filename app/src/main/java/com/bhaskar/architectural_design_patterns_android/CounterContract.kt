package com.bhaskar.architectural_design_patterns_android

interface CounterContract {

    interface View {
        fun showCounter(counter: Int)
    }

    interface Model {
        fun incrementCounter()
        fun decrementCounter()
        fun getCounter(): Int
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onIncrementCounter()
        fun onDecrementCounter()
    }


}