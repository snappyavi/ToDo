package com.example.todo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.example.todo.ui.theme.AuthViewModel
import com.example.todo.ui.theme.Beige
import com.example.todo.ui.theme.Navigation
import com.example.todo.ui.theme.ToDoTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todoViewmodel = ViewModelProvider(this)[TodoViewmodel::class.java]
        val authViewModel: AuthViewModel by viewModels()
        setContent {
            SetStatusBarColor(Color(0xFFF5DEB3))
            ToDoTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                ) {
                    Navigation(authViewModel=AuthViewModel(), modifier=Modifier.padding(it))
                }
            }
        }
    }
}


@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}

