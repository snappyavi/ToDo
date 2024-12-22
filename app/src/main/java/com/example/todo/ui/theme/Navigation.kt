package com.example.todo.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo.TodoListPage
import com.example.todo.TodoViewmodel
import com.example.todo.ui.theme.pages.HomePage
import com.example.todo.ui.theme.pages.LogIn
import com.example.todo.ui.theme.pages.SignUp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController= rememberNavController()

    NavHost(navController = navController, startDestination = "logIn", builder = {
        composable("logIn"){
            LogIn(modifier, navController, authViewModel)
        }
        composable("signUp"){
            SignUp(modifier, navController, authViewModel)
        }
        composable("home"){
            TodoListPage(modifier, TodoViewmodel(), navController, authViewModel)
        }
        composable("addTodo"){
            AddTodo(modifier, TodoViewmodel(), navController)
        }
    })
}