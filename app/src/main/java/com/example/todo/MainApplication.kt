package com.example.todo

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.ui.theme.db.TodoDatabase

class MainApplication : Application() {

    companion object {
        lateinit var todoDatabase: TodoDatabase
    }

    override fun onCreate() {
        super.onCreate()
        todoDatabase=   Room.databaseBuilder(applicationContext, TodoDatabase::class.java, TodoDatabase.NAME).build()
    }

}