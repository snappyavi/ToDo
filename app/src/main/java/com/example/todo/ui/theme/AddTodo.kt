package com.example.todo.ui.theme

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todo.R
import com.example.todo.TodoViewmodel
import com.google.firebase.annotations.concurrent.Background


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodo(
    modifier: Modifier = Modifier,
    viewModel: TodoViewmodel,
    navController: NavController,
) {


    val todoList by viewModel.todoList.observeAsState()

    var title by remember {
        mutableStateOf("")
    }
    var body by remember {
        mutableStateOf("")
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Notes", color = colorResource(id = R.color.navyblue)) },
                colors = TopAppBarDefaults.topAppBarColors(colorResource(id = R.color.terracota)),
                actions = {

                    Icon(
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .size(30.dp)
                            .clickable {
                                viewModel.addTodo(title, body)

                                body = ""
                                navController.navigate("home")
                            },
                        painter = painterResource(id = R.drawable.done),
                        tint = colorResource(id = R.color.navyblue),
                        contentDescription = null,
                    )


                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .size(30.dp)
                            .clickable {

                                navController.navigate("home")
                            },
                        painter = painterResource(id = R.drawable.exit),
                        tint = colorResource(id = R.color.navyblue),
                        contentDescription = null,
                    )
                }
            )
        },

        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {

                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        // Set the background color
                        containerColor = colorResource(id = R.color.beige), // Using color resource for background
                        // Optional: You can also customize other colors (e.g., text color, focused color, etc.)
                        focusedIndicatorColor = Color.Black,
                    ),
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    placeholder = { Text(text = "Enter Title", fontSize = 25.sp) },
                )
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        // Set the background color
                        containerColor = colorResource(id = R.color.beige), // Using color resource for background
                        // Optional: You can also customize other colors (e.g., text color, focused color, etc.)
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    value = body,
                    onValueChange = { body = it },
                    modifier = Modifier.fillMaxSize(),
                    placeholder = { Text(text = "Enter Notes", fontSize = 18.sp) },

                )

            }
        }
    )


}