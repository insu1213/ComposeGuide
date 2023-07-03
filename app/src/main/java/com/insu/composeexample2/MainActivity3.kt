package com.insu.composeexample2

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "first",
            ) {
                composable("first") {
                    FirstScreen() { dest ->
                        navController.navigate(dest)
                    }
                }
                composable("second") {
                    SecondScreen() {
                        navController.popBackStack()
                    }
                }
                composable("third/{value}") { backStackEntry ->

                    ThirdScreen(
                        backStackEntry.arguments?.getString("value") ?: "",
                    ) {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}

@Composable
fun FirstScreen(
    destination: (String) -> Unit
) {
    val (value, setValue) = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "첫 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            destination("second")
        }) {
            Text("두 번째")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = value, onValueChange = setValue)
        Button(onClick = {
            if(value.isNotEmpty()) {
                destination("third/$value")
            }
        }) {
            Text("세 번째")
        }
    }
}

@Composable
fun SecondScreen(
    goBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "두 번째 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            goBack()
        }) {
            Text("뒤로 가기")
        }
    }
}

@Composable
fun ThirdScreen(
    value: String,
    goBack: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "세 번째 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Text(value)
        Button(onClick = {
            goBack()
        }) {
            Text("뒤로 가기")
        }
    }
}