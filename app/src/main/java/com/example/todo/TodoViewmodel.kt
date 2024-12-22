package com.example.todo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.ui.theme.db.TodoDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class TodoViewmodel : ViewModel() {

    val todoDao = MainApplication.todoDatabase.getTodoDao()

    val todoList: LiveData<List<Todo>> = todoDao.getAllData()

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTodo(title: String, body:String) {
        viewModelScope.launch(Dispatchers.IO) {  //creates a different thread to do heavy task and prevent crash
            todoDao.addTodo(Todo(title = title, body =body, createdAt = Date.from(Instant.now())))
        }


    }

    fun deleteTodo(id: Int) {
        viewModelScope.launch(Dispatchers.IO){
            todoDao.deleteTodo(id)
        }
    }


}