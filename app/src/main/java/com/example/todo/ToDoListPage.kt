package com.example.todo

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.key.Key.Companion.H
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import com.example.todo.ui.theme.AuthState
import com.example.todo.ui.theme.AuthViewModel
import com.example.todo.ui.theme.NavyBlue
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.content
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoListPage(
    modifier: Modifier = Modifier,
    viewModel: TodoViewmodel,
    navController: NavController,
    authViewModel: AuthViewModel
) {

    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.UnAuthenticated -> navController.navigate("logIn")
            else -> Unit
        }
    }

    val todoList by viewModel.todoList.observeAsState()
//    var inputText by remember {
//        mutableStateOf("")
//    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notes", color = colorResource(id = R.color.navyblue), textAlign = TextAlign.Start)},
                colors = TopAppBarDefaults.topAppBarColors(colorResource(id = R.color.terracota)),
                actions = {
                    Icon(modifier = Modifier
                        .padding(end = 10.dp)
                        .size(30.dp)
                        .clickable { authViewModel.signout() },
                        painter = painterResource(id = R.drawable.sign_out),
                        tint = colorResource(id = R.color.navyblue),
                        contentDescription = null
                    )
                }
            )
        }) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp)
                .padding(it)
        ) {
            todoList?.let {   //not nullable now, if data is there then only it'll show
                LazyColumn(content = {
                    itemsIndexed(it) { index: Int, item: Todo ->

                            TodoItem(item = item, onDelete = { viewModel.deleteTodo(item.id) })

                    }
                }
                )
            }?: Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                style = MaterialTheme.typography.bodyLarge,
                text = "Add Your ToDos",
            )
        }

            }



        Box(Modifier.fillMaxSize()) {
            FloatingActionButton(
                onClick = {
                    navController.navigate("addTodo") }, modifier = Modifier
                    .padding(bottom = 75.dp, end = 45.dp)
                    .size(70.dp)
                    .align(Alignment.BottomEnd),
                containerColor = colorResource(id = R.color.warmbeige),

            ) {

                Image(
                    painter = painterResource(id = R.drawable.add_task),
                    contentDescription = "add",

                )
            }
        }

    }






@Composable
fun TodoItem(item: Todo, onDelete: () -> Unit) {
    Card(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(id = R.color.beige))
                .padding(16.dp),

            )
        {
            Column(modifier = Modifier.weight(1f)) {
                Row {
                    Text(
                        text = SimpleDateFormat(
                            "HH:mm:aa",
                            Locale.ENGLISH
                        ).format(item.createdAt),
                        fontSize = 15.sp,
                        color = Color.White, modifier = Modifier.weight(2f)
                    )

                    IconButton(onClick = onDelete) {
                        Icon(
                            painter = painterResource(id = R.drawable.delete),
                            contentDescription = "Delete",
                            tint = colorResource(id = R.color.black),
                            modifier = Modifier
                                .size(26.dp)
                                .weight(1f)
                        )
                    }
                }
                Text(
                    text = item.title,
                    fontSize = 25.sp,
                    color = Color.Black,


                )
                Text(
                    text = item.body,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }


        }
    }
}

