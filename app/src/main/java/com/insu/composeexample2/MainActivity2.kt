package com.insu.composeexample2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import kotlinx.coroutines.launch

class MainActivity2: ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var (text, setValue) = remember {
                mutableStateOf("")
            }

            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            val keyboardController = LocalSoftwareKeyboardController.current

            Scaffold(
                scaffoldState = scaffoldState,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TextField(
                        value = text,
                        onValueChange = setValue
                    )
                    Button(onClick = {
                        keyboardController?.hide()
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Hello $text")
                        }
                    }) {
                        Text("클릭")
                    }
                }
            }
        }
    }
}