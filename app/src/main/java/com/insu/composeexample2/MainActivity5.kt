package com.insu.composeexample2

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class MainActivity5: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen(viewModel: Main5ViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val (text, setText) = remember {
        mutableStateOf("Hello World")
    }

    Column() {
        Text("Hello World")
        Button(onClick = {}) {
            Text("클릭")
        }
        TextField(value = text, onValueChange = setText)
    }
}

class Main5ViewModel: ViewModel() {
    private val _value = mutableStateOf("Hello World")
    val value: State<String>
        get() = _value
}