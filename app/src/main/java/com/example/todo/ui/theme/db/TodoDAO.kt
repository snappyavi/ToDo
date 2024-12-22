package com.example.todo.ui.theme.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todo.Todo

@Dao
interface TodoDAO {

    @Query("SELECT * FROM Todo")
    fun getAllData():LiveData<List<Todo>>

    @Insert
    fun addTodo(todo: Todo)

    @Query("DELETE FROM Todo where id=:id")
    fun deleteTodo(id:Int)

}